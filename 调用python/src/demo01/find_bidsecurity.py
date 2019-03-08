# coding = utf-8
#!/usr/bin/env python3
# -*- coding: utf-8 -*-

"""
Created on Thurday Nov 22 14:40 2018
@author:Yang Yunli

"""

from fuzzywuzzy import fuzz
import numpy as np
import pandas as pd
import datetime
import copy
import drawGraph
import demjson


def turn_date_detail(date):
    l = len(date)
    y = date[0:4]
    m = date[5] if date[4]=='0' else date[4:6]      
    d = date[7] if date[6]=='0' else date[6:8]

    if l>8 and date[8] != 't':
        h = date[9] if date[8]=='0' else date[8:10]
    else:
        h = 0
    if l > 10 and date[10]!='t':
        i = date[11] if date[10]=='0' else date[10:12]
    else:
        i = 0
    if l > 12 and date[12]!='t':
        s = date[13] if date[12]=='0' else date[12:14]
    else:
        s = 0

    return datetime.datetime(int(y), int(m), int(d), int(h), int(i),int(s))

def change(type_rate, amt):
    rate={'人民币元': 1, '美元': 6.6451,'香港元':0.8450,'欧元':7.7259,'日元':0.06001,'澳大利亚元':4.9054,'加元':5.0466,'新加坡元':4.8635,'澳门元':0.8222}
    return rate[type_rate]*amt

def exchange(data):
    data = data[data['tradeAmt'].notnull()]
    data['tradeAmt'] = data['tradeAmt'].astype(str)
    data['tradeAmt'] = data['tradeAmt'].apply(lambda x: x.replace(',', ''))
    data['tradeCny'] = data['tradeCny'].apply(lambda x: x[0:x.find('元')+1])
    data['tradeCny'] = data['tradeCny'].fillna('人民币元')
    data['tradeCny'] = data['tradeCny'].replace('', '人民币元')
    data['tradeAmt'] = data['tradeAmt'].astype(float)

    data['tradeAmt'] = data[['tradeCny','tradeAmt']].apply(lambda row: change(row[0],row[1]), axis=1)

    return data

def readData(data_ori, Security_deposit):

    # 读入数据，取等于保证金金额的交易数据，返回DataFrame格式
    data_ori = pd.DataFrame(demjson.decode(data_ori))

    data_ori['tradeTime'] = data_ori['tradeTime'].astype(str)
    data1 = data_ori.copy()
    data1['tradeTime_datetime'] = data1[['tradeTime']].apply(lambda row: turn_date_detail(row[0]), axis=1)
    data1 = exchange(data1)
    data2 = data1[data1['tradeAmt']==Security_deposit]

    #将人名空值替换为ATM_Account
    data2.replace({'payBankAccount':''},'ATM_Account')
    data2.replace({'recBankAccount':''},'ATM_Account')

    #将人名空值替换为ATM
    data2.replace({'payPersonName':''},'ATM')
    data2.replace({'recPersonName':''},'ATM')

    return data2

#最简单的自定义异常
class FError(Exception):
    pass

def find_rec_money_list(elem, List, key_account_list):
    '''
    Function
        通过某个账号elem是否来源于List中元素，判断该账号elem向key_account_list中哪些账号转过保证金
    Input
        elem: 某个账号，即可能是下面[]中的某个元素
        List: [[],[],...[]]形式，向当前层调单主体账号发生转钱行为的所有账号
        key_account_list： 调单账号～即收到保证金的所有账号
    Output
        elem_out_list：list型，收到过来自elem保证金的所有账号，key_account_list的子集

    '''

    k = 0
    elem_out_list = []
    for item in List:
        if elem in item:
            elem_out_list.append(key_account_list[k])
        k = k + 1
        
    return elem_out_list

