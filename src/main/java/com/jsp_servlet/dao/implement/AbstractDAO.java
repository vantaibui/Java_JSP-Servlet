package com.jsp_servlet.dao.implement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.jsp_servlet.dao.GenericDAO;
import com.jsp_servlet.mapper.RowMapper;

public class AbstractDAO<T> implements GenericDAO<T> {

	public static Connection getConnection() {
		String jdbcDriver = "com.mysql.cj.jdbc.Driver";
		String hostName = "jdbc:mysql://localhost:3306/";
		String dbName = "project-jsp_servlet";
		String connectionURL = hostName + dbName;
		String username = "root";
		String password = "root";

		try {
			Class.forName(jdbcDriver);

			return DriverManager.getConnection(connectionURL, username, password);
		} catch (ClassNotFoundException | SQLException e) { // multiple catch
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public <T> List<T> query(String sql, RowMapper<T> rowMapper, Object... parameters) {

		List<T> results = new ArrayList<T>();

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			connection = getConnection();
			preparedStatement = connection.prepareStatement(sql);

			// Set parameters
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				results.add(rowMapper.mapRow(resultSet));
			}

			return results;
		} catch (SQLException e) {
			e.printStackTrace();
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
			}
		}

	}

}
