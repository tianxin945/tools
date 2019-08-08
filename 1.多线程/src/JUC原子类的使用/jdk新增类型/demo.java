package JUC原子类的使用.jdk新增类型;

import org.junit.Test;

/**
 * @author:tx
 * @Date:2019/7/20
 * @Description:
 */
public class demo {
    //    DoubleAccumulator
    @Test
    public void fun1() {


    }

    //    DoubleAccumulator
    @Test
    public void fun2() {


    }

    //    DoubleAccumulator
    @Test
    public void fun3() {


    }

    //    DoubleAccumulator
    @Test
    public void fun4() {

        System.out.println(Thread.currentThread().getName());
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getId());
            }
        }).start();
//        ArrayBlockingQueue
    }
}
