package com.itcast.jdbc.datasource;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.LinkedList;
import java.util.logging.Logger;

import javax.sql.DataSource;

public class MyDataSource2 implements DataSource{
	private static String url = "jdbc:mysql://localhost:3306/school";
	private static String user = "root";
	private static String password = "root";
	
	private static int initCount = 3;
	private static int maxCount =5;
	int currentCount = 0;
	
	LinkedList<Connection> connectionsPool = new LinkedList <Connection>();
	
	public MyDataSource2()  {
		try {
			for(int i = 0; i < initCount; i++) {
				this.connectionsPool.addLast(createConnection());
				this.currentCount++;
			}
		} catch (SQLException e) {
			throw new ExceptionInInitializerError();
		}
	}
	public Connection getConnection() throws SQLException {
		synchronized (connectionsPool) {
			if(this.connectionsPool.size()>0)
				return this.connectionsPool.removeLast();
			
			if(this.currentCount < maxCount) {
				this.currentCount++;
				return this.createConnection();
			}
			throw new SQLException("û��������");
		}
	}
	public void free(Connection conn) {
			this.connectionsPool.addLast(conn);
	}
	private Connection createConnection() throws SQLException {
		Connection realConn = DriverManager.getConnection(url, user, password);
		//MyConnection myConnection = new MyConnection(realConn,this);
		MyConnectionHandler proxy = new MyConnectionHandler(this);
		return proxy.bind(realConn);
	}
	@Override
	public PrintWriter getLogWriter() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void setLogWriter(PrintWriter out) throws SQLException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void setLoginTimeout(int seconds) throws SQLException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public int getLoginTimeout() throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public Logger getParentLogger() throws SQLFeatureNotSupportedException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public <T> T unwrap(Class<T> iface) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public boolean isWrapperFor(Class<?> iface) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public Connection getConnection(String username, String password) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	
}
