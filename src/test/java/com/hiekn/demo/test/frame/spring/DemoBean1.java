package com.hiekn.demo.test.frame.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DemoBean1 {
    @Autowired
    private DemoBean2 demoBean2;


}
