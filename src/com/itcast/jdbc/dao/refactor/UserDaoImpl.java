package com.itcast.jdbc.dao.refactor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.itcast.jdbc.JdbcUtils;
import com.itcast.jdbc.dao.DaoException;
import com.itcast.jdbc.domain.User;

public class UserDaoImpl extends AbstractDao{
	public User findUser(String loginName,String password) {
		String sql = "select id,name,password,date from user where name=?";
		Object[] args = new Object[] {loginName};
		Object user = super.find(sql, args);
		return (User)user;
	}	
	
	protected Object rowMapper(ResultSet rs) throws SQLException {
		User user;
		user = new User();
		user.setId(rs.getInt("id"));
		user.setName(rs.getString("name"));
		user.setPassword(rs.getString("password"));
		user.setDate(rs.getDate("date"));
		return user;		
	}
	public void delete(User user) {
		String sql = "delete from user where id=?";
		Object[] args = new Object[] {user.getId()};
		super.update(sql, args);
		
	}
	public void update(User user) {
		String sql = "update user set name=?,password=?,date=? where id=?";
		Object[] args = new Object[] {user.getName(),user.getPassword(),
				user.getDate(),user.getId()};
		super.update(sql, args);
	}
}
