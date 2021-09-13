package com.jsp_servlet.dao.implement;

import java.util.List;

import com.jsp_servlet.dao.ICategoryDAO;
import com.jsp_servlet.mapper.implement.CategoryMapper;
import com.jsp_servlet.model.CategoryModel;

public class CategoryDAO extends AbstractDAO<CategoryModel> implements ICategoryDAO {

	@Override
	public List<CategoryModel> findAll() {
		String sql = "SELECT * FROM category";
		
		return query(sql, new CategoryMapper());
	}

}
