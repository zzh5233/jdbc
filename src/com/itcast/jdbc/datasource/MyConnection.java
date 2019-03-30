package com.itcast.jdbc.datasource;

import java.sql.Array;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.NClob;
import java.sql.PreparedStatement;
import java.sql.SQLClientInfoException;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.SQLXML;
import java.sql.Savepoint;
import java.sql.Statement;
import java.sql.Struct;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Executor;

public class MyConnection implements Connection{
	private Connection realConnection;
	private MyDataSource2 dataSource;
	private int maxUserCount = 5;
	private int currentUserCount = 0;

	
	MyConnection(Connection connection,MyDataSource2 dataSource){
		this.realConnection = connection;
		this.dataSource = dataSource;
	}

	@Override
	public void close() throws SQLException {
		this.currentUserCount++;
		if(this.currentUserCount < this.maxUserCount) 
			this.dataSource.connectionsPool.addLast(this);
		else {
			this.realConnection.close();
			this.dataSource.currentCount--;
		}
	}

	@Override
	public <T> T unwrap(Class<T> iface) throws SQLException {
		return this.realConnection.unwrap(iface);
	}

	@Override
	public boolean isWrapperFor(Class<?> iface) throws SQLException {
		return this.realConnection.isWrapperFor(iface);
	}

	@Override
	public Statement createStatement() throws SQLException {
		return this.realConnection.createStatement();
	}

	@Override
	public PreparedStatement prepareStatement(String sql) throws SQLException {
		return this.realConnection.prepareStatement(sql);
	}

	@Override
	public CallableStatement prepareCall(String sql) throws SQLException {
		return this.realConnection.prepareCall(sql);
	}

	@Override
	public String nativeSQL(String sql) throws SQLException {
		return this.realConnection.nativeSQL(sql);
	}

	@Override
	public void setAutoCommit(boolean autoCommit) throws SQLException {
		this.realConnection.setAutoCommit(autoCommit);		
	}

	@Override
	public boolean getAutoCommit() throws SQLException {
		return this.realConnection.getAutoCommit();
	}

	@Override
	public void commit() throws SQLException {
		this.realConnection.commit();
	}

	@Override
	public void rollback() throws SQLException {
		this.realConnection.rollback();		
	}

	@Override
	public boolean isClosed() throws SQLException {
		return this.realConnection.isClosed();
	}

	@Override
	public DatabaseMetaData getMetaData() throws SQLException {
		return this.realConnection.getMetaData();
	}

	@Override
	public void setReadOnly(boolean readOnly) throws SQLException {
		this.realConnection.setReadOnly(readOnly);		
	}

	@Override
	public boolean isReadOnly() throws SQLException {
		return this.realConnection.isReadOnly();
	}

	@Override
	public void setCatalog(String catalog) throws SQLException {
		this.realConnection.setCatalog(catalog);		
	}

	@Override
	public String getCatalog() throws SQLException {
		return this.realConnection.getCatalog();
	}

	@Override
	public void setTransactionIsolation(int level) throws SQLException {
		this.realConnection.setTransactionIsolation(level);
	}

	@Override
	public int getTransactionIsolation() throws SQLException {
		return this.realConnection.getTransactionIsolation();
	}

	@Override
	public SQLWarning getWarnings() throws SQLException {
		return this.realConnection.getWarnings();
	}

	@Override
	public void clearWarnings() throws SQLException {
		this.realConnection.clearWarnings();		
	}

	@Override
	public Statement createStatement(int resultSetType, int resultSetConcurrency) throws SQLException {
		return this.realConnection.createStatement(resultSetType, resultSetConcurrency);
	}