def is_from_sameAccount(data,List,key_account_list,right_left_type='right',min_bid_person=3):
    '''
    Function
        向多个账号转账的list，判断是否有高于2个账户的保证金是同时来源于同于账户的
    Input
        List: [[],[],...[]]形式，向当前层调单主体账号发生转钱行为的所有账号
        min_bid_person：默认为3， 是否有3个及以上个账户保证金是来源于同一账号的  
    Output
        0、1表示否找到串投模式；
        money_map_over_min: dict，形如{'key1':[val1,val2,...,valn],'key2':[...] }，串投行为字典，key为串投人，val为收到key转钱的账户列表
        Bidding_list: list，记录所有参与被串投的人员
    '''
    money_map_over_min = {}
    Bidding_list = []
    if len(List)<min_bid_person:
        return 0, money_map_over_min, Bidding_list
    else:
        ## 将所有涉及到的账户形成List_
        List_ = []
        for item in List:
            List_ = List_ + item
        List_ = list(set(List_))

        for elem in List_:
            elem_out_list =  find_rec_money_list(elem, List, key_account_list)

            ## 如果elem转帐到的账户elem_out_list大于最小串投人数min_bid_person，记录下串投人相关信息
            if len(elem_out_list)>=min_bid_person:
                money_map_over_min[elem] = elem_out_list
                Bidding_list = Bidding_list + elem_out_list

        if money_map_over_min!={}:
            return 1, money_map_over_min, Bidding_list
        else:
            return 0, money_map_over_min, Bidding_list

def find_map_forFirstPart(data_before,data_after,Master_list,personList=[],accountList=[],agent_name=None):   
    '''
    Function：
        找到第一层交易map中的交易节点header和交易详情detial
    Input：
        agent_name: str代理商户名,如果为None，说明没有找到代理商
        data_before: dataframe型，投标截止日期之前的交易数据-查哪些账户转过保证金
        data_after: dataframe型，投标截止日期之前的交易数据-查保证金返回路线
        Master_list: list，基本户户名
        personList: list,涉及到的非代理商户名列表
        accountList: list,涉及到的非代理商账号列表
    Ouput：
    relation_header：list，其中每个元素是dict型，各元素具有相同的key，存放交易节点head
    relation_detail：list，其中每个元素是dict型，各元素具有相同的key，存放交易详情detial
    ''' 

    data_info1 = data_before[data_before['recPersonName']==agent_name].reset_index(drop = True)
    data_info2 = data_after[data_after['payPersonName']==agent_name].reset_index(drop = True)  
    data_info = data_info1.append(data_info2,ignore_index=True)## 暂未考虑基本户与基本户之间的detial交易
    ## 记录基本户内部之间交易额恰为保证金数据的交易数据
    for i in range(len(data_before)):
        item = data_before.iloc[i]
        if item['payPersonName'] in Master_list and item['recPersonName'] in Master_list:
            data_info = data_info.append(item)

    relation_detial = []
    for i in range(len(data_info)):
        item = data_info.iloc[i]
        relation = {}
        relation['payBankAccount'] = item['payBankAccount']
        relation['payPersonName'] = item['payPersonName']
        relation['recBankAccount'] = item['recBankAccount']
        relation['recPersonName'] = item['recPersonName']
        relation['tradeAmt'] = item['tradeAmt']
        relation['tradeTime'] = item['tradeTime_datetime']
        relation_detial.append(relation)
        ## 顺便记录涉及到的账号
        if relation['recBankAccount'] not in accountList and relation['recBankAccount']!=agent_name:
            accountList.append(relation['recBankAccount'])
            personList.append(relation['recPersonName'])
        if relation['payBankAccount'] not in accountList and relation['payBankAccount']!=agent_name:
            accountList.append(relation['payBankAccount'])
            personList.append(relation['payPersonName'])
    ## 按照涉及到的账号&名字，记录节点head内容
    relation_header = []
    for k in range(len(accountList)):
        relation = {}
        relation['bankAccount'] = accountList[k]
        relation['personName'] = personList[k]
        relation['roleType'] = 'based'
        relation_header.append(relation)
    if agent_name is not None:     
        relation = {}
        relation['bankAccount'] = str(data_info1.iloc[0]['recBankAccount'])
        relation['personName'] = agent_name
        relation['roleType'] = 'agent'
        relation_header.append(relation)


    return relation_header, relation_detial

