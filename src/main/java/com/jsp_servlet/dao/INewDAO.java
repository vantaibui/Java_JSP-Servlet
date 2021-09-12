package com.jsp_servlet.dao;

import java.util.List;

import com.jsp_servlet.model.NewModel;

public interface INewDAO extends GenericDAO {
	List<NewModel> findByCategoryId(Long categoryId);
}