	@Override
	public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency)
			throws SQLException {
		return this.realConnection.prepareStatement(sql, resultSetType, resultSetConcurrency);
	}

	@Override
	public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency) throws SQLException {
		return this.realConnection.prepareCall(sql, resultSetType, resultSetConcurrency);
	}

	@Override
	public Map<String, Class<?>> getTypeMap() throws SQLException {
		return this.realConnection.getTypeMap();
	}

	@Override
	public void setTypeMap(Map<String, Class<?>> map) throws SQLException {
		this.realConnection.setTypeMap(map);
	}

	@Override
	public void setHoldability(int holdability) throws SQLException {
		this.realConnection.setHoldability(holdability);
	}

	@Override
	public int getHoldability() throws SQLException {
		return this.realConnection.getHoldability();
	}

	@Override
	public Savepoint setSavepoint() throws SQLException {
		return this.realConnection.setSavepoint();
	}

	@Override
	public Savepoint setSavepoint(String name) throws SQLException {
		return this.realConnection.setSavepoint(name);
	}

	@Override
	public void rollback(Savepoint savepoint) throws SQLException {
		this.realConnection.rollback(savepoint);
	}

	@Override
	public void releaseSavepoint(Savepoint savepoint) throws SQLException {
		this.realConnection.releaseSavepoint(savepoint);		
	}

	@Override
	public Statement createStatement(int resultSetType, int resultSetConcurrency, int resultSetHoldability)
			throws SQLException {
		return this.realConnection.createStatement(resultSetType, resultSetConcurrency, resultSetHoldability);
	}

	@Override
	public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency,
			int resultSetHoldability) throws SQLException {
		return this.realConnection.prepareStatement(sql, resultSetType, resultSetConcurrency, resultSetHoldability);
	}

	@Override
	public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency,
			int resultSetHoldability) throws SQLException {
		return this.realConnection.prepareCall(sql, resultSetType, resultSetConcurrency, resultSetHoldability);
	}

	@Override
	public PreparedStatement prepareStatement(String sql, int autoGeneratedKeys) throws SQLException {
		return this.realConnection.prepareStatement(sql, autoGeneratedKeys);
	}

	@Override
	public PreparedStatement prepareStatement(String sql, int[] columnIndexes) throws SQLException {
		return this.realConnection.prepareStatement(sql, columnIndexes);
	}

	@Override
	public PreparedStatement prepareStatement(String sql, String[] columnNames) throws SQLException {
		return this.realConnection.prepareStatement(sql, columnNames);
	}

	@Override
	public Clob createClob() throws SQLException {
		return this.realConnection.createClob();
	}

	@Override
	public Blob createBlob() throws SQLException {
		return this.realConnection.createBlob();
	}

	@Override
	public NClob createNClob() throws SQLException {
		return this.realConnection.createNClob();
	}

	@Override
	public SQLXML createSQLXML() throws SQLException {
		return this.realConnection.createSQLXML();
	}

	@Override
	public boolean isValid(int timeout) throws SQLException {
		return this.realConnection.isValid(timeout);
	}

	@Override
	public void setClientInfo(String name, String value) throws SQLClientInfoException {
		this.realConnection.setClientInfo(name, value);
	}

	@Override
	public void setClientInfo(Properties properties) throws SQLClientInfoException {
		this.realConnection.setClientInfo(properties);
	}

	@Override
	public String getClientInfo(String name) throws SQLException {
		return this.realConnection.getClientInfo(name);
	}

	@Override
	public Properties getClientInfo() throws SQLException {
		return this.realConnection.getClientInfo();
	}

	@Override
	public Array createArrayOf(String typeName, Object[] elements) throws SQLException {
		return this.realConnection.createArrayOf(typeName, elements);
	}

	@Override
	public Struct createStruct(String typeName, Object[] attributes) throws SQLException {
		return this.realConnection.createStruct(typeName, attributes);
	}

	@Override
	public void setSchema(String schema) throws SQLException {
		this.realConnection.setSchema(schema);		
	}

	@Override
	public String getSchema() throws SQLException {
		return this.realConnection.getSchema();
	}

	@Override
	public void abort(Executor executor) throws SQLException {
		this.realConnection.abort(executor);		
	}

	@Override
	public void setNetworkTimeout(Executor executor, int milliseconds) throws SQLException {
		this.realConnection.setNetworkTimeout(executor, milliseconds);		
	}

	@Override
	public int getNetworkTimeout() throws SQLException {
		return this.realConnection.getNetworkTimeout();
	}
	
}
