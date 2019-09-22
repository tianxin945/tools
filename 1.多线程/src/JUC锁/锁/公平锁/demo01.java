package JUC锁.锁.公平锁;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author:tx
 * @Date:2019/9/3
 * @Description:
 */
public class demo01 {
    public static void main(String[] args) {
        ReentrantLock reentrantLock = new ReentrantLock(true);
    }
}
