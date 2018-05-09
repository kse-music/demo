package com.hiekn.demo.test.frame.spring;

import org.springframework.stereotype.Component;

@Component
//@Scope("prototype")
public class TestBean {
    public TestBean() {
        System.out.println("TestBean Constructor");
    }

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
