package com.hiekn.demo.test.frame.spring.processor;

import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.annotation.AnnotatedGenericBeanDefinition;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.Map;

@Component
public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        String[] beanStr = beanFactory.getBeanDefinitionNames();
        for (String beanName : beanStr) {
            if ("testConfiguration".equals(beanName)) {
                BeanDefinition beanDefinition = beanFactory.getBeanDefinition(beanName);
                MutablePropertyValues m = beanDefinition.getPropertyValues();
                m.addPropertyValue("home", "赵四");

                AnnotatedGenericBeanDefinition bd = (AnnotatedGenericBeanDefinition)beanDefinition;
                try {
                    Field field = bd.getBeanClass().getDeclaredField("home");
                    field.setAccessible(true);
                    Annotation[] annotations = field.getDeclaredAnnotations();
                    for (Annotation annotation : annotations) {
                        if (annotation.annotationType().getName().equals(Value.class.getName())) {
                            InvocationHandler invocationHandler = Proxy.getInvocationHandler(annotation);
                            Field declaredField = invocationHandler.getClass().getDeclaredField("memberValues");
                            declaredField.setAccessible(true);
                            Map memberValues = (Map) declaredField.get(invocationHandler);
                            memberValues.put("value", "${user.home2}");
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                BeanDefinitionRegistry r = (BeanDefinitionRegistry)beanFactory;
                r.removeBeanDefinition(beanName);
                r.registerBeanDefinition(beanName,new AnnotatedGenericBeanDefinition(bd.getBeanClass()));
            }
        }
        System.out.println("BeanFactoryPostProcessor : postProcessBeanFactory invoke");
    }

}