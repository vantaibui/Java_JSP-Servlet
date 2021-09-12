package com.jsp_servlet.dao.implement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.jsp_servlet.dao.ICategoryDAO;
import com.jsp_servlet.model.CategoryModel;

public class CategoryDAO extends AbstractDAO implements ICategoryDAO {


	@Override
	public List<CategoryModel> findAll() {
		List<CategoryModel> results = new ArrayList<CategoryModel>();

		Connection connection = getConnection();
		String sql = "SELECT * FROM category";
		PreparedStatement preparedStatement = null;

		if (connection != null) {
			try {
				preparedStatement = connection.prepareStatement(sql);
				ResultSet resultSet = preparedStatement.executeQuery(sql);

				while (resultSet.next()) {
					CategoryModel categoryModel = new CategoryModel();

					categoryModel.setId(resultSet.getLong("id"));
					categoryModel.setCode(resultSet.getString("code"));
					categoryModel.setName(resultSet.getString("name"));
					categoryModel.setCreateDate(resultSet.getTimestamp("createDate"));
					categoryModel.setModifiedDate(resultSet.getTimestamp("modifiedDate"));
					categoryModel.setCreateBy(resultSet.getString("createBy"));
					categoryModel.setModifiedBy(resultSet.getString("modifiedBy"));

					results.add(categoryModel);
				}
				return results;

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					if (connection != null) {
						connection.close();
					}
					if (preparedStatement != null) {
						preparedStatement.close();
					}

				} catch (SQLException e) {
					e.printStackTrace();
					return null;
				}
			}
		}

		return null;
	}

}
