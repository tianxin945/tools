package 线程之间共享数据共享;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @Author tianxin
 * @Date 2019/2/13
 * @Description: 类似于转账
 */
public class demo01 {
    public static int data = 0;
    static Map<Thread,Integer> map = new HashMap<Thread, Integer>();
    public static void main(String[] args) {
        for (int i = 0; i < 2; i++) {
            new Thread(new Runnable() {
                public void run() {
                    int data = new Random().nextInt();
                    map.put(Thread.currentThread(),data);
                    new A().get();
                    new B().get();

                }
            }).start();
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



