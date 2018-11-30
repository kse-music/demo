package com.hiekn.demo.test.frame.spring.proxy.jdk;

public class HelloImpl implements Hello {

    @Override
    public String sayHello(String str) {
        return "HelloImpl："+str;
    }

}
