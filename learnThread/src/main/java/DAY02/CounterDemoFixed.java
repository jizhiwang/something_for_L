package DAY02;

public class CounterDemoFixed {
    static int count = 0;

    // synchronized 修饰静态方法：锁的是 CounterDemoFixed.class 这个类对象
    static synchronized void increment() {
        count++;
    }

    public static void main(String[] args) throws InterruptedException {
        Runnable task = () -> {
            for (int i = 0; i < 100_000; i++) {
                increment();    // 每次都走加锁方法
            }
        };

        Thread t1 = new Thread(task);
        Thread t2 = new Thread(task);
        t1.start(); t2.start();
        t1.join(); t2.join();

        System.out.println("最终 count = " + count);   // 稳定输出 200000
    }
}