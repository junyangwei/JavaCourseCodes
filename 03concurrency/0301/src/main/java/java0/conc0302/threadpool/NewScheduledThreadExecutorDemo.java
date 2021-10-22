
package java0.conc0302.threadpool;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class NewScheduledThreadExecutorDemo {

    public static void main(String[] args) {
        // 创建一个线程池，可以安排命令在指定延迟时间 / 定期执行
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(16);

        for (int i = 0; i < 100; i++) {
            final int no = i;
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println("start:" + no);
                        Thread.sleep(1000L);
                        System.out.println("end:" + no);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };
            // 10s后执行
            executorService.schedule(runnable, 10, TimeUnit.SECONDS);
        }
        executorService.shutdown();
        System.out.println("Main Thread End!");


    }

}
