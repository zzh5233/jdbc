package com.itcast.jdbc;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Big_text {
	public static void main(String[] args) throws Exception {
		//create();\
		read();
	}

	private static void read() throws Exception {
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			//2.建立连接
			conn = JdbcUtils.getConnection();
			//3.创建语句
			st = conn.createStatement();
	
			//4、执行语句
			rs = st.executeQuery("select big_text from clob_test");
			
			//5、处理结果
			while(rs.next()) {
				//Clob clob = rs.getClob(1);
				//Reader reader = clob.getCharacterStream();
				Reader reader = rs.getCharacterStream(1);
				
				File file = new File("E:\\Codes\\big.java");
				Writer writer = new BufferedWriter(new FileWriter(file));
				char[] buff = new char[1024];
				for(int i = 0;(i= reader.read(buff))>0;) {
					writer.write(buff, 0, i);
				}
				writer.close();
				reader.close();
			}
		}finally {
			JdbcUtils.free(rs, st, conn);
		}
	}

	private static void create() throws SQLException, IOException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			//2.建立连接
			conn = JdbcUtils.getConnection();
			//3.创建语句
			String sql = "insert into clob_test(big_text) values (?)";
			ps = conn.prepareStatement(sql);
			File file = new File("src/com/itcast/jdbc/JdbcSing.java");
			Reader reader = new BufferedReader(new FileReader(file));
			
			ps.setCharacterStream(1, reader, file.length());
			//4、执行语句
			int i = ps.executeUpdate();
				
			System.out.println("i =" + i);
		}finally {
			JdbcUtils.free(rs, ps, conn);
		}	
	}
}
