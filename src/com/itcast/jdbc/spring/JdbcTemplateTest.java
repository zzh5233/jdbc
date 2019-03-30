package com.itcast.jdbc.spring;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.ConnectionCallback;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.itcast.jdbc.JdbcUtils;
import com.itcast.jdbc.domain.User;

public class JdbcTemplateTest {
	static JdbcTemplate jdbc = new JdbcTemplate(JdbcUtils.getDataSource());
	public static void main(String[] args) {
//		User user = findUser("lisi");
//		System.out.println(user);
		
//		User user2 = findUser2("lisi");
//		System.out.println(user2);
		
//		System.out.println("list:"+findUsers(3));
//		System.out.println("map:"+getData(2));
		System.out.println("count:"+getUserCount());
		
	}
	static int addUser(User user) {
		jdbc.execute(new ConnectionCallback() {
			@Override
			public Object doInConnection(Connection con) throws SQLException, DataAccessException {
				String sql = "insert into user(name,password,date) values (?,?,?)";
				PreparedStatement ps = con.prepareStatement(sql,
							Statement.RETURN_GENERATED_KEYS);
				ps.setString(1,user.getName());
				ps.setString(2, user.getPassword());
				ps.setDate(3, new java.sql.Date(user.getDate().getTime()));	//数据表中的date是java.sql包中的。
				ps.executeUpdate();				
				
				ResultSet rs = ps.getGeneratedKeys();
				if(rs.next()) {
					user.setId(rs.getInt(1));
				}
				return null;
			}
		});
		return 0;
	}
	
	static int getUserCount() {
		String sql ="select count(*) from user";
		return jdbc.queryForInt(sql);
	}
	
	static Map getData(int id) {
		String sql = "select id,name,password,date from user where id="+id;
		return jdbc.queryForMap(sql);
	}
	static List findUsers(int id) {
		String sql = "select id,name,password,date from user where id<"+id;
		List users = jdbc.query(sql, 
				new BeanPropertyRowMapper(User.class));
		return users;
	}
	static User findUser(String name) {
		String sql = "select id,name,password,date from user where name=?";
		Object[] args = new Object[] {name};	
		Object user = jdbc.queryForObject(sql, args,new BeanPropertyRowMapper(
				User.class));
		return (User)user;
	}	
	static User findUser2(String name) {
		String sql = "select id,name,password,date from user where name=?";
		Object[] args = new Object[] {name};	
		Object user = jdbc.queryForObject(sql, args, new RowMapper() {
			@Override
			public Object mapRow(ResultSet rs, int rowName) throws SQLException {
				User user = new User();
				user.setId(rs.getInt("id"));
				user.setName(rs.getString("name"));
				user.setPassword(rs.getString("password"));
				user.setDate(rs.getDate("date"));
				return user;					
			}
		});
		return (User)user;
	}
}
