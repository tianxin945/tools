import java.util.Random;

/**
 * @author:tx
 * @Date:2019/7/20
 * @Description:
 */
public class demo {
    public static int data = 0;

    public static ThreadLocal<Integer> threadLocal = new ThreadLocal<>();

    public static void main(String[] args) {

        for (int i = 0; i < 2; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    threadLocal.set(new Random().nextInt(10));
                    new A().get();
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
//                    new B().get();
                }
            }).start();
        }
    }

    static class A {
        public static void get() {
            System.out.println(Thread.currentThread().getName() + "  " + threadLocal.get());
        }
    }

//    static class B {
//        public void get() {
//            System.out.println(Thread.currentThread().getName() + "  " + threadLocal.get());
//        }
//    }

}




