package com.jsp_servlet.service;

import java.util.List;

import com.jsp_servlet.model.NewModel;
import com.jsp_servlet.paging.Pageble;

public interface INewService {
	List<NewModel> findByCategoryId(Long categoryId);

	NewModel save(NewModel newModel);

	NewModel update(NewModel updateNew);

	void delete(Long[] ids);
	
//	List<NewModel> findAll(Integer offset, Integer limit, String sortName, String sortBy);
	
	List<NewModel> findAll(Pageble pageble);
	
	int getTotalItem();
}
