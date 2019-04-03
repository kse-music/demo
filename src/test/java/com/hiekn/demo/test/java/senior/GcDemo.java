package com.hiekn.demo.test.java.senior;

import com.hiekn.demo.test.TestBase;
import org.junit.Test;

/**
 * describe about this class
 *
 * @author: DingHao
 * @date: 2018/12/29 21:19
 */
public class GcDemo extends TestBase {

    private static final int _1MB = 1024 * 1024;


    /**
     *  -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:+UseSerialGC
     *
     */
    @Test
    public void edgeFirst() {
       byte[] allocation1, allocation2, allocation3, allocation4;
        allocation1 = new byte[2 * _1MB];
        allocation2 = new byte[2 * _1MB];
        allocation3 = new byte[2 * _1MB];
        allocation4 = new byte[4 * _1MB];
    }

    /**
     *  -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:+UseSerialGC -XX:PretenureSizeThreshold=3145728
     *
     */
    @Test
    public void toOld() {
       byte[] allocation;
        allocation = new byte[4 * _1MB];
    }

    /**
     *  -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:+UseSerialGC -XX:MaxTenuringThreshold=1 -XX:+PrintTenuringDistribution
     *  Java虚拟机采用分代收集的思想来管理虚拟机内存。
     *  虚拟机给每个对象定义了一个对象年龄（Age）计数器。
     *  如果对象在Eden出生并且经过第一次Minor GC后仍然存活，并且能被Survivor容纳的话，将被移动到Survivor空间中，
     *  并且对象年龄增加到一定程度（默认15岁），就会被晋升到老年代。
     *  对晋升到老年代的对象的阈值可以通过-XX:MaxTenuringThreshold设置。
     *
     *
     *  虚拟并不是永远都要求对象年龄必须达到MaxTenuringThreshold才能晋升为老年代的，
     *  如果在Survivor的空间相同年龄的所有对象大小总和大于Survivor空间的一半时，年龄大于或者等于该年龄的对象直接进入老年代，
     *  无需要等到MaxTenuringThreshold中要求的年龄。
     */
    @Test
    public void sOld() {
        byte[] allocation1, allocation2, allocation3;
        allocation1 = new byte[_1MB / 4]; // 什么时候进入老年代决定于XX:MaxTenuringThreshold设置
        allocation2 = new byte[4 * _1MB];
        allocation3 = new byte[4 * _1MB];
        allocation3 = null;
        allocation3 = new byte[4 * _1MB];
    }

    /**
     *  -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:+UseSerialGC -XX:MaxTenuringThreshold=15 -XX:+PrintTenuringDistribution
     *
     *  虚拟并不是永远都要求对象年龄必须达到MaxTenuringThreshold才能晋升为老年代的，
     *  如果在Survivor的空间相同年龄的所有对象大小总和大于Survivor空间的一半时，年龄大于或者等于该年龄的对象直接进入老年代，
     *  无需要等到MaxTenuringThreshold中要求的年龄。
     */
    @Test
    public void dynamic() {
        byte[] allocation1, allocation2, allocation3, allocation4;
        allocation1 = new byte[_1MB / 4]; // allocation1+allocation2大于survivo空间一半
        allocation2 = new byte[_1MB / 4];
        allocation3 = new byte[4 * _1MB];
        allocation4 = new byte[4 * _1MB];
        allocation4 = null;
        allocation4 = new byte[4 * _1MB];
    }

}
