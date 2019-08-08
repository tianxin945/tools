import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author tianxin
 * @Date 2019/8/1
 * @Description:
 */
public class test {
    @Test
    public void fun1(){
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(1);
        list.add(2);
        list.add(2);
        System.out.println(list.indexOf(2));

        String s1 = new String("a");
        String s2 = "a";
        System.out.println(s1==s2);
        System.out.println(s1.equals(s2));

        Object object = new Object();



    }
}
