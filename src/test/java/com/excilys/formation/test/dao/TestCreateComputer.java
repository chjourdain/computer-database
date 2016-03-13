package com.excilys.formation.test.dao;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import com.excilys.formation.computerdatabase.model.Company;
import com.excilys.formation.computerdatabase.model.Computer;
import com.excilys.formation.computerdatabase.persist.connection.ConnectionFactory;
import com.excilys.formation.computerdatabase.persist.dao.impl.ComputerDaoImpl;

public class TestCreateComputer {// extends JdbcBasedDBTestCase{
	static ComputerDaoImpl computerDao;

	@Before
	public void initialize() {
		computerDao =  ComputerDaoImpl.INSTANCE;
	}

	@Test
	public void testCreateStandart() {
		int a = computerDao.count();
		Computer newOne = new Computer("newOne");
		computerDao.create(newOne);
		assertTrue(1 + a == computerDao.count());
	}

	@Test
	public void testCreateNoCompany() {
		int a = computerDao.count();
		Company pCompany = null;
		Computer newOne = new Computer("newOne", LocalDate.parse("2015-02-02"), LocalDate.parse("2015-02-02"),
				pCompany);
		computerDao.create(newOne);
		assertTrue(1 + a == computerDao.count());
	}

	@Test
	public void testCreateVoid() {
		int a = computerDao.count();
		Company pCompany = null;
		Computer newOne = null;
		try {
			computerDao.create(newOne);
		} catch (Exception e) {
		}
		assertTrue(a == computerDao.count());
	}

	@AfterClass
	public static void cleanbdd() {
		try {
			Statement st = ConnectionFactory.getConnectionManager().getConn().createStatement();
			st.executeUpdate("DELETE from computer where name='newOne' ");
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}


//	@Override
//	protected String getConnectionUrl() {
//		return "jdbc:mysql://localhost/computer-database-db?zeroDateTimeBehavior=convertToNull;create=true";
//	}
//
//	@Override
//	protected String getDriverClass() {
//		
//		return "java.sql.DriverManager";
//	}
//	  protected String getPassword(){  
//	      return "qwerty1234";  
//	   }  
//	   protected String getUsername(){  
//	      return "admincdb";  
//	   }
//
//	@Override
//	protected IDataSet getDataSet() throws Exception {
//		
//		
//		return getConnection().createDataSet();
//	}  



}
