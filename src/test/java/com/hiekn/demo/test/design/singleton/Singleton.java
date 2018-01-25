package com.hiekn.demo.test.design.singleton;

public class Singleton {
    //饿汉式,类加载就初始化
    private static Singleton instance = new Singleton();
    private Singleton (){}
    public static Singleton getInstance() {
        return instance;
    }
}
