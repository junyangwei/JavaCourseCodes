package java0.conc0303.collection;

import java.util.Comparator;
import java.util.TreeMap;

public class TreeMapDemo {
    
    public static void main(String[] args) {
        
        // 构造TreeMap类对象，并制定其排序顺序按照逆序排序
        TreeMap<Integer, String> map = new TreeMap<>(Comparator.reverseOrder());
        map.put(3, "val");
        map.put(2, "val");
        map.put(1, "val");
        map.put(5, "val");
        map.put(4, "val");
        // {5=val, 4=val, 3=val, 2=val, 1=val}
        System.out.println(map);

        // 构造TreeMap类对象，并制定其排序顺序按照顺序排序
        TreeMap<Integer, String> map1 = new TreeMap<>(Comparator.naturalOrder());
        map1.putAll(map);
        System.out.println(map1);
    }
    
}
