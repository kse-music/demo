package com.hiekn.demo.test.study.spring.basic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * top level bean
 */
@Component
public class CircleBean {

    @Autowired
    private DemoBean demoBean;

}
