package com.hiekn.demo.test.frame.spring.basic;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigBean {

    @Bean
    public DemoBean3 demoBean3(){
        return new DemoBean3();
    }
}
