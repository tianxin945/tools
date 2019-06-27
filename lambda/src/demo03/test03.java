package demo03;

import org.junit.Test;

/**
 * @Author tianxin
 * @Date 2019/2/12
 * @Description:
 */
public class test03 {
    @Test
    public void fun1() {
        Integer s = op(1, 2, (x, y) -> x + y);
        System.out.println(s);
    }

    public Integer op(Integer t1, Integer t2, MyFunction2<Integer, Integer> myFunction2) {

        return myFunction2.sour(t1, t2);
    }
}
