package 线程之间共享数据共享;

/**
 * @Author tianxin
 * @Date 2019/2/14
 * @Description:
 */
public class demo03 {
    public static void main(String[] args) {

        sell sell = new sell();
        new Thread(sell).start();
        new Thread(sell).start();
    }
    static class sell implements Runnable {
        private int total = 100;

        @Override
        public synchronized void run() {
            while (total > 0) {
                total--;
                System.out.println(total);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
