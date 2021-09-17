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
			newModel.setThmbnail(resultSet.getString("thumbnail"));
			newModel.setShortDescription(resultSet.getString("shortdescription"));
			newModel.setContent(resultSet.getString("content"));
			newModel.setCategoryId(resultSet.getLong("categoryid"));

			newModel.setCreateBy(resultSet.getString("createby"));
			newModel.setCreateDate(resultSet.getTimestamp("createdate"));
			if (resultSet.getString("modifiedby") != null) {
				newModel.setModifiedBy(resultSet.getString("modifiedby"));
			}
			if (resultSet.getTimestamp("modifieddate") != null) {
				newModel.setModifiedBy(resultSet.getString("modifieddate"));
			}
			return newModel;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

	}

}
