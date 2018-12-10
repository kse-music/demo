package com.hiekn.demo.test.frame.spring.basic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * top level bean
 */
@Component
public class DemoBean2 {

    @Autowired
    private DemoBean1 demoBean1;

}
