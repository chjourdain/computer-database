package com.excilys.formation.test.dao;

import static org.junit.Assert.*;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import com.excilys.formation.computerdatabase.connection.ConnectionFactory;
import com.excilys.formation.computerdatabase.dao.ComputerDao;
import com.excilys.formation.computerdatabase.model.Computer;

public class TestListComputer {
	ComputerDao cD;

	@Before
	public void initialize() {
		cD = new ComputerDao((ConnectionFactory.getConnectionManager().getConn()));
	}

	@Test
	public void lister2() {
		List<Computer> test = cD.findAll(0, 2);
		assertTrue(test.size() == 2);
		assertTrue(test.get(0).equals(cD.find(1)));
	}

	@Test
	public void listerNegatif() {
		List<Computer> test = cD.findAll(0, -2);
		assertTrue(test.size() == 0);
	}

	@Test
	public void listerNegatifdeux() {
		try {
			List<Computer> test = cD.findAll(-4, 5);
			fail();
		} catch (Exception e) {
		}
	}
	@Test
	public void listerOver() {
		List<Computer> test = cD.findAll(0, 10000);
		assertTrue(test.size() == cD.getNumberOfElement());
	
	}


}
