package com.itcast.jdbc.spring;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.itcast.jdbc.JdbcUtils;
import com.itcast.jdbc.domain.User;

public class NamedJdbcTemplate {
	static NamedParameterJdbcTemplate named = new NamedParameterJdbcTemplate(
			JdbcUtils.getDataSource());
	
	public static void main(String[] args) {
//		User user = new User();
//		user.setName("xiaoming");
//		user.setDate(new Date());
//		user.setId(4);
//		System.out.println(findUser(user));
		
	}
	static void addUser(User user) {
		String sql ="insert into user(name,password,date)"
				+ " values (:name,:password,:date)";
		SqlParameterSource ps = new BeanPropertySqlParameterSource(user);
		KeyHolder keyHolder = new GeneratedKeyHolder();
		int id = named.update(sql, ps, keyHolder);	
		user.setId(id);
		
		//Map map = keyHolder.getKeys();
	}
	
	
	
	static User findUser(User user) {
		String sql = "select id,name,password,date from user "
				+ "where name=:name and id=:id";
		Object[] args = new Object[] {user.getName(),
				user.getDate(),user.getId()};	
		SqlParameterSource ps = new BeanPropertySqlParameterSource(user);
//		Map params = new HashMap();
//		params.put("n", user.getName());
//		params.put("i", user.getId());
		
		Object u = named.queryForObject(sql, ps, 
				new BeanPropertyRowMapper(User.class));
		
		return (User)u;
	}	
	
	
}
