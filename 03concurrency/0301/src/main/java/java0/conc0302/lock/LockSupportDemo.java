package java0.conc0302.lock;

import java.util.concurrent.locks.LockSupport;

public class LockSupportDemo {
    
    public static Object u = new Object();
    static ChangeObjectThread t1 = new ChangeObjectThread("t1");
    static ChangeObjectThread t2 = new ChangeObjectThread("t2");
    
    public static class ChangeObjectThread extends Thread {
        public ChangeObjectThread(String name) {
            super(name);
        }
        @Override public void run() {
            synchronized (u) {
                System.out.println("in " + getName());
                /*
                无限期挂起当前执行线程，最常见的唤醒方法有两种：
                    - 其它线程调用了 unpark 方法，参数为被挂起的线程
                    - 其它线程中断了被挂起的线程
                 */
                LockSupport.park();
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println(getName() + "被中断了");
                }
                System.out.println(getName() + "继续执行");
            }
        }
    }
    
    public static void main(String[] args) throws InterruptedException {
        t1.start();
        Thread.sleep(1000L);
        t2.start();
        Thread.sleep(3000L);
        // 中断被挂起的线程 t1，让它继续执行后面的逻辑
        t1.interrupt();
        // 主线程调用 unpark 方法，唤醒被挂起的线程 t2
        LockSupport.unpark(t2);
        // 主线程等待 t1，t2 线程执行完毕
        t1.join();
        t2.join();
    }
}