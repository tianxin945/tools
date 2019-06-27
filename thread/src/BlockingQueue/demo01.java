package src.BlockingQueue;

import org.junit.Test;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;

/**
 * @Author tianxin
 * @Date 2019/6/27
 * @Description:
 */
public class demo01 {
    @Test
    public void fun1() throws InterruptedException {
//        BlockingDeque queue = new ArrayBlockingQueue(1024);
        BlockingQueue queue = new ArrayBlockingQueue(1024);
        Producer producer = new Producer(queue);
        Customer customer = new Customer(queue);

        new Thread(producer).start();
        new Thread(customer).start();

        Thread.sleep(4000);

    }
}
