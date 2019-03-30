package com.itcast.jdbc;

import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.itcast.jdbc.domain.Bean;
import com.itcast.jdbc.domain.User;

public class ORMTest {

	public static void main(String[] args) throws Exception {
		Bean bean = (Bean) getObject("select id as Id,name as Name,date as Date "
				+ "from bean where id=1",Bean.class);
		System.out.println(bean);
		
		User user = (User) getObject("select id as Id,name as Name,password as Password,date as Date "
				+ "from user where id=1",User.class);
		System.out.println(user);
	}
	private static Object getObject(String sql,Class clazz) throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Object obj = null;
		try {
			conn = JdbcUtils.getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			int count = rsmd.getColumnCount();	
			String[] colNames = new String[count];
			for(int i=1; i<=count; i++) {
				colNames[i-1]= rsmd.getColumnLabel(i);
			}		
			Method[] ms = clazz.getMethods();
			if(rs.next()) {
				obj = clazz.newInstance();
				for(int i = 0; i < colNames.length; i++) {
					String colName = colNames[i];
					String methodName = "set" +colName;
					for(Method m : ms) {
						if(methodName.equals(m.getName())) {
							m.invoke(obj,rs.getObject(colName));
						}
					}
				}
			}
		}finally {
			JdbcUtils.free(rs, ps, conn);
		}
		return obj;
	}
}
