package com.hiekn.demo.test.java.collection;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.hiekn.demo.test.TestBase;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

/**
 * List Map
 *
 * @author: DingHao
 * @date: 2019/1/23 21:25
 */
public class CollectionDemo extends TestBase {


    @Test
    public void hashMap() {
        Map<Integer, String> map = new HashMap<>();
        map.put(1, "one");
        map.put(2, "two");
        map.put(3, "three");
        map.put(4, "four");
        map.put(5, "five");
        map.remove(3);
        map.put(6, "six");
        map.forEach((k, v) -> System.out.println(k + " = " + v));

    }

    @Test
    public void treeMap() {
        TreeMap<Integer, String> map = new TreeMap();
        map.put(55, "fifty-five");
        map.put(56, "fifty-six");
        map.put(57, "fifty-seven");
        map.put(58, "fifty-eight");
        map.put(83, "eighty-three");
        map.remove(57);
        map.put(59, "fifty-nine");
        map.forEach((k, v) -> System.out.println(k + " = " + v));

    }

    @Test
    public void list() {
        List<String> list = new ArrayList<>();// Default initial capacity：10
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
