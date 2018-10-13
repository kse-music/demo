package com.hiekn.demo.test.frame.spring.parent;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ParentChildDemo {
    public static void main(String[] args) {
        ApplicationContext c = new AnnotationConfigApplicationContext("com.hiekn.demo.test.frame.spring.parent");
        ApplicationContext c2 = new AnnotationConfigApplicationContext("com.hiekn.demo.test.frame.spring.child");
        Object p1 = c.getBean("parentContext");
        ((AnnotationConfigApplicationContext) c2).setParent(c);
        Object p2 = c2.getBean("parentContext");
        ParentContext p3 = c2.getBean(ParentContext.class);
        Object p4 = c2.getBean("parentContext");
        System.out.println(p1);
        System.out.println(p2);
        System.out.println(p3);
        System.out.println(p4);
    }
}
