package com.hiekn.demo.test.java.inner;

public class Callee extends MyIncrement implements IncrementAble {

    public void increment() {
        System.out.println("Callee");
    }

    public static void main(String[] args) {
        MyIncrement callee = new Callee();
        callee.increment();
    }

}
