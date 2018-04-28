package com.hiekn.demo.test.java.inner;

/*

 内部类拥有外围类的所有元素的访问权限

 */
public class TagBean {


    private String name = "MJ";

    private class InTest {

        public InTest(){

            System.out.println(name);

        }

    }

    public void test() {

        new InTest();

    }

    public static void main(String args[]) {

        TagBean bb = new TagBean();

        bb.test();

    }

}
