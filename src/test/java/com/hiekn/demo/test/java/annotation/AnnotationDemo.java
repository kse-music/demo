package com.hiekn.demo.test.java.annotation;

import com.hiekn.demo.test.TestBase;
import org.junit.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.Map;

/**
 * describe about this class
 *
 * @author: DingHao
 * @date: 2019/4/3 17:44
 */
public class AnnotationDemo extends TestBase {

    @Test
    public void modifyAnnotationValue() throws Exception {
        BeanDefine bar = new BeanDefine();

        Annotation[] annotations = bar.getClass().getDeclaredAnnotations();
        for (Annotation annotation : annotations) {
            if (annotation.annotationType().getName().equals(TestAnnotation.class.getName())) {
                TestAnnotation t = (TestAnnotation) annotation;
                String value = t.value();
                System.out.println("修改之前的注解值：" + value);

                InvocationHandler invocationHandler = Proxy.getInvocationHandler(annotation);
                Field declaredField = invocationHandler.getClass().getDeclaredField("memberValues");
                declaredField.setAccessible(true);
                Map memberValues = (Map) declaredField.get(invocationHandler);
                memberValues.put("prefix", "test.annotation.new.value");

                String newValue = t.value();
                System.out.println("修改之后的注解值：" + newValue);
            }
        }

    }
}
