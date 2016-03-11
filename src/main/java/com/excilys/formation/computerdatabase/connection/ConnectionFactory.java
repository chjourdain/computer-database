package com.excilys.formation.computerdatabase.connection;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {
	private static final String CONFIG_FILENAME = "config_bd.properties";
	private static String USER_NAME;// = "admincdb";
	private static String USER_PWD;// = "qwerty1234";
	private static String URL;// =								// "jdbc:mysql://127.0.0.1:3306/computer-database-db?zeroDateTimeBehavior=convertToNull";
	private static Connection conn;
	private static ConnectionFactory instance = null;

	static {
		try {
			// chargement des propriétés
			Properties prop = loadProp(CONFIG_FILENAME);
			URL = prop.getProperty("url", "");
			USER_NAME = prop.getProperty("user", "");
			USER_PWD = prop.getProperty("password", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Connection getConn() {
		return conn;
	}

	private ConnectionFactory() throws ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver");
		try {
			conn = DriverManager.getConnection(URL, USER_NAME, USER_PWD);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public synchronized static ConnectionFactory getConnectionManager() {
		if (instance == null) {
			try {
				instance = new ConnectionFactory();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		return instance;
	}

	public static Properties loadProp(String filename) throws IOException, FileNotFoundException {
		Properties properties = new Properties();
		InputStream input =  ConnectionFactory.class.getClassLoader().getResourceAsStream(filename);
			properties.load(input);
			return properties;
		
	}
}
