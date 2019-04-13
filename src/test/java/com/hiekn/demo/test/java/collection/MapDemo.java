package com.hiekn.demo.test.java.collection;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.hiekn.demo.test.TestBase;
import org.junit.Test;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * description about this class
 *
 * @author DingHao
 * @date 2019/4/13 16:43
 */
public class MapDemo extends TestBase {

    private static HashMap<Long, EasyCoding> map = new HashMap<>();

    @Test
    public void treeMap() {
        Map<Key, String> map = new TreeMap();
        map.put(new Key(),"value one");
        map.put(new Key(),"value two");
        map.forEach((k,v) -> System.out.println(k+" = "+v));

        TreeMap<Integer, String> treeMap = new TreeMap<>();
        treeMap.put(55, "fifty-five");
        treeMap.put(56, "fifty-six");
        treeMap.put(57, "fifty-seven");
        treeMap.put(58, "fifty-eight");
        treeMap.put(83, "eighty-three");
        treeMap.remove(57);
        treeMap.put(59, "fifty-nine");
        System.out.println(treeMap.size());

        Map.Entry<Integer, String> integerStringEntry = treeMap.lowerEntry(58);
        Integer integer2 = treeMap.lowerKey(58);
        Map.Entry<Integer, String> integerStringEntry1 = treeMap.floorEntry(58);
        Integer integer3 = treeMap.floorKey(58);
        NavigableMap<Integer, String> integerStringNavigableMap = treeMap.descendingMap();
        NavigableSet<Integer> integers = treeMap.navigableKeySet();
        NavigableSet<Integer> integers2 = treeMap.descendingKeySet();

        SortedMap<Integer, String> integerStringSortedMap = treeMap.subMap(56, 59);
        SortedMap<Integer, String> integerStringSortedMap1 = treeMap.headMap(57);
        SortedMap<Integer, String> integerStringSortedMap2 = treeMap.tailMap(57);
        Integer integer1 = treeMap.firstKey();
        Integer integer = treeMap.lastKey();

        Collections.synchronizedMap(treeMap);//在方法操作上添加同步块
    }

    @Test
    public void hashMap() {
        // Default initial capacity：16,thr=16*0.75=12,大于12则扩容
        //initialCapacity = (需要存储的元素个数 / 负载因子) + 1

        for (int i = 0; i < 100000; i++) {
            new Thread(() -> {
                map.put(System.nanoTime(),new EasyCoding());
            }).start();
        }

    }

    static class EasyCoding{}

    @Test
    public void concurrentMap() {
        Map<String, String> map = new ConcurrentHashMap<>();
        map.put("c", "o");
    }

    @Test
    public void group() {
        Map<String, Object> map1 = Maps.newHashMap();
        map1.put("room_id", "1");
        map1.put("name", "语文简体");
        Map<String, Object> map2 = Maps.newHashMap();
        map2.put("room_id", "1");
        map2.put("name", "语文繁体");
        List<Map<String, Object>> list = Lists.newArrayList(map1, map2);

        Map<String, Object> map = Maps.newHashMap();
        list.stream().collect(Collectors.groupingBy(m -> m.get("room_id"), Collectors.mapping(m -> m.get("name"), Collectors.toList()))).forEach((k, v) -> {
            map.put("room_id", k);
            map.put("names", v);
        });
        System.out.println(map);
    }

    static class Key implements Comparable<Key> {

        @Override
        public int compareTo(Key o) {
            return -1;
        }

        @Override
        public int hashCode() {
            return 1;
        }

        @Override
        public boolean equals(Object obj) {
            return true;
        }
    }

}
