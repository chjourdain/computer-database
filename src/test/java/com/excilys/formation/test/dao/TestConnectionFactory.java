package com.excilys.formation.test.dao;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.Test;

import com.excilys.formation.computerdatabase.persist.connection.ConnectionFactory;

public class TestConnectionFactory {

    @Test
    public void TestConnection() {
	Connection con = ConnectionFactory.getConnectionManager().getConn();
	try {
	    assertTrue(con.isValid(1000));
	} catch (SQLException e) {
	    e.printStackTrace();
	}

    }

}
