package DAY01;

public class SpeedDemo {
    public static void main(String[] args) throws InterruptedException {
        // 任务：假装干一件耗时 1 秒的活
        Runnable work = () -> {
            try {
                Thread.sleep(1000);   // 睡 1 秒，模拟"查数据库/调接口"
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        // ========== 单线程：做 3 次 ==========
        long start1 = System.currentTimeMillis();
        for (int i = 0; i < 3; i++) {
            work.run();   // 注意：这里是普通调用，串行
        }
        System.out.println("单线程耗时：" + (System.currentTimeMillis() - start1) + " ms");

        // ========== 多线程：开 3 个线程同时做 ==========
        long start2 = System.currentTimeMillis();
        Thread t1 = new Thread(work);
        Thread t2 = new Thread(work);
        Thread t3 = new Thread(work);
        t1.start();
        t1.start();
        t2.start();
        t3.start();
//        t1.join(); t2.join(); t3.join();   // join：等这 3 个线程都执行完
        System.out.println("多线程耗时：" + (System.currentTimeMillis() - start2) + " ms");
    }
}