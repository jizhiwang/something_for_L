package DAY03;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PoolDemo {
    public static void main(String[] args) {
        // 1. 创建一个固定 3 个线程的线程池
        ExecutorService pool = Executors.newFixedThreadPool(3);

        // 2. 提交任务
        pool.execute(() -> System.out.println("任务执行在：" + Thread.currentThread().getName()));

        // 3. 关闭线程池（重要，不关 JVM 不退出）
        pool.shutdown();
    }
}