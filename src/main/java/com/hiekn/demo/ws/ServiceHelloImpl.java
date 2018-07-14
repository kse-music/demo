package com.hiekn.demo.ws;

import javax.jws.WebService;
import javax.xml.ws.Endpoint;

@WebService
public class ServiceHelloImpl implements ServiceHello{
	
	@Override
	public String getValue(String name) {
		return "我叫："+name;
	}
	
	public static void main(String[] args) {
		Endpoint.publish("http://localhost:9001/Service/ServiceHello", new ServiceHelloImpl());
		System.out.println("service success!");
	}
}
