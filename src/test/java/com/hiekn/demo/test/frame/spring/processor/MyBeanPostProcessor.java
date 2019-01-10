package com.hiekn.demo.test.frame.spring.processor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

//@Component
public class MyBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if(bean instanceof ResearchConfiguration) {
            ResearchConfiguration testConfiguration = (ResearchConfiguration) bean;
//            testConfiguration.setHome("BeanPostProcessor:postProcessBeforeInitialization");
        }
        System.out.println(beanName+" BeanPostProcessor : postProcessBeforeInitialization invoke");
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if(bean instanceof ResearchConfiguration) {
            ResearchConfiguration testConfiguration = (ResearchConfiguration) bean;
//            testConfiguration.setHome("BeanPostProcessor:postProcessAfterInitialization");
        }
        System.out.println(beanName+" BeanPostProcessor : postProcessAfterInitialization invoke");
        return bean;
    }

}