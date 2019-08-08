package JUC原子类的使用.基本类型;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author:tx
 * @Date:2019/7/17
 * @Description:
 */
public class AtomicBooleanTest {
    @Test
    public void fun1() {
        AtomicBoolean atomicBoolean = new AtomicBoolean();
        boolean b = atomicBoolean.compareAndSet(false, true);
        atomicBoolean.lazySet(false);
        System.out.println(atomicBoolean);
    }
}
