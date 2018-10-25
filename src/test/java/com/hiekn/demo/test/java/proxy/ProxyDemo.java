package com.hiekn.demo.test.java.proxy;

import com.hiekn.demo.test.java.proxy.cglib.HelloConcrete;
import com.hiekn.demo.test.java.proxy.cglib.MyMethodInterceptor;
import com.hiekn.demo.test.java.proxy.jdk.Hello;
import com.hiekn.demo.test.java.proxy.jdk.HelloImpl;
import com.hiekn.demo.test.java.proxy.jdk.LogInvocationHandler;
import org.springframework.cglib.proxy.Enhancer;

import java.lang.reflect.Proxy;

public class ProxyDemo {

    public static void main(String[] args) {
        Hello hello = (Hello)Proxy.newProxyInstance(
                ProxyDemo.class.getClassLoader(), // 1. 类加载器
                new Class<?>[] {Hello.class}, // 2. 代理需要实现的接口，可以有多个
                new LogInvocationHandler(new HelloImpl()));// 3. 方法调用的实际处理者
        System.out.println(hello.sayHello("I love you!"));


        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(HelloConcrete.class);
        enhancer.setCallback(new MyMethodInterceptor());

        HelloConcrete hello2 = (HelloConcrete)enhancer.create();
        System.out.println(hello2.sayHello("I love you!"));

    }

}
