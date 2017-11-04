package com.hiekn.demo.dao.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.google.common.collect.Maps;
import com.hiekn.demo.dao.UserDao;

@Repository
public class UserDaoImpl implements UserDao{

	@Resource
	private JdbcTemplate jdbcTemplate;
	
	@Resource
	private SqlSessionFactory sqlSessionFactory;

	@Override
	public void delete(Integer id) {
		String SQL = "delete from h_log where id = ?";
		jdbcTemplate.update(SQL, id);
		Map<String,Object> paramMap = Maps.newHashMap();
		paramMap.put("id", 16);
		/*
		 * Mybatis是通过SqlSession来操作数据库的。SqlSession中不仅包含要处理的SQL信息，还包括一些数据信息，
			所以说它是线程不安全的，因此它最佳的生命周期范围是在方法体之内。
		 */
		SqlSession sqlSession = sqlSessionFactory.openSession();
		sqlSession.delete("com.hiekn.demo.mapper.UserMapper.deleteById", paramMap);
		sqlSession.close();
	}
}
