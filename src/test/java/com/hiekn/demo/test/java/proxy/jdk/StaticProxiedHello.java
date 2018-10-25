package com.hiekn.demo.test.java.proxy.jdk;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class StaticProxiedHello implements Hello {

    private static final Log logger = LogFactory.getLog(StaticProxiedHello.class);


    private Hello hello = new HelloImpl();

    @Override
    public String sayHello(String str) {
        logger.info("log : You said"+str);
        return hello.sayHello(str);
    }

}
