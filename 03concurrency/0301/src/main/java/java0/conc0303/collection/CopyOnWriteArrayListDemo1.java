package java0.conc0303.collection;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class CopyOnWriteArrayListDemo1 {
    private static final int THREAD_POOL_MAX_NUM = 10;
    private List<String> mList = new ArrayList<String>();  // ArrayList 无法运行
    //private List<String> mList = new CopyOnWriteArrayList<>();
    
    public static void main(String args[]) {
        new CopyOnWriteArrayListDemo1().start();
    }
    
    private void initData() {
        for (int i = 0; i <= THREAD_POOL_MAX_NUM; i++) {
            this.mList.add("...... Line " + (i + 1) + " ......");
        }
    }
    
    private void start() {
        // 初始化 mList 对象，为其构造数据
        initData();
        // 构建固定大小值为10的线程池
        ExecutorService service = Executors.newFixedThreadPool(THREAD_POOL_MAX_NUM);

        // 循环十次，将一读，一写的任务交给线程池来执行
        // 和CopyOnWriteArrayListDemo一样，如果集合不能保证读和写的线程安全，将会报错：java.util.ConcurrentModificationException
        for (int i = 0; i < THREAD_POOL_MAX_NUM; i++) {
            service.execute(new ListReader(this.mList));
            service.execute(new ListWriter(this.mList, i));
        }
        service.shutdown();
    }
    
    private class ListReader implements Runnable {
        private List<String> mList;
        
        public ListReader(List<String> list) {
            this.mList = list;
        }
        
        @Override
        public void run() {
            if (this.mList != null) {
                for (String str : this.mList) {
                    System.out.println(Thread.currentThread().getName() + " : " + str);
                }
            }
        }
    }
    
    private class ListWriter implements Runnable {
        private List<String> mList;
        private int mIndex;
        
        public ListWriter(List<String> list, int index) {
            this.mList = list;
            this.mIndex = index;
        }
        
        @Override
        public void run() {
            if (this.mList != null) {
                //this.mList.remove(this.mIndex);
                this.mList.add("...... add " + mIndex + " ......");
            }
        }
    }
}