package com.hiekn.demo.test.frame.spring.processor;

import org.aspectj.lang.annotation.Around;
import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.annotation.AnnotatedGenericBeanDefinition;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Map;

@Component
public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor,EnvironmentAware {

    private Environment environment;

    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        String[] beanStr = beanFactory.getBeanDefinitionNames();
        for (String beanName : beanStr) {
            if ("testConfiguration".equals(beanName)) {
                BeanDefinition beanDefinition = beanFactory.getBeanDefinition(beanName);
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
                MutablePropertyValues m = beanDefinition.getPropertyValues();
                m.addPropertyValue("home", "赵四");
            }else if ("aopConfig".equals(beanName)) {
                BeanDefinition beanDefinition = beanFactory.getBeanDefinition(beanName);
                AnnotatedGenericBeanDefinition bd = (AnnotatedGenericBeanDefinition)beanDefinition;
                try {
                    Method[] declaredMethods = bd.getBeanClass().getDeclaredMethods();
                    for (Method declaredMethod : declaredMethods) {
                        Annotation[] declaredAnnotations = declaredMethod.getDeclaredAnnotations();
                        for (Annotation annotation : declaredAnnotations) {
                            if(annotation.annotationType().getName().equals(Around.class.getName())){
                                Around around = (Around)annotation;
                                declaredMethod.setAccessible(true);
                                InvocationHandler invocationHandler = Proxy.getInvocationHandler(annotation);
                                Field declaredField = invocationHandler.getClass().getDeclaredField("memberValues");
                                declaredField.setAccessible(true);
                                Map memberValues = (Map) declaredField.get(invocationHandler);
                                memberValues.put("value", environment.getProperty(around.value(),around.value()));
                            }
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("BeanFactoryPostProcessor : postProcessBeanFactory invoke");
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }
}