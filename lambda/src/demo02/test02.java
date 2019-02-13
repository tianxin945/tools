package demo02;

import org.junit.Test;

/**
 * @Author tianxin
 * @Date 2019/2/12
 * @Description:
 */
public class test02 {
    @Test
    public void fun1(){
        String s = changeStr("abcde",(x) -> x.toUpperCase());
        String s2 = changeStr("abcde",(x) -> x.substring(2,3));
        System.out.println(s2);
    }
    public String changeStr(String str,MyFunction myFunction){
        return myFunction.getValue(str);
    }
}
