package com.itcast.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.text.html.HTMLDocument.HTMLReader.PreAction;


public class CRUD {
	public static void main(String[] args) throws Exception {
		//create();
		read();
		//update();
		//delete();
	}
	private static void create() throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			//2.建立连接
			conn = JdbcUtils.getConnection();
			//3.创建语句
			String sql = "insert into students(name,age) values (?,?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, "xiaoming");
			ps.setInt(2, 20);
			//4、执行语句
			int i = ps.executeUpdate();
				
			System.out.println("i =" + i);
		}finally {
			JdbcUtils.free(rs, ps, conn);
		}
	}

	private static void delete() throws SQLException {
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			//2.建立连接
			conn = JdbcUtils.getConnection();
			//3.创建语句
			st = conn.createStatement();
			String sql = "delete from students where id>2";
	
			//4、执行语句
			int i = st.executeUpdate(sql);
			System.out.println("i = " + i);
		}finally {
			JdbcUtils.free(rs, st, conn);
		}	
	}
	
	private static void update() throws SQLException {
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			//2.建立连接
			conn = JdbcUtils.getConnection();
			//3.创建语句
			st = conn.createStatement();
			String sql = "update  students set age = age+10";
	
			//4、执行语句
			int i = st.executeUpdate(sql);
			System.out.println("i = " + i);
		}finally {
			JdbcUtils.free(rs, st, conn);
		}			
	}

	private static void read() throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			//2.建立连接
			conn = JdbcUtils.getConnection();
			//3.创建语句
			String sql = "select id,name,age from students";
			ps = conn.prepareStatement(sql);
	
			//4、执行语句
			rs = ps.executeQuery();
			//5、处理结果
			while(rs.next()) {
				System.out.println(rs.getInt("id") + "\t"
							+ rs.getString("name") + "\t"+rs.getInt("age"));
			}
		}finally {
			JdbcUtils.free(rs, ps, conn);
		}
	}


}
