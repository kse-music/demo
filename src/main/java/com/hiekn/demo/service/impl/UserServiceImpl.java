package com.hiekn.demo.service.impl;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.hiekn.demo.bean.UserBean;
import com.hiekn.demo.bean.result.ErrorCode;
import com.hiekn.demo.dao.UserMapper;
import com.hiekn.demo.exception.ServiceException;
import com.hiekn.demo.service.UserService;
import com.hiekn.demo.util.ValidatorUtils;

@Service("userService")
public class UserServiceImpl implements UserService{
	
	@Resource
	private UserMapper userMapper;

	@Override
	public UserBean getByName(String name) {
		UserBean u = new UserBean();
		u.setName(name);
		ValidatorUtils.validate(u);  
		if(StringUtils.isBlank(name)){
			throw ServiceException.newInstance(ErrorCode.PARAM_ERROR);
		}
		return userMapper.selectByName(name);
	}
	
}
