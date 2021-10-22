package java0.conc0301.op;

public class Join {
    
    public static void main(String[] args) {
        // 初始化 Object 类对象 oo，后面作为 synchronized 修饰的对象
        Object oo = new Object();

        // 初始化一个新的线程对象
        MyThread thread1 = new MyThread("thread1 -- ");
        //oo = thread1;

        // 将 oo 赋值到线程属性 Object 对象中，确保 run 方法中锁的对象和主线程是同一个
        thread1.setOo(oo);
        thread1.start();

        // 使用 oo 作为锁的对象，确保和新线程中 run 方法时同一个对象，使得两个线程需要争抢锁，保证线程安全
        // 还可以将 oo 改成 thread1，新线程 run 方法中改为 this，达到同样的目的
        synchronized (oo) {
            for (int i = 0; i < 100; i++) {
                if (i == 20) {
                    try {
                        oo.wait(0);
                        //thread1.join();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(Thread.currentThread().getName() + " -- " + i);
            }
        }
    }
    
}

class MyThread extends Thread {
    
    private String name;
    private Object oo;
    
    public void setOo(Object oo) {
        this.oo = oo;
    }
    
    public MyThread(String name) {
        this.name = name;
    }
    
    @Override
    public void run() {
        synchronized (oo) { // 这里用oo或this，效果不同
            for (int i = 0; i < 100; i++) {
                System.out.println(name + i);
            }
        }
    }
    
}