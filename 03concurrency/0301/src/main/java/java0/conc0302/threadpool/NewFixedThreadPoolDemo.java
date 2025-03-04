
package java0.conc0302.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NewFixedThreadPoolDemo {

    public static void main(String[] args) {
        // 构造一个固定大小为16的线程池
        ExecutorService executorService = Executors.newFixedThreadPool(16);
        for (int i = 0; i < 100; i++) {
            final int no = i;
            executorService.execute(() -> {
                try {
                    System.out.println("start:" + no);
                    Thread.sleep(1000L);
                    System.out.println("end:" + no);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        executorService.shutdown();
        System.out.println("Main Thread End!");
    }

}
