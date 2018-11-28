package com.hiekn.demo.test;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)  
@Transactional
@ContextConfiguration({"classpath:applicationContext.xml"})  
public class TestBase {

    protected final Log logger = LogFactory.getLog(getClass());

    @Ignore
	@Test
	public void _test_(){
	
	}
}
