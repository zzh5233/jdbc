package com.itcast.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Base {
	public static void main(String[] args) throws Exception{
		//template();
		SingTemplate();
//		for(int i = 0; i< 10; i++) {
//			Connection conn = JdbcUtils.getConnection();
//			System.out.println(conn);
//			JdbcUtils.free(null, null, conn);
//		}
	}
	public static void SingTemplate() throws Exception {
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		
		//2.��������
		conn = JdbcSing.getInstance().getConnection();
		//3.�������
		st = conn.createStatement();
		//4��ִ�����
		String sql = "select * from students";
		rs = st.executeQuery(sql);
		//5��������
		while(rs.next()) {
			System.out.println(rs.getInt("id") + "\t"
						+ rs.getString("name") + "\t"+rs.getInt("age"));
		}
		//6���ͷſռ�
		JdbcSing.getInstance().free(rs, st, conn);	
	}
	public static void template() throws Exception{
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		//2.��������
		conn = JdbcUtils.getConnection();
		//3.�������
		st = conn.createStatement();
		//4��ִ�����
		String sql = "select * from students";
		rs = st.executeQuery(sql);
		//5��������
		while(rs.next()) {
			System.out.println(rs.getInt("id") + "\t"
						+ rs.getString("name") + "\t"+rs.getInt("age"));
		}
		//6���ͷſռ�
		JdbcUtils.free(rs, st, conn);
	}
	
	public static void base() throws Exception {
		//1.ע������
		//DriverManager.registerDriver(new com.mysql.jdbc.Driver());
		//System.setProperty("jdbc.drivers", "com.mysql.jdbc.Driver");
		Class.forName("com.mysql.jdbc.Driver");
		
		//2.��������
		String url = "jdbc:mysql://localhost:3306/school";
		String admin = "root";
		String password = "root";
		Connection conn = DriverManager.getConnection(url, admin, password);
		
		//3.�������
		Statement st = conn.createStatement();
		
		//4��ִ�����
		String sql = "select * from students";
		ResultSet rs = st.executeQuery(sql);
		
		//5��������
		while(rs.next()) {
			System.out.println(rs.getObject("id") + "\t"
						+ rs.getObject("name") + "\t"+rs.getObject("age"));
		}
		//6���ͷſռ�
		rs.close();
		st.close();
		conn.close();			
	}

	
}
