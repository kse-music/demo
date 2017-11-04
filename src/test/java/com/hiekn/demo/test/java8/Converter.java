package com.hiekn.demo.test.java8;

@FunctionalInterface
public interface Converter<F, T> {
    T convert(F from);
}
