package com.hiekn.demo.test.java.proxy.jdk;

import com.hiekn.demo.test.java.proxy.UserManager;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Arrays;

public class LogInvocationHandler implements InvocationHandler {

    private UserManager userManager;

    public LogInvocationHandler(UserManager userManager) {
        this.userManager = userManager;
    }



    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if("addUser".equals(method.getName())) {
            System.out.println("You said: " + Arrays.toString(args));
        }
        return method.invoke(userManager, args);
    }
}
