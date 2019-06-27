package src;

import org.junit.Test;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * @Author tianxin
 * @Date 2019/3/21
 * @Description:
 */
public class et {
    @Test
    public void fun12() {
        List<User> list = new ArrayList<>();
        list.add(new User("tianxin", 18, 11));
        list.add(new User("tianxin", 17, 12));
        list.add(new User("tianxin", 16, 11));


        for (User user : list.stream().filter(distinctByKey(b -> b.getName())).collect(Collectors.toList())) {
            System.out.println(user.getName());
        }
    }

    private static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
        Map<Object, Boolean> seen = new ConcurrentHashMap<>();
        return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }

}
