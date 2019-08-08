package JUC锁.锁;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author:tx
 * @Date:2019/7/2
 * @Description:
 */
public class ReentrantLockTest01 {
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
    private volatile int size;
    private Lock lock;

    public Factory() {
        this.size = 0;
        this.lock = new ReentrantLock();
    }

    public void addPro(int val) {
        try {
            lock.lock();
            size += val;
            System.out.println(Thread.currentThread().getName() + ":" + size);
        } finally {
            lock.unlock();
        }
    }

    public void reCon(int val) {
        try {
            lock.lock();
            size -= val;
            System.out.println(Thread.currentThread().getName() + ":" + size);
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

