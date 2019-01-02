package com.hiekn.demo.test.frame.spring.basic;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class TestBean {
    private String name = "test";

    @PostConstruct
    private void init(){
        System.out.println("init");
    }

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
