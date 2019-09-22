package src;

/**
 * @author:tx
 * @Date:2019/9/22
 * @Description:
 */
public class Person {
    private String cardNo;
    private String accountNo;
    private String openName;
    private String openBank;
    private String IdNumber;
    private String flag;
    private String openNameList;
    private String openBankList;
    private String IdNumberList;

    public String getOpenNameList() {
        return openNameList;
    }

    public void setOpenNameList(String openNameList) {
        this.openNameList = openNameList;
    }

    public String getOpenBankList() {
        return openBankList;
    }

    public void setOpenBankList(String openBankList) {
        this.openBankList = openBankList;
    }

    public String getIdNumberList() {
        return IdNumberList;
    }

    public void setIdNumberList(String idNumberList) {
        IdNumberList = idNumberList;
    }

    public Person(String cardNo, String accountNo, String openName, String openBank, String idNumber) {
        this.cardNo = cardNo;
        this.accountNo = accountNo;
        this.openName = openName;
        this.openBank = openBank;
        IdNumber = idNumber;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public Person() {
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getOpenName() {
        return openName;
    }

    public void setOpenName(String openName) {
        this.openName = openName;
    }

    public String getOpenBank() {
        return openBank;
    }

    public void setOpenBank(String openBank) {
        this.openBank = openBank;
    }

    public String getIdNumber() {
        return IdNumber;
    }

    public void setIdNumber(String idNumber) {
        IdNumber = idNumber;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }
}
