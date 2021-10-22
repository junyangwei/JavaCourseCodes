package java0.conc0303.future;

import java.util.Random;
import java.util.concurrent.*;

public class FutureDemo1 {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newCachedThreadPool();
        Future<Integer> result = executor.submit(() -> new Random().nextInt());
        executor.shutdown();
        try {
            System.out.println("result:" + result.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}