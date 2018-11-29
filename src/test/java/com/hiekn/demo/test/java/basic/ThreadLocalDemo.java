package com.hiekn.demo.test.java.basic;

import com.hiekn.demo.test.TestBase;
import org.junit.Test;

public class ThreadLocalDemo extends TestBase {

    ThreadLocal<Long> longLocal = ThreadLocal.withInitial(Thread.currentThread()::getId);
    ThreadLocal<String> stringLocal = ThreadLocal.withInitial(Thread.currentThread()::getName);

    public void set() {
        longLocal.set(Thread.currentThread().getId());
        stringLocal.set(Thread.currentThread().getName());
    }

    public long getLong() {
        return longLocal.get();
    }

    public String getString() {
        return stringLocal.get();
    }

    /**
     * ThreadLocal在每个线程中对该变量会创建一个副本，即每个线程内部都会有一个该变量，
     * 且在线程内部任何地方都可以使用，线程之间互不影响，
     * 这样一来就不存在线程安全问题，也不会严重影响程序执行性能。
     * <p>
     * 在进行get之前，必须先set，否则会报空指针异常；
     * 如果想在get之前不需要调用set就能正常访问的话，必须重写initialValue()方法。
     * <p>
     * 应用场景:
     * 最常见的ThreadLocal使用场景为 用来解决数据库连接、Session管理等。
     */
    @Test
    public void testThreadLocal() {
        final ThreadLocalDemo test = new ThreadLocalDemo();

//        test.set();
        System.out.println(test.getLong());
        System.out.println(test.getString());

        Thread thread1 = new Thread(() -> {
            test.set();
            System.out.println(test.getLong());
            System.out.println(test.getString());
        });
        thread1.start();
        try {
            thread1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(test.getLong());
        System.out.println(test.getString());
    }


}
