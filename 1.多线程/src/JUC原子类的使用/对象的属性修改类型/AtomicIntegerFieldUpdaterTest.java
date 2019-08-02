package JUC原子类的使用.对象的属性修改类型;

import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author:tx
 * @Date:2019/7/18
 * @Description:
 */
public class AtomicIntegerFieldUpdaterTest {
    public static void main(String[] args) {
//        System.out.println(Thread.currentThread().getName());
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                System.out.println(Thread.currentThread().getName());
//            }
//        }).start();
        CopyOnWriteArrayList<Integer> list = new CopyOnWriteArrayList<Integer>();
        list.add(1);
        System.out.println(list);
    }
}
