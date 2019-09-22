package JUC原子类的使用;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicLong;

/**
 * @author:tx
 * @Date:2019/7/2
 * @Description:
 */
public class Long {
    @Test
    public void fun1(){
        AtomicLong i = new AtomicLong();
        i.set(1);
        System.out.println(i.get());
    }
}
