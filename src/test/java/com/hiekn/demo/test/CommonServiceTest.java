package com.hiekn.demo.test;

import javax.annotation.Resource;

import com.hiekn.demo.dao.UserDao;
import com.hiekn.demo.util.SendEmailUtils;
import org.apache.commons.mail.EmailException;
import org.junit.Test;

import com.hiekn.demo.bean.Student;
import com.hiekn.demo.service.CommonService;

public class CommonServiceTest extends TestBase{

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

    @Test
    public void sendTest(){

        try {
            SendEmailUtils.sendEmail("dh.hiekn@gmail.com", "111111");
        } catch (EmailException e) {
            e.printStackTrace();
        }
    }

}
