package com.hiekn.demo.bean;

import org.springframework.stereotype.Component;

@Component
public class Student {
	private Integer age;
	private String name;
	
	public Student(){
		this.age = 29;
		this.name = "丁浩";
	}
	
	public void setAge(Integer age) {
		this.age = age;
	}
	public Integer getAge() {
		System.out.println("Age : " + age );
		return age;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		System.out.println("Name : " + name );
		return name;
	}
	public void printThrowException(){
		System.out.println("Exception raised");
		throw new IllegalArgumentException();
	}
}
