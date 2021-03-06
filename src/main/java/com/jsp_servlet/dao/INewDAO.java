package com.jsp_servlet.dao;

import java.util.List;

import com.jsp_servlet.model.NewModel;
import com.jsp_servlet.paging.Pageble;

public interface INewDAO extends GenericDAO<NewModel> {
	List<NewModel> findByCategoryId(Long categoryId);

	Long save(NewModel model);

	NewModel findOne(Long id);

	void update(NewModel updateNew);

	void delete(Long id);

//	List<NewModel> findAll(Integer offset, Integer limit, String sortName, String sortBy);
	
	List<NewModel> findAll(Pageble pageble);
	
	int getTotalItem();

}
