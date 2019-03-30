package com.itcast.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class SQLInject {
	public static void main(String[] args) throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		//2.建立连接
		conn = JdbcUtils.getConnection();
		//3.创建语句
		String sql = "select id,name,age from students where name=?";
		ps = conn.prepareStatement(sql);
		ps.setNString(1, "lisi");
		//4、执行语句
		rs = ps.executeQuery();
		//5、处理结果
		while(rs.next()) {
			System.out.println(rs.getInt("id") + "\t"
						+ rs.getString("name") + "\t"+rs.getInt("age"));
		}
		//6、释放空间
		JdbcUtils.free(rs, ps, conn);		
	}
}
