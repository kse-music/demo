package com.hiekn.demo.test.java.inner;

/*
    可是实现多重继承
 */
public class MoreExtentsExample {
    private class Test1 extends Example1{
        public String name() {
            return super.name();
        }
    }
    private class Test2 extends Example2{
        public Integer age() {
            return super.age();
        }
    }

    public String name() {
        return new Test1().name();
    }
    public int age() {
        return new Test2().age();
    }

    public static void main(String[] args) {
        MoreExtentsExample mi = new MoreExtentsExample();
        System.out.println("姓名:" + mi.name());
        System.out.println("年龄:" + mi.age());
    }
}
