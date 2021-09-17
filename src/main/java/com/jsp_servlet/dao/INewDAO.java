package com.jsp_servlet.dao;

import java.util.List;

import com.jsp_servlet.model.NewModel;

public interface INewDAO extends GenericDAO<NewModel> {
	List<NewModel> findByCategoryId(Long categoryId);

	Long save(NewModel model);

	NewModel findOne(Long id);

	void update(NewModel updateNew);

	void delete(Long id);

	List<NewModel> findAll(Integer offset, Integer limit);
	
	int getTotalItem();
}
