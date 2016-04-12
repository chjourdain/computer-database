package com.excilys.formation.test.dao;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.excilys.formation.computerdatabase.model.Computer;
import com.excilys.formation.computerdatabase.persist.connection.ConnectionFactory;
import com.excilys.formation.computerdatabase.persist.dao.impl.ComputerDaoImpl;

public class TestListComputer {
   /* ComputerDaoImpl cD;

    @Before
    public void initialize() {
	cD = ComputerDaoImpl.INSTANCE;
    }

    @Test
    public void lister2() {
	ConnectionFactory.getConnectionManager().iniTransaction();
	List<Computer> test = cD.findAll(0, 2);
	assertTrue(test.size() == 2);
	assertTrue(test.get(0).equals(cD.find(2)));
	ConnectionFactory.getConnectionManager().rollback();
    }

    @Test
    public void listerNegatif() {
	ConnectionFactory.getConnectionManager().iniTransaction();
	List<Computer> test = cD.findAll(0, -2);
	assertTrue(test.size() == 0);
	ConnectionFactory.getConnectionManager().rollback();
    }

    @Test
    public void listerNegatifdeux() {
	ConnectionFactory.getConnectionManager().iniTransaction();
	try {
	    List<Computer> test = cD.findAll(-4, 5);
	    fail();
	} catch (Exception e) {
	}
	ConnectionFactory.getConnectionManager().rollback();
    }*/
}
