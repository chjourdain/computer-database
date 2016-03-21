package com.excilys.formation.test.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.excilys.formation.computerdatabase.model.Company;
import com.excilys.formation.computerdatabase.model.Computer;
import com.excilys.formation.computerdatabase.persist.connection.ConnectionFactory;
import com.excilys.formation.computerdatabase.persist.dao.CompanyDao;
import com.excilys.formation.computerdatabase.persist.dao.impl.CompanyDaoImpl;
import com.excilys.formation.computerdatabase.persist.dao.impl.ComputerDaoImpl;

import junit.framework.TestCase;

public class TestDao extends TestCase {

    public void testListComputeur() {
	ComputerDaoImpl cD = ComputerDaoImpl.INSTANCE;
	List<Computer> cl = cD.findAll(0, 10);
	assertTrue(cl.size() > 0);
	assertTrue(cl.get(0).getName() != null);

    }

    public void testFindComputer() {
	ComputerDaoImpl cD = ComputerDaoImpl.INSTANCE;
	Computer c = cD.find(13);
	assertTrue(c.getName() != null);

    }

    public void testUpdate() {
	ComputerDaoImpl cD = ComputerDaoImpl.INSTANCE;
	int aModifier = 0;

	try {
	    Statement stat = ConnectionFactory.getConnectionManager().getConn().createStatement();
	    ResultSet rs = stat.executeQuery("select MAX(id) from computer");
	    rs.first();
	    aModifier = rs.getInt("max(id)");
	} catch (SQLException e) {
	    e.printStackTrace();
	}
	Computer c2 = cD.find(aModifier);
	c2.setName("echange");
	Computer c = cD.update(c2);
	assertNotNull(c);
	assertEquals(c.getName(), "echange");
	assertEquals(c.getId(), c2.getId());
	assertEquals(c.getCompany(), c2.getCompany());
    }

    public void testUpdateValid() {
	ComputerDaoImpl cD = ComputerDaoImpl.INSTANCE;
	int aModifier = 0;

	try {
	    Statement stat = ConnectionFactory.getConnectionManager().getConn().createStatement();
	    ResultSet rs = stat.executeQuery("select MAX(id) from computer");
	    rs.first();
	    aModifier = rs.getInt("max(id)");
	} catch (SQLException e) {
	}
	Computer c2 = cD.find(aModifier);
	c2.setDiscontinued(LocalDate.parse("2015-02-03"));
	c2.setIntroduced(LocalDate.parse("2015-01-03"));
	Computer c3 = cD.update(c2);
	assertTrue(c3 != null);
	assertEquals(c3, c2);
    }

    public void testUpdateNull() {
	ComputerDaoImpl cD = ComputerDaoImpl.INSTANCE;
	Computer c2 = null, c3 = null;
	try {
	    c3 = cD.update(c2);
	    fail();
	} catch (Exception e) {
	    assertNull(c3);
	}
    }

 /*   public void testDeleteComputer() {
	ComputerDaoImpl cD = ComputerDaoImpl.INSTANCE;
	int size1 = cD.count();
	int aSupprimer = 0;
	try {
	    Statement stat = ConnectionFactory.getConnectionManager().getConn().createStatement();
	    ResultSet rs = stat.executeQuery("select MAX(id) from computer");
	    rs.first();
	    aSupprimer = rs.getInt("max(id)");
	} catch (SQLException e) {
	    e.printStackTrace();
	}
	Computer c2 = cD.find(aSupprimer);
	cD.delete(c2);
	int size2 = cD.count();
	assertTrue(size2 == (size1 - 1));
    }*/

    public void testDeleteNull() {
	ComputerDaoImpl cD = ComputerDaoImpl.INSTANCE;
	int size1 = cD.count();
	Computer c2 = null;
	try {
	    cD.delete(c2);
	    fail();
	} catch (IllegalArgumentException e) {
	}
    }
}
