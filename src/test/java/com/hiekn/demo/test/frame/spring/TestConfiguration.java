package com.hiekn.demo.test.frame.spring;

import org.springframework.beans.factory.annotation.Autowired;

//@Configuration
//@ComponentScan("com.hiekn.demo.test.frame.spring")
public class TestConfiguration {

    @Autowired
    private TestConfiguration a;

    public TestConfiguration(){
        System.out.println(a);
        System.out.println("TestConfiguration");
    }

//    @Bean注解注册bean,同时可以指定初始化和销毁方法
//    @Bean(name="testNean",initMethod="start",destroyMethod="cleanUp")
//    public TestBean testBean() {
//        return new TestBean();
//    }
}