package 框架.ForkJoin;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;

/**
 * @author:tx
 * @Date:2019/7/24
 * @Description:
 */
public class demo01 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        Future<Integer> result = forkJoinPool.submit(new RecursiveTask<Integer>() {
            @Override
            protected Integer compute() {
//                new RecursiveTask<>().fork()
//                new RecursiveTask<>().join()
                return 1+1;
            }
        });
        System.out.println(result.get());
    }
}
