package DAY03;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class BatchQueryDemo {
    public static void main(String[] args) throws InterruptedException {
        // 模拟 10 个"零件号"，每个都需要查一次数据库
        String[] partNumbers = new String[10];
        for (int i = 0; i < 10; i++) {
            partNumbers[i] = "PART-" + i;
        }

        // ========== 串行版 ==========
        long t1 = System.currentTimeMillis();
        for (String pn : partNumbers) {
            fakeQuery(pn);   // 每个查 100ms，10 个串起来约 1000ms
        }
        System.out.println("串行耗时：" + (System.currentTimeMillis() - t1) + " ms");

        // ========== 线程池版 ==========
        ExecutorService pool = Executors.newFixedThreadPool(5);
        long t2 = System.currentTimeMillis();
        CountDownLatch latch = new CountDownLatch(partNumbers.length);  // 计数器：10 个任务
        for (String pn : partNumbers) {
            pool.execute(() -> {
                try {
                    fakeQuery(pn);
                } finally {
                    latch.countDown();   // 每完成一个，计数 -1
                }
            });
        }
        latch.await();        // 主线程等：10 个任务全完成再往下
        pool.shutdown();
        System.out.println("线程池耗时：" + (System.currentTimeMillis() - t2) + " ms");
    }

    // 模拟一次数据库查询，耗时 100ms
    static void fakeQuery(String partNumber) {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("查询 " + partNumber + " 完成，线程：" + Thread.currentThread().getName());
    }
}