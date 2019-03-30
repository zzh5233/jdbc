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
		//2.��������
		conn = JdbcUtils.getConnection();
		//3.�������
		String sql = "select id,name,age from students where name=?";
		ps = conn.prepareStatement(sql);
		ps.setNString(1, "lisi");
		//4��ִ�����
		rs = ps.executeQuery();
		//5��������
		while(rs.next()) {
			System.out.println(rs.getInt("id") + "\t"
						+ rs.getString("name") + "\t"+rs.getInt("age"));
		}
		//6���ͷſռ�
		JdbcUtils.free(rs, ps, conn);		
	}
}
