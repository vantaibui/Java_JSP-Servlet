package com.jsp_servlet.service.implement;

import java.util.List;

import com.jsp_servlet.dao.INewDAO;
import com.jsp_servlet.dao.implement.NewDAO;
import com.jsp_servlet.model.NewModel;
import com.jsp_servlet.service.INewService;

public class NewService implements INewService {

	private INewDAO newDAO;

	public NewService() {
		newDAO = new NewDAO();
	}

	@Override
	public List<NewModel> findByCategoryId(Long categoryId) {
		return newDAO.findByCategoryId(categoryId);
	}

}
