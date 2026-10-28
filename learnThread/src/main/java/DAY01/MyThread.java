package DAY01;

public class MyThread extends Thread {
    /*
    * 创建线程的第二种方式：继承 Thread 类并重写 run() 方法
    * 1. 创建一个继承 Thread 类的子类
    * 2. 重写 run() 方法，编写线程执行的代码
    * 3. 创建子类对象
    * 4. 调用 start() 方法启动线程
    * */

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
