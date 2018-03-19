package com.hiekn.demo.test.frame.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.context.ContextLoader;

public class SpringDemo {
    public static void main(String[] args) {

        //@Configuration注解的spring容器加载方式，用AnnotationConfigApplicationContext替换ClassPathXmlApplicationContext
        ApplicationContext context = new AnnotationConfigApplicationContext(TestConfiguration.class);
        System.out.println(context);
        System.out.println(SpringManager.getApplicationContext());

        //如果加载spring-context.xml文件：
//        ApplicationContext context = new ClassPathXmlApplicationContext("spring-context.xml");

        TestBean tb = context.getBean("testBean",TestBean.class);
        System.out.println(tb);
        TestBean tb2 = context.getBean("testBean",TestBean.class);
        System.out.println(tb2);
        tb.sayHello();
    }
}

