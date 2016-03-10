package com.excilys.formation.computerdatabase.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	private static final String USER_NAME = "admincdb";
	private static final String USER_PWD = "qwerty1234";
	private static final String URL = "jdbc:mysql://127.0.0.1:3306/computer-database-db?zeroDateTimeBehavior=convertToNull";
	private static Connection conn;

	public Connection getConn() {
		return conn;
	}

	private static ConnectionFactory instance = null;

	private ConnectionFactory() throws ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver");

		try {
			conn = DriverManager.getConnection(URL, USER_NAME, USER_PWD);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static ConnectionFactory getConnectionManager() {
		if (instance == null) {
			try {
				instance = new ConnectionFactory();
			} catch (ClassNotFoundException e) {

				e.printStackTrace();
			}
		}
		return instance;
	}

}
