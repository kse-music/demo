package com.hiekn.demo.dao;

import com.hiekn.demo.bean.UserBean;

public interface UserMapper {
	 UserBean selectByName(String name);
	 int deleteById(Integer id);
}
