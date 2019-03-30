package com.itcast.jdbc.dao.refactor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.itcast.jdbc.JdbcUtils;
import com.itcast.jdbc.dao.DaoException;
import com.itcast.jdbc.domain.User;

public abstract class AbstractDao {
	public Object find(String sql,Object[] args) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		User user = null;
		try {
			conn = JdbcUtils.getConnection();
			ps = conn.prepareStatement(sql);
			for(int i = 0;i < args.length; i++) 
				ps.setObject(i+1, args[i]);
			rs = ps.executeQuery();
			Object obj = null;
			while(rs.next()) {
				obj = rowMapper(rs);
			}
			return obj;
		}catch(SQLException e) {
			throw new DaoException(e.getMessage(),e);
		}finally {
			JdbcUtils.free(rs, ps, conn);
		}
	}
	
	abstract protected Object rowMapper(ResultSet rs) throws SQLException;
	
	
	public int update(String sql,Object[] args) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = JdbcUtils.getConnection();
			ps = conn.prepareStatement(sql);
			for(int i = 0;i < args.length; i++) 
				ps.setObject(i+1, args[i]);
			return ps.executeUpdate();
		}catch(SQLException e) {
			throw new DaoException(e.getMessage(),e);
		}finally {
			JdbcUtils.free(rs, ps, conn);
		}
	}
}
