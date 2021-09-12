package com.jsp_servlet.dao;

import java.util.List;

import com.jsp_servlet.model.CategoryModel;

public interface ICategoryDAO extends GenericDAO {
	List<CategoryModel> findAll();
}
