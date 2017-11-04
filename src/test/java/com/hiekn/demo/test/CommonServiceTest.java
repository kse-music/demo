package com.hiekn.demo.test;

import javax.annotation.Resource;

import org.junit.Test;

import com.hiekn.demo.bean.Student;
import com.hiekn.demo.service.CommonService;

public class CommonServiceTest extends TestBase{

	@Resource
	private CommonService commonService;
	@Resource
	private Student student;


	@Test
	public void testDelete(){
		commonService.testTransaction();
	}

	@Test
	public void testAop(){
		student.getName();
		student.getAge();     
		student.printThrowException();
	}

}
