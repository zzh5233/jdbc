package com.itcast.jdbc.spring;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;

import com.itcast.jdbc.JdbcUtils;
import com.itcast.jdbc.domain.Account;
import com.itcast.jdbc.domain.User;

public class SimpleJdbcTemplateTest {
	static SimpleJdbcTemplate simple = new SimpleJdbcTemplate(
			JdbcUtils.getDataSource());
	public static void main(String[] args) {
		System.out.println("user:"+find("xiaoming",User.class));
//		System.out.println("account:"+find("xiaoming",Account.class));
	}
	static <T> T find(String name,Class<T> clazz) {
		String sql = "select id,name,password,date from user "
				+ "where name=? and id =?";
		T t = simple.queryForObject(sql,
				ParameterizedBeanPropertyRowMapper.newInstance(clazz),
				name,2);
		return t;
	}	
	static User findUser2(String name) {
		String sql = "select id,name,password,date from user "
				+ "where name=? and id =?";
		User user = simple.queryForObject(sql,
				ParameterizedBeanPropertyRowMapper.newInstance(User.class),
				name,2);
		return user;
	}	
}
