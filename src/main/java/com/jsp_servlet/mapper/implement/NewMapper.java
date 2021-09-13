package com.jsp_servlet.mapper.implement;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.jsp_servlet.mapper.RowMapper;
import com.jsp_servlet.model.NewModel;

public class NewMapper implements RowMapper<NewModel> {

	@Override
	public NewModel mapRow(ResultSet resultSet) {
		NewModel newModel = new NewModel();

		try {
			newModel.setId(resultSet.getLong("id"));
			newModel.setTitle(resultSet.getString("title"));
			newModel.setCategoryId(resultSet.getLong("categoryid"));
			
			return newModel;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

	}

}
