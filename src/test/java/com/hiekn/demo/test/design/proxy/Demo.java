package com.hiekn.demo.test.design.proxy;

class A{
    void method1(){}
    void method2(){}
}
class B{
    //delegation
    A a = new A();
    //method with the same name in A
    void method1(){ a.method1();}
    void method2(){ a.method2();}
    //other methods and attributes
}
public class Demo {
    public static void main(String args[]) {
        B b = new B();
        b.method1();//invoke method2 of class A in fact
        b.method2();//invoke method1 of class A in fact
    }

}
