package java0.conc0303.collection;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

public class ConcurrentHashMapDemo {
    
    public static void main(String[] args) {
        demo1();
    }
    
    public static void demo1() {
        final Map<String, AtomicInteger> count = new ConcurrentHashMap<>();
        final CountDownLatch endLatch = new CountDownLatch(2);
        Runnable task = () -> {
            // 定义一个原子整型类变量
            AtomicInteger oldValue;

            // 循环5次，递增 oldValue 的整型数值属性
            for (int i = 0; i < 5; i++) {
                // 获取在 ConcurrentHashMap 中的 key 为 a 的数值，赋值给 oldValue
                oldValue = count.get("a");

                // 第一次获取时，若没有这个值，则初始化该值为 0
                if (null == oldValue) {
                    AtomicInteger zeroValue = new AtomicInteger(0);
                    oldValue = count.putIfAbsent("a", zeroValue);
                    if (null == oldValue) {
                        oldValue = zeroValue;
                    }
                }

                oldValue.incrementAndGet();
            }

            // 线程执行完毕后，调用 endLatch 对象的 countDown 方法，将等待数减 1，表示执行完毕
            endLatch.countDown();
        };
        new Thread(task).start();
        new Thread(task).start();
        
        try {
            // 设置主线程等待两个线程执行完毕（通过等待数减为0判断）
            endLatch.await();
            System.out.println(count);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
