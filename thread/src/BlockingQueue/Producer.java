package src.BlockingQueue;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;

/**
 * @Author tianxin
 * @Date 2019/6/27
 * @Description:
 */
public class Producer implements Runnable {
    private BlockingQueue blockingDeque = null;

    public Producer(BlockingQueue blockingDeque) {
        this.blockingDeque = blockingDeque;
    }

    @Override
    public void run() {
        try {
            blockingDeque.put("1");
            System.out.println("第1次插入"+blockingDeque.size());
            Thread.sleep(1000);
            blockingDeque.put("2");
            System.out.println("第2次插入"+blockingDeque.size());
            Thread.sleep(1000);
            blockingDeque.put("3");
            System.out.println("第3次插入"+blockingDeque.size());

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
