package com.itcast.jdbc.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.itcast.jdbc.JdbcUtils;
import com.itcast.jdbc.dao.DaoException;
import com.itcast.jdbc.dao.UserDao;
import com.itcast.jdbc.domain.User;

public class UserDaoJdbcImpl implements UserDao {

	@Override
	public void addUser(User user) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = JdbcUtils.getConnection();
			String sql = "insert into user(name,password,date) values (?,?,?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1,user.getName());
			ps.setString(2, user.getPassword());
			ps.setDate(3, new java.sql.Date(user.getDate().getTime()));	//数据表中的date是java.sql包中的。
			ps.executeUpdate();
		}catch(SQLException e) {
			throw new DaoException(e.getMessage(),e);
		}finally {
			JdbcUtils.free(rs, ps, conn);
		}
	}

	@Override
	public void deleteUser(User user) {
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			conn = JdbcUtils.getConnection();
			st = conn.createStatement();
			String sql = "delete from user where userId=" + user.getId();
			st.executeUpdate(sql);
		}catch(SQLException e) {
			throw new DaoException(e.getMessage(),e);
		}finally {
			JdbcUtils.free(rs, st, conn);
		}	

	}
	@Override
	public void updateUser(User user) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = JdbcUtils.getConnection();
			String sql = "update user set name=?,password=?,date=? where id=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1,user.getName());
			ps.setString(2,user.getPassword());
			ps.setDate(3, new java.sql.Date(user.getDate().getTime()));
			ps.setInt(4, user.getId());
			ps.executeUpdate();
		}catch(SQLException e) {
			throw new DaoException(e.getMessage(),e);
		}finally {
			JdbcUtils.free(rs, ps, conn);
		}
	}
	
	@Override
	public User readUser(String loginName,String password) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		User user = null;
		try {
			conn = JdbcUtils.getConnection();
			String sql = "select id,name,password,date from user where name=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1,loginName);
			rs = ps.executeQuery();
			while(rs.next()) {
				user = mappingUser(rs);
			}
		}catch(SQLException e) {
			throw new DaoException(e.getMessage(),e);
		}finally {
			JdbcUtils.free(rs, ps, conn);
		}
		return user;
	}

	private User mappingUser(ResultSet rs) throws SQLException {
		User user;
		user = new User();
		user.setId(rs.getInt("id"));
		user.setName(rs.getString("name"));
		user.setPassword(rs.getString("password"));
		user.setDate(rs.getDate("date"));
		return user;
	}

	@Override
	public User getUser(int userId) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		User user = null;
		try {
			conn = JdbcUtils.getConnection();
			String sql = "select id,name,password,date from user where id=?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1,userId);
			rs = ps.executeQuery();
			while(rs.next()) {
				user = mappingUser(rs);
			}
		}catch(SQLException e) {
			throw new DaoException(e.getMessage(),e);
		}finally {
			JdbcUtils.free(rs, ps, conn);
		}
		return user;
	}

}
