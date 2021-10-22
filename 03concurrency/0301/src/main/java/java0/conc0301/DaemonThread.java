package java0.conc0301;

public class DaemonThread {
    
    public static void main(String[] args) throws InterruptedException {
        Runnable task = () -> {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Thread t = Thread.currentThread();
            System.out.println("当前线程:" + t.getName());
        };
        Thread thread = new Thread(task);
        thread.setName("test-thread-1");

        // 设置新线程为守护线程，当主线程退出时，若只剩下守护线程，JVM 会直接结束该进程
        // 因此新线程可能还没执行完就结束了，解决方法如下
        // 方法一：不设置新线程为守护线程，方法二：休眠主线程几秒，为新线程预留一点执行时间
        thread.setDaemon(true);
        thread.start();

        //Thread.sleep(5500);
    }
    
    
}
