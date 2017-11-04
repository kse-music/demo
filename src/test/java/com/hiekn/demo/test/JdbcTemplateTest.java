package com.hiekn.demo.test;

import javax.annotation.Resource;

import org.junit.Test;

import com.hiekn.demo.dao.UserDao;

public class JdbcTemplateTest extends TestBase{

	@Resource
	private UserDao userDao;
	
	@Test
	public void del(){
		userDao.delete(15);
	}
}
