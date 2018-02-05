package com.hiekn.demo.service.impl;

import com.hiekn.demo.bean.UserBean;
import com.hiekn.demo.bean.result.ErrorCodes;
import com.hiekn.demo.dao.UserMapper;
import com.hiekn.demo.exception.ServiceException;
import com.hiekn.demo.service.UserService;
import com.hiekn.demo.util.ValidatorUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

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
			throw ServiceException.newInstance(ErrorCodes.PARAM_PARSE_ERROR);
		}
		return userMapper.selectByName(name);
	}
	
}
