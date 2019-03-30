package com.itcast.jdbc.datasource;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;

public class MyConnectionHandler implements InvocationHandler {
	private Connection realConnection;
	private Connection warpedConnection;
	private MyDataSource2 dataSource;
	private int currentUserCount = 0;
	private int maxUserCount = 5;
	
	MyConnectionHandler(MyDataSource2 dataSource){
		this.dataSource = dataSource;
	}
	Connection bind(Connection realConn) {
		this.realConnection = realConn;
		this.warpedConnection = (Connection) Proxy.newProxyInstance(
				this.getClass().getClassLoader(), new Class[] {Connection.class},
				this );
		return warpedConnection;
	}
	
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) 
		  throws Throwable {
		if("close".equals(method.getName())) {
			this.currentUserCount++;
			if(this.currentUserCount < this.maxUserCount ) 
				this.dataSource.connectionsPool.addLast(this.warpedConnection);
			else {
				this.realConnection.close();
				this.dataSource.currentCount--;
			}
		}
		return method.invoke(this.realConnection, args);
	}

}
