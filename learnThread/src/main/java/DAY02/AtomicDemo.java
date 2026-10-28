package DAY02;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicDemo {
    static AtomicInteger count = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException {
        Runnable task = () -> {
            for (int i = 0; i < 100_000; i++) {
                count.incrementAndGet();   // 原子自增，线程安全
            }
        };

        Thread t1 = new Thread(task);
        Thread t2 = new Thread(task);
        t1.start(); t2.start();
        t1.join(); t2.join();

        System.out.println("最终 count = " + count.get());   // 200000
    }
}