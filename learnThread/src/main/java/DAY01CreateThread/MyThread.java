package DAY01CreateThread;

public class MyThread extends Thread {

    @Override
    public void run() {
        System.out.println("我是继承 Thread 的线程：" + Thread.currentThread().getName());
    }
    // 使用
    public static class Demo1 {
        public static void main(String[] args) {
            MyThread t = new MyThread();
            t.start();   // ★ 启动线程，不是 run()！
        }
    }
}
