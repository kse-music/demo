package com.hiekn.demo.test.design.bridge;

/**
 * 桥接模式,使得具体类与接口实现者类无关。
 *
 */
public class BridgeDemo {

	public static void main(String[] args) {
		Shape redCircle = new Circle(100,100, 10, new ColorPrinter());
		Shape blackCircle = new Circle(100,100, 10, new BlackPrinter());

		redCircle.draw();
		blackCircle.draw();
	}

}


