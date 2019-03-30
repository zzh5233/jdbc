package com.itcast.jdbc.domain;

import java.util.Date;

public class User {
	private int id;
	private String name;
	private String password;
	private Date date;
	public User() {
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	@Override
	public String toString() {
		return "id->" + id + ",name->" + name + ",password->" + password + ",date->" + date;
	}
}
