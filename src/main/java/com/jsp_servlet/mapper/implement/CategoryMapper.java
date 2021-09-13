package com.jsp_servlet.mapper.implement;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.jsp_servlet.mapper.RowMapper;
import com.jsp_servlet.model.CategoryModel;

public class CategoryMapper implements RowMapper<CategoryModel> {

	@Override
	public CategoryModel mapRow(ResultSet resultSet) {
		CategoryModel categoryModel = new CategoryModel();

		try {
			categoryModel.setId(resultSet.getLong("id"));
			categoryModel.setCode(resultSet.getString("code"));
			categoryModel.setName(resultSet.getString("name"));
			categoryModel.setCreateDate(resultSet.getTimestamp("createDate"));
			categoryModel.setModifiedDate(resultSet.getTimestamp("modifiedDate"));
			categoryModel.setCreateBy(resultSet.getString("createBy"));
			categoryModel.setModifiedBy(resultSet.getString("modifiedBy"));

			return categoryModel;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

}
