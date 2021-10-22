package java0.conc0301.sync;

public class TestSetGet {

    public static void main(String[] args) throws Exception {

        final SetGet s = new SetGet();
        Thread t = new Thread(() -> {
            try {
                s.get();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        t.start();
        long start = System.currentTimeMillis();

        // 主线程调用了 SetGet 类对象的 set 方法，该方法通过 synchronized 修饰
        // 又因为新线程调用的 SetGet 类对象 get 方法，也通过 synchronized 修饰
        // 并且主线程和新线程都作用于同一个对象 s，因此会与新线程争抢锁
        s.set(10);
        System.out.println(" ... " + ( System.currentTimeMillis() - start ));

    }


    public static class SetGet {

        int a = 0;

        /**
         * set 和 get 方法都使用了 synchronized 修饰
         * 当两个不同的线程，对同一个SetGet类对象，同一时间，分别调用了set和get，也需要争抢锁
         * 这个时候 synchronized 的锁对象就是 SetGet 类对象，等价于 synchronized (this) {}
         */
        public synchronized void set(int v) throws Exception {
            System.out.println(Thread.currentThread().getName() +" setting " +v);
            Thread.sleep(5000);
            a = v;
            System.out.println(Thread.currentThread().getName() +" set " +v);
        }

        public synchronized int get() throws Exception {
            System.out.println(Thread.currentThread().getName() +" getting ");
            Thread.sleep(1000);
            System.out.println(Thread.currentThread().getName() + " get ");
            return a;
        }

    }
}
