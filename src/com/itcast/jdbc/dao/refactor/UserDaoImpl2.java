package com.itcast.jdbc.dao.refactor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.itcast.jdbc.JdbcUtils;
import com.itcast.jdbc.dao.DaoException;
import com.itcast.jdbc.domain.User;

public class UserDaoImpl2{
	MyDaoTemplate template = new MyDaoTemplate();
	
	public User findUser(String loginName,String password) {
		String sql = "select id,name,password,date from user where name=?";
		Object[] args = new Object[] {loginName};
		Object user = this.template.find(sql, args, new UserRowMapper());
		return (User)user;
	}	
	public String findUserName(int id) {
		String sql = "select name from user where id =?";
		Object[] args = new Object[] {id};
		Object name = this.template.find(sql, args, new RowMapper() {
			@Override
			public Object mapRow(ResultSet rs) throws SQLException {
				return rs.getObject("name");
			}
		});
		return (String)name;
	}
}
class UserRowMapper implements RowMapper{

	@Override
	public Object mapRow(ResultSet rs) throws SQLException {
		User user = new User();
		user.setId(rs.getInt("id"));
		user.setName(rs.getString("name"));
		user.setPassword(rs.getString("password"));
		user.setDate(rs.getDate("date"));
		return user;		
	}
}