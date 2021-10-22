package java0.conc0303.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class StreamParallelDemo {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>(10000);
        IntStream.range(1, 10000).forEach(i -> list.add(i));
        BlockingQueue<Long> blockingQueue = new LinkedBlockingQueue(10000);
        // Stream 的操作，可以简单的在原本单线程处理的代码上，通过加一个 .parallel
        // 就把这个操作变成一个并行多线程的线程池来处理的这样的一个过程
        List<Long> longList = list.stream().parallel()
                .map(i -> i.longValue())
                .sorted()
                .collect(Collectors.toList());
//      // 串行，单线程
//      longList.stream().forEach(i -> {
        // 并行，默认使用CPU * 2个线程
        longList.stream().parallel().forEach(i -> {
            try {
                blockingQueue.put(i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        System.out.println("blockingQueue:" + blockingQueue.toString());

        /*
        我自己执行的时候，发现加了 .parallel 耗时反而增多了，可能原因是构造线程池需要
        耗费一定的时间导致的；因此，当处理的数量不多时，直接用单线程的方式处理也许会更好
         */
    }
    
    
}
