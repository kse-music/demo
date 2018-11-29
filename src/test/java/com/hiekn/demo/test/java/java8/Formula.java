package com.hiekn.demo.test.java.java8;

public interface Formula {
	double calculate(int a);
	default double sqrt(int a) {
		return Math.sqrt(a);
	}
}