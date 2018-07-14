package com.hiekn.demo.ws;

import javax.jws.WebService;

@WebService
public interface ServiceHello {
	
	String getValue(String name);

}
