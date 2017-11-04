package com.hiekn.demo.test.java8;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.hiekn.demo.test.TestBase;
import com.hiekn.demo.util.JsonUtils;

public class OtherTest extends TestBase	{
	
	private static Logger log = LogManager.getLogger(OtherTest.class);
	
	@Test
	public void t() throws Exception {
		A a = new A();
		a.setAge(20);
		a.setName(null);
		System.out.println(JSON.toJSON(a));
		System.out.println(JsonUtils.toJson(a));
		
		log.info("info");
		log.warn("warn");
		log.error("error");
	}
}

class A{
	private String name;
	private Integer age;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	
}