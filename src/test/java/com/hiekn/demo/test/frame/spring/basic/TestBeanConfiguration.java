package com.hiekn.demo.test.frame.spring.basic;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Author: DingHao
 * @Date: 2018/12/10 10:43
 */
//@Configuration
@ComponentScan("com.hiekn.demo.test.frame.spring.basic")
public class TestBeanConfiguration {

//   @Bean注解注册bean,同时可以指定初始化和销毁方法
    @Bean(name="testBean",initMethod="start",destroyMethod="cleanUp")
    public TestBean testBean() {
        return new TestBean();
    }

}
