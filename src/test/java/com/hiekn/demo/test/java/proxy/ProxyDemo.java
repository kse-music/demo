package com.hiekn.demo.test.java.proxy;

import com.hiekn.demo.test.java.proxy.cglib.HelloConcrete;
import com.hiekn.demo.test.java.proxy.cglib.MyMethodInterceptor;
import com.hiekn.demo.test.java.proxy.jdk.JDKProxy;
import com.hiekn.demo.test.java.proxy.jdk.LogInvocationHandler;
import com.hiekn.demo.test.study.spring.SpringDemo;
import org.junit.Test;
import org.springframework.cglib.proxy.Enhancer;

import java.lang.reflect.Proxy;

/**
 * jdk And Cglib Proxy
 *
 * @author: DingHao
 * @date: 2019/4/15 17:09
 */
public class ProxyDemo {

    @Test
    public void jdkProxy(){

        UserManager hello = (UserManager) Proxy.newProxyInstance(
                SpringDemo.class.getClassLoader(), // 1. 类加载器
                new Class<?>[] {UserManager.class}, // 2. 代理需要实现的接口，可以有多个
                new LogInvocationHandler(new UserManagerImpl()));// 3. 方法调用的实际处理者
        hello.addUser("1","I love you!");

        JDKProxy jdkProxy = new JDKProxy();
        UserManager userManagerJDK = (UserManager) jdkProxy.newProxy(new UserManagerImpl());
        userManagerJDK.addUser("tom", "root");

    }

    @Test
    public void cglibProxy(){
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(HelloConcrete.class);
        enhancer.setCallback(new MyMethodInterceptor());
        HelloConcrete hello = (HelloConcrete)enhancer.create();
        System.out.println(hello.sayHello("I love you!"));

//        UserManager userManager = (UserManager) new CGLibProxy().createProxyObject(new UserManagerImpl());
//        userManager.addUser("tom", "root");
    }

}
