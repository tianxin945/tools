package src;

import java.util.ArrayList;
import java.util.List;

/**
 * @author:tx
 * @Date:2019/9/22
 * @Description:
 */
public class test {
    public static void main(String[] args) {
        List<Person> list = new ArrayList<>();
        list.add(new Person("1", "2", "田鑫1", "开户行1", "111"));
        list.add(new Person("1", "2", "田鑫2", "开户行2", "222"));
        list.add(new Person("1", "2", "田鑫3", "开户行3", "333"));
        list.add(new Person("1", "2", "田鑫4", "开户行4", "444"));
        list.add(new Person("3", "", "田鑫5", "开户行5", "555"));
        list.add(new Person("", "4", "田鑫6", "开户行6", "666"));
        // 1. 填充

        // 2.卡号为空，那么就以账号作为标志
        for (Person data : list) {
            if (null != data.getCardNo() && "" != data.getCardNo()) {
                data.setFlag(data.getCardNo());
                continue;
            } else {
                data.setFlag(data.getAccountNo());
            }
        }
        // 3. 根据标志进行统计和去重



    }
}
