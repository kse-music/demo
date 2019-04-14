package com.hiekn.demo.test.java.concurrent;

/**
 * describe about this class
 *
 * @author: DingHao
 * @date: 2019/4/14 23:23
 */
public class SyncDemo {

    public int simpleMethod(){
        int x = 13;
        int y = 14;
        int z = x + y;
        return z;
    }

    public void syncBlock() {
        synchronized (this) {
            System.out.println("hello block");
        }
    }

    public synchronized void syncMethod() {
        System.out.println("hello method");
    }

}
