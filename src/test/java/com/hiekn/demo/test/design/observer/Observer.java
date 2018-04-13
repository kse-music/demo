package com.hiekn.demo.test.design.observer;

public abstract class Observer {
    protected Subject subject;
    public abstract void update();
}
