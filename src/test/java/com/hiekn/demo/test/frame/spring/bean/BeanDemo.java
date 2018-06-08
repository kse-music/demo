package com.hiekn.demo.test.frame.spring.bean;

import com.hiekn.demo.test.frame.spring.DemoBean3;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class BeanDemo {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext("com.hiekn.demo.test.frame.spring.bean");
        DemoBean3 tb = context.getBean(DemoBean3.class);
        System.out.println(tb);
    }
}
