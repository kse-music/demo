package com.hiekn.demo.test.design.bridge;

public abstract class Shape {
    protected Printer print;
    protected Shape(Printer p){
        this.print = p;
    }
    public abstract void draw();
}
