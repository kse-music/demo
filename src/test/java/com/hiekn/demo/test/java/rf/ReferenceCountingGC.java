package com.hiekn.demo.test.java.rf;

public class ReferenceCountingGC {

    private Object instance;

    private static final int _1MB = 1024 *1024;

    private byte[] bigSize = new byte[2 * _1MB];


    public static void main(String[] args) {
        ReferenceCountingGC objA = new ReferenceCountingGC();
        ReferenceCountingGC objB = new ReferenceCountingGC();
        objA.instance = objB;
        objB.instance = objA;

        objA = null;
        objB = null;
        System.gc();
    }
}

