package com.excilys.formation.computerdatabase.persist.connection;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import org.apache.log4j.Logger;

public class ConnectionFactory {
	private static final String CONFIG_FILENAME = "config_bd.properties";
	private static String USER_NAME;
	private static String USER_PWD;
	private static String URL;
	private static String DRIVER;
	private Connection conn;
	private static ConnectionFactory instance = null;
	private static final Logger LOGGER = Logger.getLogger(ConnectionFactory.class);

	static {
		try {
			Properties prop = loadProp(CONFIG_FILENAME);
			URL = prop.getProperty("url", "");
			USER_NAME = prop.getProperty("user", "");
			USER_PWD = prop.getProperty("password", "");
			DRIVER = prop.getProperty("driver", "");
		} catch (Exception e) {
			LOGGER.error(e);
		}
	}

	private ConnectionFactory() {
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			LOGGER.error(e);
		}
	}

	public synchronized static ConnectionFactory getConnectionManager() {
		if (instance == null) {
				instance = new ConnectionFactory();
		}
		return instance;
	}

	public static Properties loadProp(String filename) throws IOException {
		Properties properties = new Properties();
		InputStream input = ConnectionFactory.class.getClassLoader().getResourceAsStream(filename);
		if (input != null) {
			properties.load(input);
			return properties;
		}
//		if (new File(CONFIG_FILENAME).exists()) {
//			FileInputStream file = new FileInputStream(new File(CONFIG_FILENAME));
//			properties.load(file);
//			return properties;
//		}
		return null;
	}

	public Connection getConn() {
		try {
			return DriverManager.getConnection(URL, USER_NAME, USER_PWD);
		} catch (SQLException e) {
			LOGGER.error(e);
			throw new RuntimeException();
		}
	}
	
	public void closeConnection(Connection conn, Statement stmt) {
		closeConnection(conn, stmt, null);
	}
	public void closeConnection(Connection conn, Statement stmt, ResultSet rs) {
		try {
			if (conn != null)
				conn.close();
			if (stmt != null)
				stmt.close();
			if (rs != null)
				rs.close();
		} catch (SQLException e) {
			String message = new StringBuilder("Couldn't close jdbc connection: ").append(e.getMessage()).toString();
			throw new RuntimeException(message);
		}
	}
}
