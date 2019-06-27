package src.BlockingQueue;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;

/**
 * @Author tianxin
 * @Date 2019/6/27
 * @Description:
 */
public class Customer implements Runnable {
    private BlockingQueue blockingDeque = null;

    public Customer(BlockingQueue blockingDeque) {
        this.blockingDeque = blockingDeque;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(1000);
            blockingDeque.take();
            System.out.println("第1次取出"+blockingDeque.size());
            blockingDeque.take();
            System.out.println("第2次取出"+blockingDeque.size());
            blockingDeque.take();
            System.out.println("第3次取出"+blockingDeque.size());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
