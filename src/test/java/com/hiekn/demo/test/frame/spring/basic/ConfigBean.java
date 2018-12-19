package com.hiekn.demo.test.frame.spring.basic;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class ConfigBean {

    @Bean
    @Primary
    public DemoBean3 demoBean3(){
        return new DemoBean3();
    }
}
