package com.jsp_servlet.dao.implement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.jsp_servlet.dao.INewDAO;
import com.jsp_servlet.model.NewModel;

public class NewDAO extends AbstractDAO<NewModel> implements INewDAO {
	
	@Override
	public List<NewModel> findByCategoryId(Long categoryId) {

		List<NewModel> results = new ArrayList<NewModel>();

		Connection connection = getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		// init parameter
		String sql = "select * from news where categoryid = ?";
		if (connection != null) {
			try {
				preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setLong(1, categoryId);
				resultSet = preparedStatement.executeQuery();

				while (resultSet.next()) {
					NewModel newModel = new NewModel();

					newModel.setId(resultSet.getLong("id"));
					newModel.setTitle(resultSet.getString("title"));
					newModel.setCategoryId(resultSet.getLong("categoryid"));

					results.add(newModel);

				}
				System.out.println(results);
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
					if (resultSet != null) {
						resultSet.close();
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

		}
		return null;

	}

}
