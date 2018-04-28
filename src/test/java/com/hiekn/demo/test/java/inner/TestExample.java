package com.hiekn.demo.test.java.inner;

/*
 内部类可以很好的实现隐藏
 一般的非内部类，是不允许有 private 与protected权限的，但内部类可以
 */
public class TestExample {

    public static void main(String args[]) {
        Example a=new Example();

        InterfaceTest a1 = a.getIn();
        a1.test();

    }

}
