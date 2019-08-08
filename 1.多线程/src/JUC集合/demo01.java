package JUC集合;

import org.junit.Test;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @author:tx
 * @Date:2019/7/21
 * @Description:
 */
public class demo01 {

    // CopyOnWriteArrayList,不能使用基本类型的泛型
    @Test
    public void fun1(){
//        CopyOnWriteArrayList<int> list = new CopyOnWriteArrayList<>();
    }

    // CopyOnWriteArraySet
    @Test
    public void fun2(){
        CopyOnWriteArraySet<Integer> set = new CopyOnWriteArraySet<>();

    }

    // CopyOnWriteArraySet
    @Test
    public void fun3(){
        CopyOnWriteArraySet<Integer> set = new CopyOnWriteArraySet<>();

    }

    // ConcurrentHashMap
    @Test
    public void fun4(){
        HashMap<String,Integer> map1 = new HashMap<>() ;
        Hashtable<String,Integer> map2 = new Hashtable<>();

        ConcurrentHashMap<Integer,Integer> map = new ConcurrentHashMap<>();

    }
    //
}
