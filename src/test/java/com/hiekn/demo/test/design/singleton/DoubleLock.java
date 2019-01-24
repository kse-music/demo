package com.hiekn.demo.test.design.singleton;

/**
 * volatile 防止指令重排序    可见性
 */
public class DoubleLock {
    //双检验锁，线程安全
    private volatile static DoubleLock instance;

    private DoubleLock(){}

    public static DoubleLock getInstance() {
        if (instance == null) {
            synchronized(DoubleLock.class){
                if (instance == null) {
                    instance = new DoubleLock();
                }
            }
        }
        return instance;
    }

}
