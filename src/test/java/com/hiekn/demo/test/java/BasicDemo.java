package com.hiekn.demo.test.java;

import org.springframework.beans.BeanUtils;
import org.springframework.core.MethodParameter;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;

public class BasicDemo {
    public static void main(String[] args) throws Exception{
        Integer a = 128;
        Integer b = 128;
        Integer c = Integer.valueOf(127);
        Integer d = Integer.valueOf(127);
        System.out.println(a == b);
        System.out.println(a.equals(b));
        System.out.println(c == d);
        PropertyDescriptor[] descriptors = BeanUtils.getPropertyDescriptors(P.class);
        P p = new P();
        for (PropertyDescriptor descriptor : descriptors) {
            String name = descriptor.getName();
            if(!"class".equals(name)){
                MethodParameter methodDescriptors = BeanUtils.getWriteMethodParameter(descriptor);
                System.out.println(name);
                Method method = methodDescriptors.getMethod();
                method.invoke(p,1);
            }
        }
        System.out.println(p.getA());
    }
}

class P{
    private Integer a;

    public Integer getA() {
        return a;
    }

    public void setA(Integer a) {
        this.a = a;
    }

}
