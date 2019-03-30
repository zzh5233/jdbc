package com.itcast.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ResultSetMateData {

	public static void main(String[] args) throws Exception {
		List<Map<String,Object>> datas = read("select id,name,password from user where id<5");
		System.out.println(datas);
		
	}
	private static List<Map<String,Object>> read(String sql) throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Map<String,Object>> datas= null;
		try {
			conn = JdbcUtils.getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			ResultSetMetaData rsmd = rs.getMetaData();
			int count = rsmd.getColumnCount();	//获取查询结果集的列数
			String[] colName = new String[count];	//构建装得下这个列的所有名字的数组
			//获取所有列的名字
			for(int i=1; i<=count; i++) {
				colName[i-1]= rsmd.getColumnLabel(i);
			}		
			//5、处理结果
			datas = new ArrayList<Map<String,Object>>();
			while(rs.next()) {
				Map<String,Object> data = new HashMap<String,Object>();
				for(int i=0; i<count; i++) {
					data.put(colName[i], rs.getObject(colName[i]));
				}
				datas.add(data);
			}
		}finally {
			JdbcUtils.free(rs, ps, conn);
		}
		return datas;
	}
}
