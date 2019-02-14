package 线程之间共享数据共享;

import org.junit.Test;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.Random;

/**
 * @Author tianxin
 * @Date 2019/2/13
 * @Description: 类似于转账
 */
public class demo01 {
    public static int data = 0;
    static Map<Thread,Integer> map = new Hashtable<>();
    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 2; i++) {
            new Thread(new Runnable() {
                public void run() {
                    int data = new Random().nextInt();
                    map.put(Thread.currentThread(),data);
                    System.out.println(Thread.currentThread().getName()+": "+data);
                    new A().get();
                    new B().get();

                }
            }).start();
        }
        Thread.sleep(1000);
        for (Thread thread : map.keySet()) {
            System.out.println(thread+" "+map.get(thread));

        }
    }
    static class A {
        public static void get() {
            System.out.println(Thread.currentThread().getName() + " : " + map.get(Thread.currentThread()));
        }
    }

    static class B {
        public static void get() {
            System.out.println(Thread.currentThread().getName() + " : " + map.get(Thread.currentThread()));
        }
    }
}



