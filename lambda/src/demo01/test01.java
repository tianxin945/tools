package demo01;

import demo01.model.Employee;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @Author tianxin
 * @Date 2019/2/12
 * @Description:
 */
public class test01 {

    @Test
    public void fun1() {
        List<Employee> list = new ArrayList<>();
        list.add(new Employee("TIANXIN1", 13));
        list.add(new Employee("TIANXIN2", 11));
        list.add(new Employee("TIANXIN3", 13));
        list.add(new Employee("TIANXIN4", 11));
        list.add(new Employee("TIANXIN5", 10));

        Collections.sort(list,(x,y) -> {
            if(x.getAge() == y.getAge()){
                return x.getName().compareTo(y.getName());
            }else {
                return Integer.compare(x.getAge(),y.getAge());
            }
        });

        for (Employee employee : list) {
            System.out.println(employee.getName() + "  " + employee.getAge());
        }
    }

}
