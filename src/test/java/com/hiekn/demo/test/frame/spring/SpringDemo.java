package com.hiekn.demo.test.frame.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SpringDemo {

    public static void main(String[] args) {

        //@Configuration注解的spring容器加载方式，用AnnotationConfigApplicationContext替换ClassPathXmlApplicationContext
//        ApplicationContext context = new AnnotationConfigApplicationContext("com.hiekn.demo.test.frame.spring");

        //如果加载spring-context.xml文件：
//        ApplicationContext context = new ClassPathXmlApplicationContext("spring-context.xml");

//        TestBean tb = context.getBean(TestBean.class);
//        System.out.println(tb.getName());
//        DemoBean1 demoBean1 = context.getBean(DemoBean1.class);
//        System.out.println(demoBean1);
//        demoBean1.d();
//        System.out.println(context.getBean(DemoBean2.class));

        justOne();

    }

    public static void justOne(){
        ApplicationContext context = new AnnotationConfigApplicationContext(TestConfiguration.class);
        System.out.println(context.getBean(TestConfiguration.class));
    }
}

