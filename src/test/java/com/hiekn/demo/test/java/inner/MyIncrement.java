package com.hiekn.demo.test.java.inner;

public class MyIncrement {
    public void increment() {
        System.out.println("MyIncrement");
    }

    static void f(MyIncrement f) {
        f.increment();
    }
}
