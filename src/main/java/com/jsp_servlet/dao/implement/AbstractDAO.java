package com.jsp_servlet.dao.implement;

import static java.sql.Statement.RETURN_GENERATED_KEYS;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.jsp_servlet.dao.GenericDAO;
import com.jsp_servlet.mapper.RowMapper;

public class AbstractDAO<T> implements GenericDAO<T> {

	public static Connection getConnection() {
		ResourceBundle resourceBundle = ResourceBundle.getBundle("db");

		String jdbcDriver = resourceBundle.getString("jdbcDriver");
		String hostName = resourceBundle.getString("hostName");
		String dbName = resourceBundle.getString("dbName");
		String connectionURL = hostName + dbName;
		String username = resourceBundle.getString("username");
		String password = resourceBundle.getString("password");

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
			setParameters(preparedStatement, parameters);

			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				results.add(rowMapper.mapRow(resultSet));
			}
//			System.out.println("Results: " + results);
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

	private void setParameters(PreparedStatement preparedStatement, Object... parameters) {
		try {
			for (int i = 0; i < parameters.length; i++) {
				Object parameter = parameters[i];
				int index = i + 1;

				if (parameter instanceof Long) {
					preparedStatement.setLong(index, (long) parameter);
				} else if (parameter instanceof Integer) {
					preparedStatement.setInt(index, (int) parameter);
				} else if (parameter instanceof String) {
					preparedStatement.setString(index, (String) parameter);
				} else if (parameter instanceof Timestamp) {
					preparedStatement.setTimestamp(index, (Timestamp) parameter);
				} else if (parameter == null) {
					preparedStatement.setNull(index, Types.NULL);
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public Long insert(String sql, Object... parameters) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			Long id = null;
			connection = getConnection();
			connection.setAutoCommit(false);
			preparedStatement = connection.prepareStatement(sql, RETURN_GENERATED_KEYS);
			setParameters(preparedStatement, parameters);

			System.out.println("DAO" + sql);
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
				} catch (SQLException e2) {
					e2.printStackTrace();
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
			}
		}

	}

	@Override
	public void update(String sql, Object... parameters) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = getConnection();
			connection.setAutoCommit(false);
			preparedStatement = connection.prepareStatement(sql);
			setParameters(preparedStatement, parameters);

			preparedStatement.executeUpdate();
			System.out.println(sql);
			connection.commit();
		} catch (SQLException e) {
			if (connection != null) {
				try {
					connection.rollback();
				} catch (SQLException e2) {
					e2.printStackTrace();
				}
			}
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
			}
		}

	}

	@Override
	public int count(String sql, Object... parameters) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			int count = 0;
			connection = getConnection();
			preparedStatement = connection.prepareStatement(sql);

			// Set parameters
			setParameters(preparedStatement, parameters);

			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				count = resultSet.getInt(1); // select count(*) from name
			}
			return count;
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
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
