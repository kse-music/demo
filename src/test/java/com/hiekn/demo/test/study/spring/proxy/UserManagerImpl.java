package com.hiekn.demo.test.study.spring.proxy;

public class UserManagerImpl implements UserManager {

    public void addUser(String id, String password) {
        System.out.println("调用了UserManagerImpl.addUser()方法！ ");
    }

    public void delUser(String id) {
        System.out.println("调用了UserManagerImpl.delUser()方法！ ");
    }
}