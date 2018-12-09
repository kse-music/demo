package com.hiekn.demo.test.design.decorator;

/**
 * @Author: DingHao
 * @Date: 2018/12/9 22:43
 */
public abstract class ShapeDecorator implements Shape {
    protected Shape decoratedShape;

    public ShapeDecorator(Shape decoratedShape){
        this.decoratedShape = decoratedShape;
    }

    public void draw(){
        decoratedShape.draw();
    }
}