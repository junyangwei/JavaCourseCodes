package java0.conc0303.tool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class SemaphoreDemo2 {
        
        private final static int threadCount = 20;
        
        public static void main(String[] args) throws Exception {
            
            ExecutorService exec = Executors.newCachedThreadPool();

            // 使用信号量控制同一时间并发线程数目为5
            final Semaphore semaphore = new Semaphore(5);
            
            for (int i = 0; i < threadCount; i++) {
                final int threadNum = i;
                exec.execute(() -> {
                    try {
                        // 获取全部许可，退化成串行执行
                        // 相当于阻止3个线程运行，除去主线程外，就允许线程池中一个线程运行
                        // 可将这个值改为2，查看一下结果
                        semaphore.acquire(3);
                        test(threadNum);
                        // 释放多个许可
                        semaphore.release(3);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
            }
            exec.shutdown();
        }
        
        private static void test(int threadNum) throws Exception {
            System.out.println("id:"+threadNum+","+Thread.currentThread().getName());
            Thread.sleep(1000);
        }
    }
