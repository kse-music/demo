package com.hiekn.demo.test.frame.spring.basic;

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
public class DemoBean1 {

    private DemoBean3 demoBean333;

//    private DemoBean2 demoBean22;
//    public DemoBean1(DemoBean2 demoBean22) {
//        this.demoBean22 = demoBean22;
//    }

    @Autowired
    private DemoBean2 demoBean2;

    @Autowired
    private ObjectFactory<DemoBean2> demoBean2s;

    @Autowired
    private ObjectProvider<List<DemoBean3>> demoBean3s;

    public void test(){
        System.out.println(demoBean2);
        System.out.println(demoBean2s.getObject());//beanFactory必须存在DemoBean2至少一个
        List<DemoBean3> ifAvailable = demoBean3s.getIfAvailable();//DemoBean3一个没有则为空
        List<DemoBean3> ifUnique = demoBean3s.getIfUnique();//不唯一且没有@primary，throw ex
        System.out.println(ifAvailable);
        System.out.println(ifUnique);
        System.out.println(demoBean333);
    }

    @Autowired
    public void setDemoBean333(DemoBean3 demoBean333) {
        this.demoBean333 = demoBean333;
    }
}
