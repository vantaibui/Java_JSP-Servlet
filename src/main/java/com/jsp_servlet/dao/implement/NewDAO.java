package com.jsp_servlet.dao.implement;

import static java.sql.Statement.RETURN_GENERATED_KEYS;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.jsp_servlet.dao.INewDAO;
import com.jsp_servlet.mapper.implement.NewMapper;
import com.jsp_servlet.model.NewModel;

public class NewDAO extends AbstractDAO<NewModel> implements INewDAO {

	@Override
	public List<NewModel> findByCategoryId(Long categoryId) {
		// init parameter
		String sql = "SELECT * FROM news WHERE categoryid = ?";

		return query(sql, new NewMapper(), categoryId);

	}

	@Override
	public Long save(NewModel model) {
		String sql = "INSERT INTO news (title, content, categoryid) VALUES (?, ?, ?)";

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		Long id = null;
		try {
			connection = getConnection();
			connection.setAutoCommit(false);
			preparedStatement = connection.prepareStatement(sql, RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, model.getTitle());
			preparedStatement.setString(2, model.getContent());
			preparedStatement.setLong(3, model.getCategoryId());

			preparedStatement.executeUpdate();
			resultSet = preparedStatement.getGeneratedKeys();
			if (resultSet.next()) {
				id = resultSet.getLong(1);
			}
			connection.commit();
			return id;
		} catch (SQLException e) {
			if (connection != null) {
				try {
					connection.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			return null;
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

				return null;
			}
		}
	}

	@Override
	public NewModel saveNew(NewModel newModel) {
		// TODO Auto-generated method stub
		return null;
	}

}
