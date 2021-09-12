package com.jsp_servlet.service.implement;

import java.util.List;

import com.jsp_servlet.dao.ICategoryDAO;
import com.jsp_servlet.dao.implement.CategoryDAO;
import com.jsp_servlet.model.CategoryModel;
import com.jsp_servlet.service.ICategoryService;

public class CategoryService implements ICategoryService {

	private ICategoryDAO categoryDAO;
	

	public CategoryService() {
		categoryDAO = new CategoryDAO();
	}
	
	@Override
	public List<CategoryModel> findAll() {
		return categoryDAO.findAll();
	}

}
