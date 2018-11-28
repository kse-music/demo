package com.hiekn.demo.test.java.java8;

public interface PersonFactory<P extends Person> {
    P create(String firstName, String lastName);
}