def find_map_forMiddlePart(person_list, requestPersons, data_before):
    '''
    Function：
        找到第k（大于1）层交易map中的交易节点header和交易详情detial
    Input：
        person_list：list型，当前层调单账户形成的列表
        requestPersons：list型，当前层发起串投的账户列表
    Ouput：
    relation_header：list，其中每个元素是dict型，各元素具有相同的key，存放交易节点head
    relation_detail：list，其中每个元素是dict型，各元素具有相同的key，存放交易详情detial
    requestAccount：list，待调单的账户其对应的账号信息
    ''' 

    relation_header = []
    relation_detial = []
    requestAccount = []
    for person in requestPersons:
        data_info = data_before[data_before['payPersonName']==person]
        ## 添加待调单的人
        relation = {}
        account = data_info.iloc[0]['payBankAccount']
        relation['bankAccount'] = account
        relation['personName'] = person
        relation['roleType'] = 'bidding'
        relation_header.append(relation)
        requestAccount.append(account)
        rec_person_list = list(set(list(data_info['recPersonName'])))
        for rec_person in rec_person_list:
            if rec_person in person_list:
                data_i = data_info[data_info['recPersonName']==rec_person]
                ## 按照名字提取调单主题时，可能存在多个转帐时间或者账号
                for i in range(len(data_i)):
                    item = data_i.iloc[i]
                    relation = {}
                    relation['payPersonName'] = item['payPersonName']
                    relation['payBankAccount'] = item['payBankAccount']
                    relation['recPersonName'] = item['recPersonName']
                    relation['recBankAccount'] = item['recBankAccount']
                    relation['tradeAmt'] = item['tradeAmt']
                    relation['tradeTime'] = item['tradeTime_datetime']
                    relation_detial.append(relation)

    return relation_header, relation_detial, requestAccount

