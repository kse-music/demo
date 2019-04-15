package com.hiekn.demo.test.java.proxy.cglib;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * CGLibProxy动态代理类的实例
 *
 *
 */
public class CGLibProxy implements MethodInterceptor {

    private Object targetObject;// CGLib需要代理的目标对象

    public Object createProxyObject(Object obj) {
        this.targetObject = obj;
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(obj.getClass());
        enhancer.setCallback(this);
        Object proxyObj = enhancer.create();
        return proxyObj;// 返回代理对象
    }

    @Override
    public Object intercept(Object proxy, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        if ("addUser".equals(method.getName())) {// 过滤方法
            checkPermission();// 检查权限
        }
        Object obj = method.invoke(targetObject, args);
        return obj;
    }

    private void checkPermission() {
        System.out.println("检查权限  checkPermission()!");
    }
}
