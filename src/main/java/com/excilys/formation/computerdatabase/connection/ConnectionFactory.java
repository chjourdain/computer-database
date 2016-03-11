package com.excilys.formation.computerdatabase.connection;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.log4j.Logger;

public class ConnectionFactory {
	private static final String CONFIG_FILENAME = "config_bd.properties";
	private static String USER_NAME;
	private static String USER_PWD;
	private static String URL;
	private Connection conn;
	private static ConnectionFactory instance = null;
	private static final Logger logger = Logger.getLogger(ConnectionFactory.class);

	static {
		try {
			Properties prop = loadProp(CONFIG_FILENAME);
			URL = prop.getProperty("url", "");
			USER_NAME = prop.getProperty("user", "");
			USER_PWD = prop.getProperty("password", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
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
		InputStream input = ConnectionFactory.class.getClassLoader().getResourceAsStream(filename);
		if (input != null) {
			properties.load(input);
			return properties;
		}
		if (new File(CONFIG_FILENAME).exists()) {
			FileInputStream file = new FileInputStream(new File(CONFIG_FILENAME));
			properties.load(file);
			return properties;
		}
		return null;
	}

	public Connection getConn() {
		return conn;
	}
}
