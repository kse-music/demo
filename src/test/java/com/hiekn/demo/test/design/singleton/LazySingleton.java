package com.hiekn.demo.test.design.singleton;

public class LazySingleton {
    //懒汉式,线程不安全
    private static LazySingleton instance;
    private LazySingleton (){}
    public static synchronized LazySingleton getInstance() {
        if (instance == null) {
            instance = new LazySingleton();
        }
        return instance;
    }
}
