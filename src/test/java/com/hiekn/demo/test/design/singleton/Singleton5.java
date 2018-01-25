package com.hiekn.demo.test.design.singleton;

public enum  Singleton5 {
    //枚举,非懒加载
    INSTANCE;
    private Resource instance;
    Singleton5() {
        instance = new Resource();
    }
    public Resource getInstance() {
        return instance;
    }
}

class Resource{

}
