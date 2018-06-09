package com.hiekn.demo.test.frame.spring.proxy;

public class Client {

    public static void main(String[] args) {
        System.out.println("-----------CGLibProxy-------------");
        UserManager userManager = (UserManager) new CGLibProxy().createProxyObject(new UserManagerImpl());
        userManager.addUser("tom", "root");

        System.out.println("-----------JDKProxy-------------");
        JDKProxy jdkProxy = new JDKProxy();
        UserManager userManagerJDK = (UserManager) jdkProxy.newProxy(new UserManagerImpl());
        userManagerJDK.addUser("tom", "root");
    }

}