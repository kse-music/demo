package com.hiekn.demo.test.java.generic;

public class GenericDemo {

    public static void main(String[] args) {

        new A().a();

        A a = new A() {
            @Override
            public void a() {
                System.out.println("over a");
            }

            @Override
            public void b() {
                System.out.println("over b");

            }
        };
        a.a();

    }


}

class A{

    public void a(){
        System.out.println("a");
    }

    public void b(){
        System.out.println("b");
    }
}