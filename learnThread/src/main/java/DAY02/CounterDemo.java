package DAY02;

public class CounterDemo {
    static int count = 0;   // 共享变量，两个线程都要改它

    static final Object lock = new Object();
    public static void main(String[] args) throws InterruptedException {
        Runnable task = () -> {
            for (int i = 0; i < 100_000; i++) {
                synchronized (lock) {
                    count++;
                }
            }
        };

        Thread t1 = new Thread(task);
        Thread t2 = new Thread(task);
        t1.start();
        t2.start();
        t1.join();    // 等两个线程都跑完
        t2.join();

        System.out.println("最终 count = " + count);
        System.out.println("期望值     = " + 200_000);
    }
}