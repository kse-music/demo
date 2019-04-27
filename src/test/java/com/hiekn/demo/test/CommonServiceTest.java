package com.hiekn.demo.test;

import com.hiekn.demo.bean.Student;
import com.hiekn.demo.dao.UserDao;
import com.hiekn.demo.service.CommonService;
import org.junit.Test;

import javax.annotation.Resource;

public class CommonServiceTest extends TestBase {

	@Resource
	private CommonService commonService;

	@Resource
	private Student student;

    @Resource
    private UserDao userDao;

    @Test
    public void del(){
        userDao.delete(15);
    }


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
