package com.itcast.jdbc.dao.refactor;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface RowMapper {
	 public Object mapRow(ResultSet rs) throws SQLException;
	
}
