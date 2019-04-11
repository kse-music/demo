package com.hiekn.demo.test.design.facade;

import com.hiekn.demo.test.design.factory.Circle;
import com.hiekn.demo.test.design.factory.Rectangle;
import com.hiekn.demo.test.design.factory.Shape;
import com.hiekn.demo.test.design.factory.Square;

/**
 * describe about this class
 *
 * @author: DingHao
 * @date: 2019/4/11 12:27
 */
public class ShapeMaker {
    private Shape circle;
    private Shape rectangle;
    private Shape square;

    public ShapeMaker() {
        circle = new Circle();
        rectangle = new Rectangle();
        square = new Square();
    }

    public void drawCircle(){
        circle.draw();
    }
    public void drawRectangle(){
        rectangle.draw();
    }
    public void drawSquare(){
        square.draw();
    }
}
