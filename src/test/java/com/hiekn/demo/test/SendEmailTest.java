package com.hiekn.demo.test;

import org.apache.commons.mail.EmailException;
import org.junit.Test;

import com.hiekn.demo.util.SendEmailUtils;

public class SendEmailTest extends TestBase{

	
	@Test
	public void sendTest(){
		
		try {
			SendEmailUtils.sendEmail("dh.hiekn@gmail.com", "111111");
		} catch (EmailException e) {
			e.printStackTrace();
		}
	}
}
