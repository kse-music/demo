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
