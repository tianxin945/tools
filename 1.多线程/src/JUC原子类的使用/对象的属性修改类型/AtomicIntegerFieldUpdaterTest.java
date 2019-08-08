package JUC原子类的使用.对象的属性修改类型;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author:tx
 * @Date:2019/7/18
 * @Description:
 */
public class AtomicIntegerFieldUpdaterTest {
    @Test
    public void fun1() {
        AtomicReference demo = new AtomicReference();
        List<String> list = new ArrayList<>();
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        boolean b = checkListIsNull(list);
        if(b){
            System.out.println("为空");
        }
        System.out.println(b);
    }

    public boolean checkListIsNull(List<String> list) {
        boolean isNull = false;
        String temp = list.toString().replace("[", "")
                .replace("]", "")
                .replace(",", "")
                .replace(" ", "");
        if (temp != null&& "".equals(temp)) {
            return !isNull;
        }
        return isNull;
    }
    public static void main(String[] args) {
//        System.out.println(Thread.currentThread().getName());
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                System.out.println(Thread.currentThread().getName());
//            }
//        }).start();
        CopyOnWriteArrayList<Integer> list = new CopyOnWriteArrayList<Integer>();
        list.add(1);
        System.out.println(list);
    }
}
