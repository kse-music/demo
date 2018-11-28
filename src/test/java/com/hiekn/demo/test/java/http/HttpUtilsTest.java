package com.hiekn.demo.test.java.http;

import com.hiekn.demo.util.HttpRequest;

public class HttpUtilsTest {
	public static void main(String[] args) {
        testHttp();
	}


	private static void testHttp(){
        String s = HttpRequest.sendGet("http://www.hiboot.cn/");
        System.out.println(s);
    }

}
