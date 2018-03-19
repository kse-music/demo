package com.hiekn.demo.test.frame.spring;

public class TestBean {

    public void sayHello(){
        System.out.println("TestBean sayHello...");
    }

    public void start(){
        System.out.println("TestBean 初始化。。。");
    }

    public void cleanUp(){
        System.out.println("TestBean 销毁。。。");
    }
}
