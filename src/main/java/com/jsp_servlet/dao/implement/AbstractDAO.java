package com.jsp_servlet.dao.implement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.jsp_servlet.dao.GenericDAO;

public class AbstractDAO implements GenericDAO {

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

}
