package com.hiekn.demo.test.frame.spring.proxy.jdk;

public class StaticProxyHello implements Hello {

    private Hello hello = new HelloImpl();

    @Override
    public String sayHello(String str) {
        System.out.println("log : You said"+str);
        return hello.sayHello(str);
    }

}
