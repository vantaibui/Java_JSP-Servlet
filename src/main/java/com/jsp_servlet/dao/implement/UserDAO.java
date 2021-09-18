package com.jsp_servlet.dao.implement;

import java.util.List;

import com.jsp_servlet.dao.IUserDAO;
import com.jsp_servlet.mapper.implement.UserMapper;
import com.jsp_servlet.model.UserModel;

public class UserDAO extends AbstractDAO<UserModel> implements IUserDAO {

	@Override
	public UserModel findByUserNameAndPasswordAndStatus(String userName, String password, Integer status) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM user AS u ");
		sql.append("INNER JOIN role as r ON r.id = u.roleid ");
		sql.append("WHERE username = ? AND password = ? AND status = ?");

		List<UserModel> users = query(sql.toString(), new UserMapper(), userName, password, status);

		return users.isEmpty() ? null : users.get(0);
	}

}
