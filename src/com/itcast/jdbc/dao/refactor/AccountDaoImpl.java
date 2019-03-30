package com.itcast.jdbc.dao.refactor;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.itcast.jdbc.domain.Account;

public class AccountDaoImpl extends AbstractDao {
	
	public Account findAccount(int id) {
		String sql = "select id,name,money from account where id =?";
		Object[] args = new Object[] {id};
		Object account = super.find(sql, args);
		return (Account)account;
	}
	
	@Override
	protected Object rowMapper(ResultSet rs) throws SQLException {
		Account account = new Account();
		account.setId(rs.getInt("id"));
		account.setName(rs.getString("name"));
		account.setMoney(rs.getFloat("money"));
		return account;
	}

}
