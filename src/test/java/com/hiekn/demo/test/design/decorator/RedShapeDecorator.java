package com.hiekn.demo.test.design.decorator;

import com.hiekn.demo.test.design.factory.Shape;

/**
 * @Author: DingHao
 * @Date: 2018/12/9 22:44
 */
public class RedShapeDecorator extends ShapeDecorator {

    public RedShapeDecorator(Shape decoratedShape) {
        super(decoratedShape);
    }

    @Override
    public void draw() {
        decoratedShape.draw();
        setRedBorder(decoratedShape);
    }

    private void setRedBorder(Shape decoratedShape){
        System.out.println("Border Color: Red");
    }
}