package 线程之间共享数据共享;

import java.util.Random;

/**
 * @Author tianxin
 * @Date 2019/2/13
 * @Description:
 */
public class demo02 {
    public static int data = 0;
    //static Map<Thread,Integer> map = new HashMap<Thread, Integer>();
    private static ThreadLocal<Integer> threadLocal = new ThreadLocal();

    public static void main(String[] args) {
        for (int i = 0; i < 2; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    int data = new Random().nextInt();
                    threadLocal.set(data);
                    new A().get();
                    new B().get();

                }
            }).start();
        }


    }

    static class A {
        public static void get() {
            System.out.println(Thread.currentThread().getName() + " : " + threadLocal.get());
        }
    }

    static class B {
        public static void get() {
            System.out.println(Thread.currentThread().getName() + " : " + threadLocal.get());
        }
    }
}
