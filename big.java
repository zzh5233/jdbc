package com.itcast.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


class JdbcSing {
	private static String url = "jdbc:mysql://localhost:3306/school";
	private static String user = "root";
	private static String password = "root";
	private static JdbcSing instance= null;
	
	private JdbcSing() {
	}
	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			throw new ExceptionInInitializerError(e);
		}
	}
	
	public static JdbcSing getInstance() {
		if(instance == null) {
			synchronized (JdbcSing.class) {
				if(instance == null) {
					instance = new JdbcSing();
				}
			}
		}
		return instance;
	}
	
	public Connection getConnection() throws SQLException {
		return DriverManager.getConnection(url, user, password);
	}

	public void free(ResultSet rs,Statement st, Connection conn) {
		try {
			if(rs != null)
				rs.close();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(st != null){
					st.close();
				}
			}catch(Exception e) {
				e.printStackTrace();
			}finally{
				try {
					if(conn != null) 
						conn.close();
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
}
