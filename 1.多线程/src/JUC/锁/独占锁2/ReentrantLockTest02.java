package JUC.锁.独占锁2;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author:tx
 * @Date:2019/7/2
 * @Description:
 */
public class ReentrantLockTest02 {
    public static void main(String[] args) {
        Factory factory = new Factory();
        pp con = new pp(factory);
        Pro pro = new Pro(factory);
        pro.addPro(200);
        pro.addPro(100);
        con.reCon(300);
        con.reCon(100);
        pro.addPro(200);
    }
}

class Factory {
    private int maxSize;
    private volatile int size;
    private Lock lock;
    private Condition fullCondition; // 仓库满了的条件
    private Condition emptyCondition; // 仓库空了的条件


    public Factory() {
        this.size = 0;
        this.lock = new ReentrantLock();
        this.maxSize = 200;
        this.fullCondition = lock.newCondition();
        this.emptyCondition = lock.newCondition();
    }

    public void addPro(int val) {
        lock.lock();
        try {
            int temp = val;
            while (temp > 0) {
                while (size >= maxSize) {
                    fullCondition.await(); // 添加仓库的进程就开始等待
                }
                int inc = temp + size > maxSize ? maxSize-size : temp;
                size += inc;
                temp -= inc;
                System.out.println(Thread.currentThread().getName() + ":" + size);
                // 唤醒消费者的线程
                emptyCondition.signal();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void reCon(int val) {
        try {
            lock.lock();
            int temp = val;
            while (temp > 0) {
                while (size <= 0) {
                    emptyCondition.await();
                }
                int inc = temp < size ? temp : size;
                size -= inc;
                temp -= inc;

            }
            System.out.println(Thread.currentThread().getName() + ":" + size);
            fullCondition.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}

class pp {
    private Factory factory;

    public pp(Factory factory) {
        this.factory = factory;
    }

    public void reCon(int val) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                factory.reCon(val);
            }
        }).start();
    }
}


class Pro {
    private Factory factory;

    public Pro(Factory factory) {
        this.factory = factory;
    }

    public void addPro(int val) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                factory.addPro(val);
            }
        }).start();
    }
}

