package DAY04;

import java.util.*;
import java.util.concurrent.*;

public class FutureBatchDemo {
    public static void main(String[] args) throws Exception {
        String[] partNumbers = {"PART-0", "PART-1", "PART-2", "PART-3", "PART-4",
                                "PART-5", "PART-6", "PART-7", "PART-8", "PART-9"};

        ExecutorService pool = Executors.newFixedThreadPool(5);
        long start = System.currentTimeMillis();

        // 第一步：批量提交，拿到 10 张"提货单"
        List<Future<String>> futures = new ArrayList<>();
        for (String pn : partNumbers) {
            Future<String> f = pool.submit(() -> queryFromDb(pn));   // Callable，有返回值
            futures.add(f);
        }

        // 第二步：依次取货
        List<String> results = new ArrayList<>();
        for (Future<String> f : futures) {
            results.add(f.get());   // 谁没好就等谁；但因为已经并发跑了，总耗时≈最慢的那个
        }

        long cost = System.currentTimeMillis() - start;
        System.out.println("收集到 " + results.size() + " 条结果，总耗时：" + cost + " ms");
        results.forEach(System.out::println);

        pool.shutdown();
    }

    // 模拟一次 DB 查询，耗时 100ms，返回一条"结果"
    static String queryFromDb(String partNumber) throws InterruptedException {
        Thread.sleep(100);
        return "结果[" + partNumber + "] 来自线程 " + Thread.currentThread().getName();
    }
}