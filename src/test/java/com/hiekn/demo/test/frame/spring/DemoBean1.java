package com.hiekn.demo.test.frame.spring;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DemoBean1 {

    private DemoBean3 demoBean33;
    private DemoBean3 demoBean333;

    public DemoBean1(DemoBean3 demoBean33) {
        this.demoBean33 = demoBean33;
    }

    @Autowired
    private DemoBean2 demoBean2;

    @Autowired
    private ObjectFactory<DemoBean2> demoBean2s;

    @Autowired
    private ObjectProvider<List<DemoBean3>> demoBean3s;

    public void d(){
        System.out.println(demoBean2);
        System.out.println(demoBean2s.getObject());//beanFactory必须存在DemoBean2至少一个
        List<DemoBean3> ifAvailable = demoBean3s.getIfAvailable();//DemoBean3一个没有则为空
        List<DemoBean3> ifUnique = demoBean3s.getIfUnique();//不唯一且没有@primary，throw ex
        System.out.println(ifAvailable);
        System.out.println(ifUnique);
        System.out.println("---"+demoBean33);
        System.out.println("---"+demoBean333);
    }

    @Autowired
    public void setDemoBean333(DemoBean3 demoBean333) {
        this.demoBean333 = demoBean333;
    }
}
