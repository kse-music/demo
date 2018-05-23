package com.hiekn.demo.test.java.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE})
public @interface TestAnnotation {
    String prefix() default "";
    String[] name() default {};
}
