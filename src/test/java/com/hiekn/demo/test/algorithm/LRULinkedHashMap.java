package com.hiekn.demo.test.algorithm;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 最近最少使用
 *
 * @author: DingHao
 * @date: 2019/4/2 10:26
 */
public class LRULinkedHashMap<K,V> extends LinkedHashMap<K,V>{

    private int size;

    public LRULinkedHashMap(int initialCapacity) {
        this(initialCapacity, 0.75f);
    }

    public LRULinkedHashMap(int initialCapacity, float loadFactor) {
        super(initialCapacity, loadFactor,true);
        this.size = initialCapacity;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return size < size();
    }
}
