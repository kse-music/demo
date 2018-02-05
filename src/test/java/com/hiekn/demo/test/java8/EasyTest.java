package com.hiekn.demo.test.java8;

import com.google.common.collect.Lists;

import java.util.List;

public class EasyTest {

    public static void main(String[] args) {
        List<Integer> list = Lists.newArrayList(1,2);

        list.stream().forEach(System.out::println);
    }
}
