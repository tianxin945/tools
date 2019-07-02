package JUC;

import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 * @author:tx
 * @Date:2019/7/2
 * @Description:
 */
public class Array {
    public static void main(String[] args) {
        AtomicIntegerArray arr = new AtomicIntegerArray(5);
        arr.set(0,1);
        arr.set(1,2);
        arr.set(2,3);
        arr.getAndAdd(0,1);
        boolean b = arr.weakCompareAndSet(0, 2, 3);
        System.out.println(b);
        System.out.println(arr);

    }
}
