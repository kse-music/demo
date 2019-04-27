package com.hiekn.demo.test.spring.annotation;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE})
@ZxfResource(name = "z")
public @interface TestAnnotation {
    String value() default "";
}
