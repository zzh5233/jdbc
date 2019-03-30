package com.itcast.jdbc.dao.impl;


import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.itcast.jdbc.JdbcUtils;
import com.itcast.jdbc.dao.UserDao;
import com.itcast.jdbc.domain.User;

public class UserDaoSpingImpl implements UserDao{
	private SimpleJdbcTemplate simpleJdbcTemplate = new SimpleJdbcTemplate(
			JdbcUtils.getDataSource());
	
	@Override
	public void addUser(User user) {
		String sql = "insert into user(name,password,date) values (:name,:password,:date)";
		SqlParameterSource param = new BeanPropertySqlParameterSource(user);
		KeyHolder keyHolder = new GeneratedKeyHolder();
		this.simpleJdbcTemplate.getNamedParameterJdbcOperations().update(
				sql, param,keyHolder);
		user.setId(keyHolder.getKey().intValue());
	}

	@Override
	public User getUser(int userId) {
		String sql = "select id,name,password,date from user where id=?";
		return (User) this.simpleJdbcTemplate.queryForObject(sql,
				ParameterizedBeanPropertyRowMapper.newInstance(User.class), 
				userId);
	}

	@Override
	public User readUser(String loginName, String password) {
		String sql = "select id,name,password,date from user where name=?";
		return (User) this.simpleJdbcTemplate.queryForObject(sql,
				ParameterizedBeanPropertyRowMapper.newInstance(User.class), 
				loginName);
	}

	@Override
	public void updateUser(User user) {
		String sql = "update user set name=?,password=?,date=? where id=?";
		this.simpleJdbcTemplate.update(sql, user.getName(),user.getPassword(),
				user.getDate(),user.getId());
		
//		String sql = "update user set name=:name,password=:password,date=:date where id=:id";
//		this.simpleJdbcTemplate.update(sql, new BeanPropertySqlParameterSource(user));
	}

	@Override
	public void deleteUser(User user) {
		String sql = "delete from user where userId=?";
		this.simpleJdbcTemplate.update(sql, user.getId());
		
//		sql = "delete from user where userId="+user.getId();
//		this.simpleJdbcTemplate.getJdbcOperations().update(sql);
	}
}
