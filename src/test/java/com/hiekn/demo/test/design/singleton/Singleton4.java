package com.hiekn.demo.test.design.singleton;

public class Singleton4 {
    //登记式/静态内部类,利用了 classloader 机制来保证初始化 instance 时只有一个线程
    private static class SingletonHolder {
        private static final Singleton4 INSTANCE = new Singleton4();
    }
    private Singleton4 (){}
    public static final Singleton4 getInstance() {
        return SingletonHolder.INSTANCE;
    }
}
