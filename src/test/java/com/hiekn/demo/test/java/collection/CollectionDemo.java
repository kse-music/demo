package com.hiekn.demo.test.java.collection;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.hiekn.demo.test.TestBase;
import org.junit.Test;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

/**
 * List Map
 *
 * @author: DingHao
 * @date: 2019/1/23 21:25
 */
public class CollectionDemo extends TestBase {

    @Test
    public void concurrentMap(){
        Map<String, String> map = new ConcurrentHashMap<>();
        map.put("c","o");
    }


    @Test
    public void hashMap() {
        // Default initial capacity：16,thr=16*0.75=12,大于12则扩容

        Map<String, String> map = new HashMap<>();
        map.put("a", "one");
        map.put("b", "two");
        map.put("c", "three");
        map.put("d", "four");
        map.put("e", "five");
        map.remove("c");
        map.put("f", "six");
        map.forEach((k, v) -> System.out.println(k + " = " + v));

    }

    @Test
    public void copy() {
        List<Long> list = new CopyOnWriteArrayList<>();//适用于读多写极少的场景，fail-safe机制
        long start = System.currentTimeMillis();
        for (int i = 0; i < 20 * 10000; i++) {
            list.add(System.nanoTime());
        }

        System.out.println(System.currentTimeMillis()-start);
    }


    @Test
    public void treeMap() {
        Map<Integer, String> treeMap = new TreeMap();
        treeMap.put(55, "fifty-five");
        treeMap.put(56, "fifty-six");
        treeMap.put(57, "fifty-seven");
        treeMap.put(58, "fifty-eight");
        treeMap.put(83, "eighty-three");
        treeMap.remove(57);
        treeMap.put(59, "fifty-nine");
        System.out.println(treeMap.size());
    }

    class Key implements Comparable<Key>{

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

    @Test
    public void list() {
        // Default initial capacity：10,大于则扩容
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");
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

}
