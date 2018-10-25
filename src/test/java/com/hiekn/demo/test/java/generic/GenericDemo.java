package com.hiekn.demo.test.java.generic;

import java.util.ArrayList;
import java.util.List;

/**
 * 逆变和协变
 *       Java中String类型是继承自Object的，姑且记做String ≦ Object，表示String是Object的子类型，String的对象可以赋给Object的对象。
 *       而Object的数组类型Object[]，理解成是由Object构造出来的一种新的类型,可以认为是一种构造类型，记f(Object)，
 *       那么可以这么来描述协变和逆变：
 *        当A ≦ B时,如果有f(A) ≦ f(B),那么f叫做协变；
 *        当A ≦ B时,如果有f(B) ≦ f(A),那么f叫做逆变；
 *        如果上面两种关系都不成立则叫做不可变
 *
 *         JAVA中泛型是不变的
 */
public class GenericDemo {

    public static void main(String[] args) {

        //上界通配符： ? extends Number，适合读, 窄
        //下界通配符： ? super Number，适合写,  宽
        //   PECS: producer-extends, consumer-super.
        List<? super Number> list001 = new ArrayList<Number>();
        List<? extends Number> list002 = new ArrayList<Float>();

        list001.add(new Integer(3));
        list001.add(new Double(3));

    }


}
