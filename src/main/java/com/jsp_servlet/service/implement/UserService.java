package com.jsp_servlet.service.implement;

import com.jsp_servlet.dao.IUserDAO;
import com.jsp_servlet.dao.implement.UserDAO;
import com.jsp_servlet.model.UserModel;
import com.jsp_servlet.service.IUserService;

public class UserService implements IUserService {

	private IUserDAO userDAO;

	public UserService() {
		userDAO = new UserDAO();
	}

	@Override
	public UserModel findByUserNameAndPasswordAndStatus(String userName, String password, Integer status) {
		return userDAO.findByUserNameAndPasswordAndStatus(userName, password, status);
	}

}
