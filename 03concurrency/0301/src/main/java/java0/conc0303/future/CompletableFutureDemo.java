package java0.conc0303.future;

import java.util.concurrent.CompletableFuture;

public class CompletableFutureDemo {
    
    public static void main(String[] args){
        
        // 1.变换结果
        System.out.println("=====>1.变换结果");
        String result1 = CompletableFuture
                .supplyAsync(()-> "Hello ")
                .thenApplyAsync(v -> v + "world")
                .join();
        System.out.println(result1);
    
        // 2.消费
        CompletableFuture
                .supplyAsync(()-> "Hello ")
                .thenAccept(v -> {
                    System.out.println("=====>2.消费");
                    System.out.println("consumer: " + v);
                });
        
        // 3.组合
        System.out.println("=====>3.组合");
        String result3 = CompletableFuture
                .supplyAsync(() -> {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return "Hello";
                }).thenCombine(
                        CompletableFuture.supplyAsync(()->{
                            try {
                                Thread.sleep(2000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            return "world";
                        }),
                        (s1, s2)-> s1 + " " + s2
                ).join();
        System.out.println("thenCombine:"+result3);
    
        CompletableFuture
                .supplyAsync(() -> "Hello, java course.")
                .thenApply(String::toUpperCase)
                .thenCompose(s -> CompletableFuture.supplyAsync(s::toLowerCase))
                .thenAccept(v -> System.out.println("thenCompose:" + v));
        
        // 4.竞争
        System.out.println("=====>4.竞争（两者先到先得）");
        String result4 = CompletableFuture
                .supplyAsync(()->{
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return "Hi Boy";
                }).applyToEither(
                        CompletableFuture.supplyAsync(()->{
                            try {
                                // 可改变休眠时间查看结果
                                Thread.sleep(98);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            return "Hi Girl";
                        }),
                        (s)-> s
                ).join();
        System.out.println(result4);
        
        // 5.补偿异常
        System.out.println("=====>5.补偿异常");
        String result5 = CompletableFuture
                .supplyAsync(()->{
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if(true) {
                        throw new RuntimeException("exception test!");
                    }
        
                    return "Hi Boy";
                }).exceptionally(e -> {                // Fluent API
                    System.out.println(e.getMessage());
                    return "Hello world!";
                }).join();
        System.out.println(result5);
        
        
        
    }
    
}
