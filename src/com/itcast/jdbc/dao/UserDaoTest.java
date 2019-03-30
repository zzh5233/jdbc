package com.itcast.jdbc.dao;

import java.util.Date;

import com.itcast.jdbc.dao.refactor.AccountDaoImpl;
import com.itcast.jdbc.dao.refactor.UserDaoImpl;
import com.itcast.jdbc.dao.refactor.UserDaoImpl2;
import com.itcast.jdbc.domain.Account;
import com.itcast.jdbc.domain.User;

public class UserDaoTest {
	public static void main(String[] args) {
		UserDao userDao = DaoFactory.getInstance().getUserDao();
		
		User user = new User();
		user.setName("zhangsan");
		user.setPassword("root");
		user.setDate(new Date());
		
//		userDao.addUser(user);
		
//		User u = userDao.readUser("xiaoming", null);
//		System.out.println("user:" + u);
		
//		User u2 = userDao.getUser(2);
//		System.out.println("user:" + u2);		

		User us = userDao.getUser(2);
		System.out.println("user:" + us);		
		us.setPassword("111122");
		System.out.println("user:" + us);		
//		userDao.updateUser(us);
		
//		User us2 = userDao.getUser(2);
//		userDao.deleteUser(us2);
		
//		UserDaoImpl udi = new UserDaoImpl();
//		User user = udi.findUser("xiaoming","root222");
//		System.out.println(user);
		
//		UserDaoImpl2 udi2 = new UserDaoImpl2();
//		String name = udi2.findUserName(3);
//		System.out.println(name);
		
//		AccountDaoImpl adi = new AccountDaoImpl();
//		Account account = adi.findAccount(1);
//		System.out.println(account);
	}
}
