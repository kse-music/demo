package com.hiekn.demo.test.spring.basic;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * ObjectFactory
 * ObjectProvider 继承了 ObjectFactory
 * 构造器循环依赖（解决不了）
 * 字段循环依赖（spring内部解决）
 */
@Component
public class DemoBean {

    private ExampleBean exampleBean;

    @Autowired
    private CircleBean circleBean;

    @Autowired
    private ObjectFactory<ExampleBean> exampleBeans;

    @Autowired
    private ObjectProvider<List<ExampleBean>> exampleBeanList;

    public void test(){
        System.out.println(exampleBean);
        System.out.println(exampleBeans.getObject());//beanFactory必须存在ExampleBean至少一个,不唯一且没有@primary，throw ex

        List<ExampleBean> ifAvailable = exampleBeanList.getIfAvailable();
        System.out.println(ifAvailable);

        List<ExampleBean> ifUnique = exampleBeanList.getIfUnique();
        System.out.println(ifUnique);

    }

    @Autowired
    public void setExampleBean(ExampleBean exampleBean) {
        this.exampleBean = exampleBean;
    }
}
