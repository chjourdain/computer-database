package com.excylis.formation.test.connection;

import java.sql.Connection;

import com.excilys.formation.computerdatabase.connection.ConnectionManager;

import junit.framework.TestCase;

public class TestConnectionManager extends TestCase{
	
	public void testNoException(){
		ConnectionManager m = ConnectionManager.getConnectionManager();
		Connection conn = m.getConn();
		
		
	}

}
