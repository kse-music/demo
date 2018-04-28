package com.hiekn.demo.test.java.ts;

public abstract class ZoomChart extends Draw{

    @Override
    public void draw() {
        super.draw();
        System.out.println("使用zoomChart画图");
        custom();
    }

    abstract void custom();
}
