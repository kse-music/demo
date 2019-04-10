package com.hiekn.demo.test.study.spring.basic;

import javax.annotation.PostConstruct;

public class TestBean {

    private String name = "test";

    @PostConstruct
    private void init(){
        System.out.println("PostConstruct");
    }

    public TestBean() {
        System.out.println("TestBean Constructor");
    }

    public void start(){
        System.out.println("TestBean initMethod。。。");
    }

    public void cleanUp(){
        System.out.println("TestBean destroyMethod。。。");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
