package com.hiekn.demo.test.design.decorator;


public class Circle implements Shape {

    @Override
    public void draw() {
        System.out.println("Shape: Circle");
    }
}