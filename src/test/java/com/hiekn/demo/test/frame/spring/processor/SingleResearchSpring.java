package com.hiekn.demo.test.frame.spring.processor;

import com.hiekn.demo.test.frame.spring.basic.ExampleBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@ComponentScan("com.hiekn.demo.test.frame.spring.processor")
public class SingleResearchSpring implements InitializingBean {

    @Value("${user.home2}")
    private String home;

    @Autowired
    private ExampleBean exampleBean;

    public void setHome(String home) {
        this.home = home;
    }

    public SingleResearchSpring(){
        System.out.println("Constructor invoke");
    }

    @PostConstruct
    private void init(){
        System.out.println("@PostConstruct");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("afterPropertiesSet");
    }

    public String test(){
        System.out.println("home = "+ home);
        System.out.println(exampleBean);
        return "test method return value";
    }

    @Configuration
    static class InnerService{

        @Bean
        public ExampleBean exampleBean(){
            return new ExampleBean();
        }

    }

}