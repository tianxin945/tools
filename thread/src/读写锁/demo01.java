package 读写锁;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @Author tianxin
 * @Date 2019/2/15
 * @Description: 缓存类
 */
public class demo01 {
    public static Map<String, String> map = new HashMap<>();
    private static ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    public static void main(String[] args) {
    }
    public static String getValue(String key) {
        String value ;
        readWriteLock.readLock().lock();
        try {
            value = map.get(key);
            if (value == null) {
                readWriteLock.readLock().unlock();
                readWriteLock.writeLock().lock();
                try {
                    if (value == null) {
                        value = "query";
                    }
                } finally {
                    readWriteLock.writeLock().unlock();
                }
                readWriteLock.readLock().lock();
            }
        } finally {
            readWriteLock.readLock().unlock();
        }
        return value;
    }
}
