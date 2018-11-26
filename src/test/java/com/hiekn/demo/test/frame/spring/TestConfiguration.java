package com.hiekn.demo.test.frame.spring;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

//@Configuration
//@ComponentScan("com.hiekn.demo.test.frame.spring")
public class TestConfiguration implements InitializingBean {

    @Autowired
    private TestConfiguration a;

    public TestConfiguration(){
        System.out.println(a);
        System.out.println("Constructor");
    }

    @PostConstruct
    private void init(){
        System.out.println("@PostConstruct");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("afterPropertiesSet");
    }

//    @Bean注解注册bean,同时可以指定初始化和销毁方法
//    @Bean(name="testNean",initMethod="start",destroyMethod="cleanUp")
//    public TestBean testBean() {
//        return new TestBean();
//    }
}