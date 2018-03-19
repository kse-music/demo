package com.hiekn.demo.test.frame.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

//@Configuration
@ComponentScan("com.hiekn.demo.test.frame.spring")
public class TestConfiguration {
    public TestConfiguration(){
        System.out.println("spring容器启动初始化。。。");
    }

    //@Bean注解注册bean,同时可以指定初始化和销毁方法
    //@Bean(name="testNean",initMethod="start",destroyMethod="cleanUp")
//    @Bean
//    @Scope("prototype")
//    public TestBean testBean() {
//        return new TestBean();
//    }
}