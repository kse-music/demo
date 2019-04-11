package com.hiekn.demo.test.study.spring.processor;

import com.hiekn.demo.test.study.spring.basic.ExampleBean;
import com.hiekn.demo.test.study.spring.basic.ExampleBean2;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@ComponentScan("com.hiekn.demo.test.study.spring.processor")
public class SingleResearchSpring implements InitializingBean {

    @Value("${user.home2}")
    private String home;

//    @Autowired
    private ExampleBean exampleBean;

    @Autowired
    private ExampleBean2 exampleBean2;

    public void setHome(String home) {
        this.home = home;
    }


//    public SingleResearchSpring(){
//        System.out.println("Constructor invoke");
//    }

    /**
     * 10、推荐使用构造函数注入
     *
     * 这一条实践来自Phil Webb（Spring Boot的项目负责人, @phillip_webb）。
     *
     * 保持业务逻辑免受Spring Boot代码侵入的一种方法是使用构造函数注入。
     * 不仅是因为@Autowired注解在构造函数上是可选的，而且还可以在没有Spring的情况下轻松实例化bean。
     */
    public SingleResearchSpring(ExampleBean exampleBean) {
        this.exampleBean = exampleBean;
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

    //@Component,不会为其生成CGLIB代理Class
    @Configuration
    static class InnerService{

        @Bean
        public ExampleBean exampleBean(){
            System.out.println("only one?");
            return new ExampleBean();
        }

        @Bean
        public ExampleBean2 exampleBean2(){
            ExampleBean2 exampleBean2 = new ExampleBean2();
            exampleBean2.setExampleBean(exampleBean());
            return exampleBean2;
        }

    }

}