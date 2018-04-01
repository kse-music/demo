package com.hiekn.demo.test.java;

public class ExtendsDemo {
    public static void main(String[] args) {
        A a = new C();
        a.t();

    }
}

class A{
    public void t(){
        System.out.println("A");
    }
}

class B extends A{
    public void t(){
        System.out.println("B");
    }
}
class C extends B{
    public void t(){
        System.out.println("C");
        super.t();
    }
}
