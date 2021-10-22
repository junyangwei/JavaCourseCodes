
package java0.conc0302.threadpool;

import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class ExecutorServiceDemo {

    public static void main(String[] args) {
        // 创建一个线程池，可以安排命令在指定延迟时间 / 定期执行
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(16);
        try {
            String str = executorService.submit(new Callable<String>() {
                @Override
                public String call() throws Exception {
                    return "I am a task, which submited by the so called laoda, and run by those anonymous workers";
                }
            }).get();

            System.out.println("str=" + str);
        } catch (Exception e) {
            e.printStackTrace();
        }

        executorService.shutdown();
        System.out.println("Main Thread End!");
    }

}
