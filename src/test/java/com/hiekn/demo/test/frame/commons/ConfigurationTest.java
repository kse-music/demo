package com.hiekn.demo.test.frame.commons;

import com.hiekn.demo.test.TestBase;
import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.configuration2.ex.ConfigurationException;
import org.junit.Test;

import java.util.List;

public class ConfigurationTest extends TestBase {
	
	@Test
	public void prop(){
		Configurations configs = new Configurations(); 
		Configuration config;
		try {
			config = configs.properties("demo.properties");
			List<Object> b = config.getList("b"); 
			List<String> bb = config.getList(String.class,"b"); 
			System.out.println(b);
			System.out.println(bb);
		} catch (ConfigurationException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void xml(){
		Configurations configs = new Configurations(); 
		Configuration config;
		try {
			config = configs.xml("log4j2.xml");
			String name = config.getString("Properties.Property[@name]");
			String property = config.getString("Properties.Property");
			List<String> refs = config.getList(String.class, "Loggers.root.appender-ref[@ref]");
			System.out.println(name);
			System.out.println(property);
			System.out.println(refs);
		} catch (ConfigurationException e) {
			e.printStackTrace();
		}
	}

}
