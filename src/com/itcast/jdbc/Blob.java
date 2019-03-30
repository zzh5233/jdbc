package com.itcast.jdbc;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Blob {
	public static void main(String[] args) throws Exception {
		//create();
		read();
	}

	private static void read() throws Exception {
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			conn = JdbcUtils.getConnection();
			st = conn.createStatement();
			rs = st.executeQuery("select blob_text from blob_test");
			while(rs.next()) {
				InputStream in = rs.getBinaryStream(1);
				
				File file = new File("blob_bar.jpg");
				OutputStream out = new BufferedOutputStream(new FileOutputStream(file));
				byte[] buff = new byte[1024];
				for(int i = 0;(i= in.read(buff))>0;) {
					out.write(buff, 0, i);
				}
				out.close();
				in.close();
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
			conn = JdbcUtils.getConnection();
			String sql = "insert into blob_test(blob_text) values (?)";
			ps = conn.prepareStatement(sql);
			
			File file = new File("E:\\Codes\\666e000106ee673bc0ec.jpg");
			InputStream in = new BufferedInputStream(new FileInputStream(file));
			
			ps.setBinaryStream(1, in, file.length());
			//4¡¢Ö´ÐÐÓï¾ä
			int i = ps.executeUpdate();
				
			System.out.println("i =" + i);
		}finally {
			JdbcUtils.free(rs, ps, conn);
		}	
	}
}
