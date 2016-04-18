package com.excilys.formation.computerdatabase.persist.connection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Class to manage the connection, create return and close the connection.
 * 
 * @author charles
 *
 */
public class ConnectionFactory {
    private static final Logger LOGGER = LoggerFactory
	    .getLogger(com.excilys.formation.computerdatabase.persist.connection.ConnectionFactory.class);
   
    public static void closeConnection(Connection conn, Statement stmt) {
	closeConnection(conn, stmt, null);
    }

    public static void closeConnection(Connection conn, Statement stmt, ResultSet rs) {
	try {
	    if (conn != null) {
		conn.close();
	    }
	    if (stmt != null) {
		stmt.close();
	    }
	    if (rs != null) {
		rs.close();
	    }
	} catch (SQLException e) {
	    String message = new StringBuilder("Couldn't close jdbc connection: ").append(e.getMessage()).toString();
	    throw new RuntimeException(message);
	}
    }
}
