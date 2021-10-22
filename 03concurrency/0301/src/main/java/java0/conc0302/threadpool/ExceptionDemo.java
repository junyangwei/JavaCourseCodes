package java0.conc0302.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ExceptionDemo {
    
    public static void main(String[] args) {
        // 构建线程执行器，采用构造固定线程池的静态方法
        ExecutorService executorService = Executors.newFixedThreadPool(1);
    
        try {
            // 接收线程池执行结果（调用submit方法在主线程中，也能捕获线程池中抛出的异常）
            Future<Double> future = executorService.submit(() -> {
                throw new RuntimeException("executorService.submit()");
            });
    
            double b = future.get();
            System.out.println(b);
            
        } catch (Exception ex) {
            System.out.println("catch submit");
            ex.printStackTrace();
        }

        try {
            // 调用execute方法，在主线程中捕获不到线程池中抛出的异常
            executorService.execute(() -> {
                throw new RuntimeException("executorService.execute()");
            });
        } catch (Exception ex) {
            System.out.println("catch execute");
            ex.printStackTrace();
        }

        executorService.shutdown();
        System.out.println("Main Thread End!");
    }
    
}
