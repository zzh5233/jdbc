package com.itcast.jdbc.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class DaoFactory {
	private static UserDao userDao= null;
	private static DaoFactory instance = new DaoFactory();
	
	private DaoFactory() {
		try {
			Properties prop = new Properties();
			///new FileInputStream(new File("src/daoconfig.properties"));
			InputStream inStream = DaoFactory.class.getClassLoader()
						.getResourceAsStream("daoconfig.properties");
					
			prop.load(inStream);
			String userDaoClass = prop.getProperty("userDaoClass");
			userDao = (UserDao) Class.forName(userDaoClass).newInstance();
		}catch(Exception e) {
			throw new ExceptionInInitializerError(e);
		}
	}
	public static DaoFactory getInstance() {
		return instance;
	}
	public  UserDao getUserDao() {
		return userDao;
	}
}
