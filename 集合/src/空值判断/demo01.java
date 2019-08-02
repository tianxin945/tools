package 空值判断;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author:tx
 * @Date:2019/8/2
 * @Description:
 */
public class demo01 {
    @Test
    public void fun1(){
        List<String> list = new ArrayList<>();
        list.add("3");
        list.add("2");
        list.add("");
        list.add("");
        String s = list.toString().replace(",","").replace("[","").replace("]","").replaceAll("\\s","");
        if("".equals(s)){
            System.out.println("lalala");
        }

        System.out.println(list);
        System.out.println(s.charAt(1));
    }
}
