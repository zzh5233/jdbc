package com.itcast.jdbc.domain;

import java.util.Date;

public class Bean {
	private int id;
	private String name;
	private Date date;
	public Bean() {
	}
	@Override
	public String toString() {
		return "id->"+id+",name->"+name+",date->"+date;
	}
	public Bean(int id) {
		this.id = id;
	}
	public Bean(int id, String name) {
		this.id = id;
		this.name = name;
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
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
}
