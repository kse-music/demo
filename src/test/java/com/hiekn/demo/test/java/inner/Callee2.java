package com.hiekn.demo.test.java.inner;

/*
    避免修改接口而实现同一个类中两种同名方法的调用
 */
public class Callee2 extends MyIncrement {
    private int i=0;
    private void incr() {
        i++;
        System.out.println(i);
    }

    private class Closure implements IncrementAble {
        public void increment() {
            incr();
        }
    }

    IncrementAble getCallbackReference() {
        return new Closure();
    }

    public static void main(String[] args) {
        Callee2 callee2 = new Callee2();
        callee2.getCallbackReference().increment();
    }
}
