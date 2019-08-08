package JUC原子类的使用.基本类型;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author:tx
 * @Date:2019/7/17
 * @Description:
 */
public class AtomicBooleanTest {
    public static void main(String[] args) {
//        AtomicBoolean atomicBoolean = new AtomicBoolean();
//        boolean b = atomicBoolean.compareAndSet(false, true);
//        atomicBoolean.lazySet(false);
//        System.out.println(atomicBoolean);
//
        final List<Integer> list = new ArrayList<>();
        List<Integer> tempList = new ArrayList<>();
        list.add(1);
        tempList.add(2);

        list.addAll(tempList);
//        list.get(0);
        list.remove(0);

        System.out.println(list);
    }

    @Test
    public void fun1() {
        AtomicBoolean atomicBoolean = new AtomicBoolean();
        boolean b = atomicBoolean.compareAndSet(false, true);
        atomicBoolean.lazySet(false);
        System.out.println(atomicBoolean);
    }
}
