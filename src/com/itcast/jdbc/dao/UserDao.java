package com.itcast.jdbc.dao;

import com.itcast.jdbc.domain.User;

public interface UserDao {
	
	public void addUser(User user);
	public User readUser(String loginName,String password);
	public void updateUser(User user);
	public User getUser(int userId);
	public void deleteUser(User user);
}
