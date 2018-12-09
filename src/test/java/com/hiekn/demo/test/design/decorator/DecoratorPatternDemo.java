package com.hiekn.demo.test.design.decorator;

/**
 * @Author: DingHao
 * @Date: 2018/12/9 22:39
 */
public class DecoratorPatternDemo {
    /**
     * 装饰器模式（Decorator Pattern）允许向一个现有的对象添加新的功能，同时又不改变其结构。
     * 这种类型的设计模式属于结构型模式，它是作为现有的类的一个包装。
     *
     * 这种模式创建了一个装饰类，用来包装原有的类，并在保持类方法签名完整性的前提下，提供了额外的功能。
     *
     *
     */

    public static void main(String[] args) {
        Shape circle = new Circle();

        Shape redCircle = new RedShapeDecorator(new Circle());

        Shape redRectangle = new RedShapeDecorator(new Rectangle());
        System.out.println("Circle with normal border");
        circle.draw();

        System.out.println("\nCircle of red border");
        redCircle.draw();

        System.out.println("\nRectangle of red border");
        redRectangle.draw();
    }
}
