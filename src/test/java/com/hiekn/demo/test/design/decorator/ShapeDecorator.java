package com.hiekn.demo.test.design.decorator;

import com.hiekn.demo.test.design.factory.Shape;

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