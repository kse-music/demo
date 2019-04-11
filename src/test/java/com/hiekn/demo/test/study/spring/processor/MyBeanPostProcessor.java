package com.hiekn.demo.test.study.spring.processor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

public class MyBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if(bean instanceof SingleResearchSpring) {
            SingleResearchSpring singleResearchSpring = (SingleResearchSpring) bean;
            singleResearchSpring.setHome("BeanPostProcessor:postProcessBeforeInitialization");
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if(bean instanceof SingleResearchSpring) {
            SingleResearchSpring singleResearchSpring = (SingleResearchSpring) bean;
            singleResearchSpring.setHome("BeanPostProcessor:postProcessAfterInitialization");
        }
        return bean;
    }

}