package DAY03;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ReuseDemo {
    public static void main(String[] args) {
//        // 只开 2 个线程的池子
//        ExecutorService pool = Executors.newFixedThreadPool(2);
//
//        // 提交 6 个任务
//        for (int i = 1; i <= 6; i++) {
//            final int taskId = i;
//            pool.execute(() -> {
//                String threadName = Thread.currentThread().getName();
//                System.out.println("任务" + taskId + " 由 " + threadName + " 执行");
//                try {
//                    Thread.sleep(200);   // 模拟任务耗时
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            });
//        }
//
//        pool.shutdown();
        ExecutorService pool = Executors.newFixedThreadPool(2);

        for (int i = 1; i <= 6; i++)
        {
            final int taskId = i;
            pool.execute(() -> {
                String threadName = Thread.currentThread().getName();
                System.out.println("任务" + taskId + " 由 " + threadName + " 执行");
                try {
                    Thread.sleep(200);   // 模拟任务耗时
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        pool.shutdown();
    }
}