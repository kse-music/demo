package com.hiekn.demo.test.java.inner;

import com.hiekn.demo.test.TestBase;
import org.junit.Test;

/*
 内部类可以很好的实现隐藏
 一般的非内部类，是不允许有 private 与protected权限的，但内部类可以

 * 总结：
 * 1.静态内部类可以有静态成员(方法，属性)，而非静态内部类则不能有静态成员(方法，属性)。
 * 2.静态内部类只能够访问外部类的静态成员,而非静态内部类则可以访问外部类的所有成员(方法，属性)。
 * 3.实例化一个非静态的内部类的方法：
 *  a.先生成一个外部类对象实例
 *  b.通过外部类的对象实例生成内部类对象
 *  4.实例化一个静态内部类的方法：
 *  a.不依赖于外部类的实例,直接实例化内部类对象
 *  b.调用内部静态类的方法或静态变量,通过类名直接调用
 *
 */
public class InnerClassDemo extends TestBase {

    private String a;
    private static String b;


    @Test
    public void inner() {
        Example a = new Example();

        InterfaceTest a1 = a.getIn();
        a1.test();

        new InnerClass().a();//内部类对象会秘密的捕获一个指向外部类的引用
        new InnerClassDemo.InnerClass().a();
        new InnerClassDemo.StaticInnerClass().b();

    }

    //普通内部类的字段与方法，只能放在类的外部层次上
    private class InnerClass {

        //不能有静态成员

        public void a() {
            System.out.println("a" + a);
            System.out.println("b" + b);
        }

    }

    //嵌套类
    private static class StaticInnerClass {

        public void b() {
            System.out.println("b" + b);
        }
    }

}

