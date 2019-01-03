package com.hiekn.demo.test.java.annotation;


import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE})
@ZxfResource(name = "z")
public @interface TestAnnotation {
    String value() default "";
}
