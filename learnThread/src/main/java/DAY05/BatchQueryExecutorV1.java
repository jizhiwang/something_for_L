package DAY05;

import java.util.*;
import java.util.concurrent.*;
import java.util.function.Function;

public class BatchQueryExecutorV1 {

    public static <K, V> Map<K, V> batchQuery(List<K> keys, Function<K, V> queryFunction, int threadCount) throws Exception {

        ExecutorService pool = Executors.newFixedThreadPool(threadCount);
        try {
            // 第一步：全部提交，拿到提货单
            Map<K, Future<V>> futureMap = new LinkedHashMap<>();
            for (K key : keys) {
                Future<V> future = pool.submit(() -> queryFunction.apply(key));
                futureMap.put(key, future);
            }

            // 第二步：全部取货（先全 submit 再全 get，这是 Day 4 的铁律）
            Map<K, V> result = new LinkedHashMap<>();
            for (Map.Entry<K, Future<V>> entry : futureMap.entrySet()) {
                result.put(entry.getKey(), entry.getValue().get());
            }
            return result;
        } finally {
            pool.shutdown();   // 无论成功失败，都要关
        }
    }

    // ========== 测试 ==========
    public static void main(String[] args) throws Exception {
        List<String> partNumbers = Arrays.asList("PART-0", "PART-1", "PART-2", "PART-3", "PART-4", "PART-5", "PART-6", "PART-7", "PART-8", "PART-9");

        long start = System.currentTimeMillis();
        Map<String, String> result = batchQuery(partNumbers, pn -> fakeQuery(pn),   // 单个查询逻辑
                5);                    // 5 线程
        long cost = System.currentTimeMillis() - start;

        System.out.println("耗时：" + cost + " ms");
        result.forEach((k, v) -> System.out.println(k + " -> " + v));
    }

    static String fakeQuery(String pn) {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
        }
        return "配送需求[" + pn + "]";
    }
}