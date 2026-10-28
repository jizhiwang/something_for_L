package DAY01;

public class MyRunnable {
    public static void main(String[] args) {
        // 1. 定义任务（做什么）
        Runnable task = new Runnable() {
            @Override
            public void run() {
                System.out.println("我是 Runnable 任务，运行在：" + Thread.currentThread().getName());
            }
        };

        Thread t1 = new Thread(() -> {
            System.out.println("我是 Lambda 线程，运行在：" + Thread.currentThread().getName());
        });
        t1.start();

        // 2. 把任务交给线程执行（谁来做）
        Thread t = new Thread(task);
        t.start();
    }
}