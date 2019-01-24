package com.hiekn.demo.test.design.singleton;

public class StaticInnerClassSingleton {
    //登记式/静态内部类,利用了 classloader 机制来保证初始化 instance 时只有一个线程
    private static class SingletonHolder {
        private static final StaticInnerClassSingleton INSTANCE = new StaticInnerClassSingleton();
    }
    private StaticInnerClassSingleton (){}
    public static final StaticInnerClassSingleton getInstance() {
        return SingletonHolder.INSTANCE;
    }
}
