package com.excilys.formation.test.service;

import static org.junit.Assert.assertTrue;

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
import com.excilys.formation.computerdatabase.service.impl.ComputerServiceImpl;

public class TestComputerService {

	static ComputerDaoImpl cD;

	@Before
	public void initialize() {
		cD = ComputerDaoImpl.INSTANCE;
	}

	@Test
	public void createFromService() {
		int a = cD.count();
		Computer newOne = new Computer("newOne");
		ComputerServiceImpl.getComputerService().create(newOne);
		assertTrue(1 + a == cD.count());

	}

	@Test
	public void testCreateNoCompany() {

		int a = cD.count();
		Company pCompany = null;
		Computer newOne = new Computer("newOne", LocalDate.parse("2015-02-02"), LocalDate.parse("2015-02-02"),
				pCompany);
		ComputerServiceImpl.getComputerService().create(newOne);
		assertTrue(1 + a == cD.count());
	}

	@Test
	public void testCreateVoid() {

		int a = cD.count();

		Computer newOne = null;
		try {
			ComputerServiceImpl.getComputerService().create(newOne);
		} catch (Exception e) {
		}
		assertTrue(a == cD.count());
	}

	@AfterClass
	public static void cleanbdd() {

		try {
			Statement st = ConnectionFactory.getConnectionManager().getConn().createStatement();
			st.executeUpdate("DELETE from computer where name='newOne' ");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}