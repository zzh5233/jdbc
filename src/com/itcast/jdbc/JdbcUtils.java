package com.itcast.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import com.itcast.jdbc.datasource.MyDataSource2;


public class JdbcUtils {
	private static String url = "jdbc:mysql://localhost:3306/school";
	private static String user = "root";
	private static String password = "root";
	private static MyDataSource2 myDataSource = null;
	private JdbcUtils() {
	}
	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			myDataSource = new MyDataSource2();
		} catch (Exception e) {
			throw new ExceptionInInitializerError(e);
		}
	}
	
	public static Connection getConnection() throws SQLException {
		//return DriverManager.getConnection(url, user, password);
		return myDataSource.getConnection();
	}
	public static DataSource getDataSource() {
		return myDataSource;
	}
	public static void free(ResultSet rs,Statement st, Connection conn) {
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
						//myDataSource.free(conn);
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
}
