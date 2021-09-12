package com.jsp_servlet.service;

import java.util.List;

import com.jsp_servlet.model.CategoryModel;

public interface ICategoryService {
	List<CategoryModel> findAll();
}
