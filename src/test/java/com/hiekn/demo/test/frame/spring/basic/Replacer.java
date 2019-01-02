package com.hiekn.demo.test.frame.spring.basic;

import org.springframework.beans.factory.support.MethodReplacer;

import java.lang.reflect.Method;

/**
 * describe about this class
 *
 * @author DingHao
 * @date 2019/1/2 11:51
 */
public class Replacer implements MethodReplacer {

    @Override
    public Object reimplement(Object obj, Method method, Object[] args) throws Throwable {
        return "replacer";
    }

}
