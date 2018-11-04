package com.hiekn.demo.test.design.proxy;

/**
 * 意图：为其他对象提供一种代理以控制对这个对象的访问。
 */
public class ProxyPatternDemo {

    public static void main(String[] args) {
        Image image = new ProxyImage("test_10mb.jpg");

        //图像将从磁盘加载
        image.display();
        System.out.println("");
        // 图像不需要从磁盘加载
        image.display();
    }
}
