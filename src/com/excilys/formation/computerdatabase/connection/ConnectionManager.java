package com.excilys.formation.computerdatabase.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
	private static final String USER_NAME = "admincdb";
	private static final String USER_PWD = "qwerty1234";
	private static final String URL = "jdbc:mysql://127.0.0.1:3306/computer-database-db?zeroDateTimeBehavior=convertToNull";
	private static Connection conn;

	public static Connection getConn() {
		return conn;
	}

	private static ConnectionManager instance = null;

	private ConnectionManager() throws ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver");

		try {
			conn = DriverManager.getConnection(URL, USER_NAME, USER_PWD);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static ConnectionManager getConnectionManager() {
		if (instance == null) {
			try {
				instance = new ConnectionManager();
			} catch (ClassNotFoundException e) {

				e.printStackTrace();
			}
		}
		return instance;
	}

}
