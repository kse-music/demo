package com.hiekn.demo.test.spring.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// 在运行时执行
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE,ElementType.FIELD, ElementType.METHOD })
public @interface ZxfResource {

    //注解的name属性
    String name() default "";
}
