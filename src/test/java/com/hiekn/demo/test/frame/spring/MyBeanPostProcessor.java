package com.hiekn.demo.test.frame.spring;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

@Component
public class MyBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if(bean instanceof TestBean) {
            TestBean pb = (TestBean)bean;
            System.out.println("name:"+pb.getName());
        }
        return bean;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if(bean instanceof TestBean) {
            TestBean pb = (TestBean)bean;
            System.out.println("name:"+pb.getName());
        }
        return bean;
    }

}