def Find_bidsecurity_new(dataList,amount,time2,k=1,mapping='null'):
    '''
    Function:
        查找串投的主函数，查找是否有三家以上单位保证金来自同一单位
    Input：
        dataList: dataframe，某个工程相关的交易数据
        time2:  招标截止时间或中标公布时间，time2之前发生串投行为&向代理商交保证金行为，time2之后代理商向各投标商退还保证金
        amount: 保证金金额
        k: 第几次调用算法
        mapping: 默认为null, 上次分析给出的连通图
    Output：
        Mapping: dict,当前形成的map图（结构参考团伙）
        resquestPersons: list,当前需要请求的人名列表
        requestAccounts: list,当前待请求数据对应的账号列表
        isFound: 1 or 0 当前是否已达到终止规则，1代表是，0代表否
    '''

    data = readData(dataList, amount)
    data = data.drop_duplicates().reset_index(drop = True)
    
    data_before = data[data['tradeTime']<=time2] #time2代表投标截止日期
    data_after = data[data['tradeTime']>time2]

    Mapping = {} ##存储新的mapping结果
    requestPersons = []
    requestAccounts = []
    isFound = 0

    if len(data)==0:
        raise FError("按照保证金amount提取后,数据为空,请注意检查!")
    elif len(data_before)==0:
        raise FError("按照时间time提取后,time2之前交易数据为空,请注意检查!")
    elif len(data_after)==0:
        raise FError("按照时间time提取后,time2之后交易数据为空,请注意检查!")
    
 
    person_list = list(set(data['masterName'])) ## 调单主题不包括收保证金的代理账号

    if mapping == 'null': 
        head_list_before = []#存储第一层基本户交易数据
        detial_list_before = []#存储第一层基本户交易数据
    else:
        head_list_before = mapping['header']#找到已有header信息
        detial_list_before = mapping['detial']#找到已有detail信息
    Agent_list = [] ## 存储k=1时，找到的保证金代收账号
    head_list_based = [] ## 存储k=1时，找到节点信息列表
    detial_list_based = [] ## 存储k=1时，找到交易信息列表

    
    if k == 1:
        ## 查找由基本账户出发，将保证金转向的代理商
        L_left = []
        for person in person_list:
            L_left.append(list(set(data_before[data_before['payPersonName']==person]['recPersonName'])))

        ## 查看当前调单账号的钱都从哪里转来
        agent = list(set(L_left[0]).intersection(*L_left[1:]))##多个list的交集
        if len(agent) == 0 :
            try:
                raise FError("未找到代理收保证金账户，请注意检查!")
            except FError as e:
                print(e)

        elif len(agent) ==1:
            backing_to_person = set(list(data_after[data_after['payPersonName']==agent[0]]['recPersonName']))
            print (backing_to_person)
            if set(person_list).intersection(set(backing_to_person)) is None:
                try:
                    raise FError("不太确定代收保证金机构是否正确，请注意检查!")
                except FError as e:
                    print(e)
            else:
                print ('当前已发现代收保证金的机构是:%s' % agent[0])
                Agent_list.append(agent[0])

        else:
            print ('当前model不支持一个工程中包含两个代收保证金机构的情况！')
            return 
        Agent_list=[]
        if len(Agent_list)!=0:
            ## 查找基本户的串投map图
            head_list_based, detial_list_based = find_map_forFirstPart(data_before,data_after,person_list,agent_name=agent[0])
        else:
            ## 查找基本户的串投map图
            head_list_based, detial_list_based = find_map_forFirstPart(data_before,data_after,person_list,agent_name=None)

    
    ## 通过查找之前的mapping，找到代收保证金的账户(其角色类型定义为agent)
    for item in head_list_before:
        if item['roleType']=='agent':
            Agent_list.append(item['personName'])    

    ## 查找有哪些账号向基本账号转保证金
    L_right = []
    for account in person_list:
        L_i = data_before[data_before['recPersonName']==account]['payPersonName']  
        L_right.append(list(set(L_i).difference(Agent_list)))

    flag, res, Bidding_list = is_from_sameAccount(data_before,L_right,person_list)
    requestPersons = list(res.keys())

    ## 查找串投详情
    head_list_mid, detial_list_mid,requestAccounts = find_map_forMiddlePart(person_list, requestPersons, data_before)
     
    ### 查找是调单主题但是并未串投的人
    person_need = list(set(person_list).difference(set(Bidding_list)))
    
    ## 是调单主体但未参与串投，找给其转钱的人作为请求调单的部分
    for person in person_need:
        data_info = data_before[data_before['recPersonName']==person]
        data_info = data_info.drop_duplicates(['payBankAccount','payPersonName'])
        account_list_req = list(data_info['payBankAccount'])
        person_list_req = list(data_info['payPersonName'])
        for i in range(len(person_list_req)):
            item = person_list_req[i]
            if item not in person_list: ### 按照人名字去调取，可能存在问题，需要修改（如：调单主题中人名，但是使用账号不知同一个）
                requestAccounts.append(account_list_req[i])
                requestPersons.append(person_list_req[i])


    Mapping['header']=head_list_before+head_list_based+head_list_mid
    Mapping['detial']=detial_list_before+detial_list_based+detial_list_mid
    isFound = flag

    return Mapping, requestPersons, requestAccounts, isFound 



##### mian函数-供前端调用 #####
if __name__ == "__main__":

    #Mapping, resquestPersons, requestAccounts, isFound = Find_bidsecurity_new(path_file,amount,time2,k,mapping)
    Mapping, resquestPersons, requestAccounts, isFound  = Find_bidsecurity_new(sys.argv[1],sys.argv[2],sys.argv[3],sys.argv[4],sys.argv[5])




