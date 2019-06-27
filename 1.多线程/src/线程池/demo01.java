package 线程池;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @Author tianxin
 * @Date 2019/2/14
 * @Description: 线程定时器
 */
public class demo01 {
    public static void main(String[] args) {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(3);
        scheduledExecutorService.scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
                System.out.println("111111");
            }
        }, 1, 2, TimeUnit.SECONDS);


    }
}
