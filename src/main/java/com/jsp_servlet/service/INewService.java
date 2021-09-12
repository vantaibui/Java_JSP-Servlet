package com.jsp_servlet.service;

import java.util.List;

import com.jsp_servlet.model.NewModel;

public interface INewService {
	List<NewModel> findByCategoryId(Long categoryId);
}
