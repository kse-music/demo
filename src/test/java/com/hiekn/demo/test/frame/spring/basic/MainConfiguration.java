package com.hiekn.demo.test.frame.spring.basic;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Primary;

/**
 * @author: DingHao
 * @date: 2018/12/10 10:43
 */
//@Configuration
@ComponentScan("com.hiekn.demo.test.frame.spring.basic")
//@ImportResource("classpath:spring-config.xml")
public class MainConfiguration {

    @Bean
    @Primary
    public ExampleBean exampleBean(){
        return new ExampleBean();
    }

    @Bean(name="testBean",initMethod="start",destroyMethod="cleanUp")
    public TestBean testBean() {
        return new TestBean();
    }

}
