
package java0.conc0301.sync;

public class Thread3 {
    class Inner {
        private void m4t1() {
            int i = 5;
            while (i-- > 0) {
                System.out.println(Thread.currentThread().getName() + " : Inner.m4t1()=" + i);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException ie) {
                }
            }
        }

        private void m4t2() {
            int i = 5;
            while (i-- > 0) {
                System.out.println(Thread.currentThread().getName() + " : Inner.m4t2()=" + i);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException ie) {
                }
            }
        }
    }

    private void m4t1(Inner inner) {
        // 使用对象锁
        synchronized (inner) {
            inner.m4t1();
        }
    }

    private void m4t2(Inner inner) {
        // m4t2 方法没有使用对象锁，因此可以让其他线程并行执行该方法
        inner.m4t2();
    }

    public static void main(String[] args) {
        final Thread3 myt3 = new Thread3();
        final Inner inner = myt3.new Inner();
        Thread t1 = new Thread(() -> myt3.m4t1(inner), "t1");
        Thread t2 = new Thread(() -> myt3.m4t2(inner), "t2");
        t1.start();
        t2.start();
    }
}
