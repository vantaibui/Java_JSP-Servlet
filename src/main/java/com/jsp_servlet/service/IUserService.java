package com.jsp_servlet.service;

import com.jsp_servlet.model.UserModel;

public interface IUserService {
	UserModel findByUserNameAndPasswordAndStatus(String userName, String password, Integer status);
}
