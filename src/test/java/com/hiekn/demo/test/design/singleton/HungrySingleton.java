package com.hiekn.demo.test.design.singleton;

public class HungrySingleton {
    //饿汉式,类加载就初始化
    private static HungrySingleton instance = new HungrySingleton();
    private HungrySingleton (){}
    public static HungrySingleton getInstance() {
        return instance;
    }
}
