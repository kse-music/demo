package com.hiekn.demo.test.java8;

public class Person {
    String firstName;
    String lastName;
    Person(){}
    Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
	@Override
	public String toString() {
		return "Person [firstName=" + firstName + ", lastName=" + lastName + "]";
	}
    
}