package com.excilys.formation.test.dao;

import static org.junit.Assert.assertTrue;

import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import com.excilys.formation.computerdatabase.connection.ConnectionFactory;
import com.excilys.formation.computerdatabase.dao.ComputerDao;
import com.excilys.formation.computerdatabase.modele.Company;
import com.excilys.formation.computerdatabase.modele.Computer;

public class TestCreateComputer {
	static ComputerDao cD;

	@Before
	public void initialize() {
		cD = new ComputerDao((ConnectionFactory.getConnectionManager().getConn()));
	}

	@Test
	public void testCreateStandart() {

		int a = cD.getNumberOfElement();
		Computer newOne = new Computer("newOne");
		cD.create(newOne);
		assertTrue(1 + a == cD.getNumberOfElement());
	}

	@Test
	public void testCreateNoCompany() {

		int a = cD.getNumberOfElement();
		Company pCompany = null;
		Computer newOne = new Computer("newOne", LocalDate.parse("2015-02-02"), LocalDate.parse("2015-02-02"),
				pCompany);
		cD.create(newOne);
		assertTrue(1 + a == cD.getNumberOfElement());
	}

	@Test
	public void testCreateVoid() {

		int a = cD.getNumberOfElement();
		Company pCompany = null;
		Computer newOne = null;
		try {
			cD.create(newOne);
		} catch (Exception e) {
		}
		assertTrue(a == cD.getNumberOfElement());
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
