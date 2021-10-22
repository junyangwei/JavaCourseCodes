package java0.conc0302.lock;

import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.ReentrantLock;

public class TestFair {
    public static volatile int race = 0;
    /**
     * 可重入锁 + 公平锁
     * 改成false会好100倍
     */
    public static ReentrantLock lock = new ReentrantLock(true);
    private static final int THREADS_COUNT = 10;

    public static void increase(){
        lock.lock();
        // 变量自增操作
        race++;
        lock.unlock();
    }

    public static void main(String[]args){
        int count = Thread.activeCount();
        long now = System.currentTimeMillis();
        System.out.println(count);

        AtomicReference<Thread> sign = new AtomicReference<>();
        // 定义10个线程
        Thread[]threads = new Thread[THREADS_COUNT];

        for(int i = 0; i < THREADS_COUNT; i++){
            threads[i] = new Thread(() -> {
                for(int i1 = 0; i1 < 100000; i1++){
                    increase();
                }
            });
            threads[i].start();
        }
        //等待所有累加线程都结束
        while(Thread.activeCount() > count) {
            Thread.yield();
        }
        System.out.println("race = " + race);
        System.out.println(lock.getClass().getName() + " ts = "+ (System.currentTimeMillis() - now));
    }
}

