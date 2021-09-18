package com.jsp_servlet.mapper.implement;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.jsp_servlet.mapper.RowMapper;
import com.jsp_servlet.model.RoleModel;
import com.jsp_servlet.model.UserModel;

public class UserMapper implements RowMapper<UserModel> {

	@Override
	public UserModel mapRow(ResultSet resultSet) {
		UserModel userModel = new UserModel();
		RoleModel roleModel = new RoleModel();

		try {
			userModel.setId(resultSet.getLong("id"));

			userModel.setId(resultSet.getLong("id"));
			userModel.setUserName(resultSet.getString("username"));
			userModel.setFullName(resultSet.getString("fullname"));
			userModel.setPassword(resultSet.getString("password"));
			userModel.setStattus(resultSet.getInt("status"));

			try {
				roleModel.setCode(resultSet.getString("code"));
				roleModel.setName(resultSet.getString("name"));
				userModel.setRole(roleModel);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}

			return userModel;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

	}

}
