package com.hiekn.demo.test.frame.json;

import com.alibaba.fastjson.JSON;
import com.hiekn.demo.test.TestBase;
import com.hiekn.demo.util.JsonUtils;
import org.junit.Test;

public class JsonDemo extends TestBase	{
	
	@Test
	public void test() {
        GsonAndFastJson a = new GsonAndFastJson();
		a.setAge(20);
		a.setName("json");
		System.out.println(JSON.toJSON(a));
		System.out.println(JsonUtils.toJson(a));
	}

}