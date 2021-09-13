package com.jsp_servlet.dao;

import java.util.List;

import com.jsp_servlet.mapper.RowMapper;

public interface GenericDAO<T> {
	<T> List<T> query(String sql, RowMapper<T> rowMapper, Object... parameters);
	
	// Câu query, đối tượng trả về, init parameter(Object... parameters ==> multiple params)
}
