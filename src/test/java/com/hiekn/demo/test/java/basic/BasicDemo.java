package com.hiekn.demo.test.java.basic;

public class BasicDemo {

    public static void main(String[] args){
        Integer a = 128;
        Integer b = 128;
        Integer c = Integer.valueOf(127);
        Integer d = Integer.valueOf(127);
        System.out.println(a == b);
        System.out.println(a.equals(b));
        System.out.println(c == d);
    }


}
