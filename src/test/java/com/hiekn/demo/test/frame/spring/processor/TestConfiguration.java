package com.hiekn.demo.test.frame.spring.processor;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;

import javax.annotation.PostConstruct;

@ComponentScan("com.hiekn.demo.test.frame.spring.processor")
public class TestConfiguration implements InitializingBean {

    @Value("${user.home}")
    private String home;

    public void setHome(String home) {
        this.home = home;
    }

    public TestConfiguration(){
        System.out.println("Constructor invoke");
    }

    @PostConstruct
    private void init(){
        System.out.println("@PostConstruct");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("afterPropertiesSet");
    }

    public String test(){
        System.out.println(home);
        return "test method return value";
    }

}