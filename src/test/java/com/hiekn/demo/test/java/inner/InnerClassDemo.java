package com.hiekn.demo.test.java.inner;

import com.hiekn.demo.test.TestBase;
import org.junit.Test;

/*
 内部类可以很好的实现隐藏
 一般的非内部类，是不允许有 private 与protected权限的，但内部类可以
 */
public class InnerClassDemo extends TestBase {

    private static String a;
    private String b;


    @Test
    public void inner() {
        Example a=new Example();

        InterfaceTest a1 = a.getIn();
        a1.test();

        new A().a();

    }

    class A{

        public void a(){
            System.out.println("a"+a);
        }

    }

    static class B{

        public void b(){

        }
    }

}

