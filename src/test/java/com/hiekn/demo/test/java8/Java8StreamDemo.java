package com.hiekn.demo.test.java8;

import java.util.stream.Stream;

/**
 * 流的操作
 * 当把一个数据结构包装成 Stream 后，就要开始对里面的元素进行各类操作了。常见的操作可以归类如下。
 *
 * Intermediate：
 * map (mapToInt, flatMap 等)、 filter、 distinct、 sorted、 peek、 limit、 skip、 parallel、 sequential、 unordered
 *
 * Terminal：
 * forEach、 forEachOrdered、 toArray、 reduce、 collect、 min、 max、 count、 anyMatch、 allMatch、 noneMatch、 findFirst、 findAny、 iterator
 *
 * Short-circuiting：
 * anyMatch、 allMatch、 noneMatch、 findFirst、 findAny、 limit
 *
 */
public class Java8StreamDemo {

    public static void main(String[] args) {

        Stream.iterate(0, n -> n + 3).limit(10).forEach(x -> System.out.print(x + " "));


    }
}
