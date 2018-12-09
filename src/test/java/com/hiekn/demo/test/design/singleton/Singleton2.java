package com.hiekn.demo.test.design.singleton;

public class Singleton2 {
    //懒汉式,线程不安全
    private static Singleton2 instance;
    private Singleton2 (){}
    public static synchronized Singleton2 getInstance() {
        if (instance == null) {
            instance = new Singleton2();
        }
        return instance;
    }
}
