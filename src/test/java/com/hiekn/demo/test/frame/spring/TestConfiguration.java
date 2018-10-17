package com.hiekn.demo.test.frame.spring;

import org.springframework.context.annotation.Configuration;

@Configuration
//@ComponentScan("com.hiekn.demo.test.frame.spring")
public class TestConfiguration {
    public TestConfiguration(){
        System.out.println("TestConfiguration");
    }

//    @Bean注解注册bean,同时可以指定初始化和销毁方法
//    @Bean(name="testNean",initMethod="start",destroyMethod="cleanUp")
//    public TestBean testBean() {
//        return new TestBean();
//    }
}