package com.hiekn.demo.test.frame.spring.basic;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

@Component
public class DemoBean3FactoryBean implements FactoryBean<DemoBean3> {

    @Override
    public DemoBean3 getObject() throws Exception {
        return new DemoBean3();
    }

    @Override
    public Class<?> getObjectType() {
        return DemoBean3.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

}
