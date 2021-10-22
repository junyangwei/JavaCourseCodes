
package java0.conc0302.atomic;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SyncCount {

    private int num = 0;

    private Lock lock = new ReentrantLock(true);

    /**
     * 显示的使用 Lock 来加锁和解锁
     */
    public int add() {
        try {
            lock.lock();
            return num++;
        } finally {
            lock.unlock();
        }
    }

    public int getNum() {
        return num;
    }
}
