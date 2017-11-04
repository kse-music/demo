package com.hiekn.demo.test;

import java.util.Map;
import java.util.Properties;

import org.junit.Test;

public class SystemPropertiesTest extends TestBase{
	
	@Test
	public void getAll(){
		
		 Properties props = System.getProperties();
		 props.list(System.out);  
		 
		 
		
		Map<String, String> envs = System.getenv();
		for (Map.Entry<String, String> env : envs.entrySet()) {
			System.out.println(env.getKey() + " = " + env.getValue());
		}
		
	}

}
