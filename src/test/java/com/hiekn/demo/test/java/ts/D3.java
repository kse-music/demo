package com.hiekn.demo.test.java.ts;

public abstract class D3 extends Draw{
    @Override
    public void draw() {
        super.draw();
        System.out.println("使用D3画图");
        custom();
    }

    abstract void custom();
}
