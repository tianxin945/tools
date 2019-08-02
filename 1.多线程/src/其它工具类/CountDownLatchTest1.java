package 其它工具类;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author:tx
 * @Date:2019/6/2
 * @Description:
 */
public class CountDownLatchTest1 {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService threadPool = Executors.newCachedThreadPool();
        CountDownLatch start = new CountDownLatch(1);
        CountDownLatch end = new CountDownLatch(3);
        for (int i = 0; i < 3; i++) {
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println(Thread.currentThread().getName() + "正在等待中");
                        start.await();

                        System.out.println("demo");


                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            };
            threadPool.execute(runnable);
        }

        Thread.sleep(2000);
        start.countDown();


    }
}
