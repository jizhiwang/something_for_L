package DAY04;

import java.util.concurrent.*;

public class FutureDemo1 {
    public static void main(String[] args) throws Exception {
        ExecutorService pool = Executors.newFixedThreadPool(2);

        // 提交一个 Callable 任务（注意：submit 接受 Callable，不是 Runnable）
        Future<Integer> future = pool.submit(() -> {
            System.out.println("子线程开始计算，线程：" + Thread.currentThread().getName());
            Thread.sleep(1000);          // 模拟耗时计算
            return 1 + 1;                // ← 有返回值！
        });

        System.out.println("主线程提交完任务，继续干别的...");

        // get() 会阻塞，直到任务完成、结果返回
        Integer result = future.get();
        System.out.println("拿到结果：" + result);   // 2

        pool.shutdown();
    }
}