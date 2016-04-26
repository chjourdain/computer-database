package com.excilys.formation.test.dao;

import javax.sql.DataSource;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.excilys.formation.computerdatabase.persist.dao.CompanyDao;
import com.excilys.formation.computerdatabase.persist.dao.ComputerDao;

import junit.framework.TestCase;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/resources/applicationContext.xml" })
public class TestDao extends TestCase {

    @Autowired
    ComputerDao cD;
    @Autowired
    CompanyDao companydao;
    @Autowired
    DataSource dataSource;
/*
    @Test
    public void testListComputeur() {
	List<Computer> cl = cD.findAll(0, 10);
	assertTrue(cl.size() > 0);
	assertTrue(cl.get(0).getName() != null);
    }
    @Test
    public void testFindComputer() {
	Computer c = cD.find(2);
	assertTrue(c.getName() != null);
    }
    @Test
    public void testUpdate() {
	int aModifier = 0;

	try {
	    Statement stat = dataSource.getConnection().createStatement();
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
    @Test
    public void testUpdateValid() {
	int aModifier = 0;

	try {
	    Statement stat = dataSource.getConnection().createStatement();
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
    @Test
    public void testUpdateNull() {
	Computer c2 = null, c3 = null;
	try {
	    c3 = cD.update(c2);
	    fail();
	} catch (Exception e) {
	    assertNull(c3);
	}
    }
    @Test
    public void testDeleteNull() {
	int size1 = cD.count();
	Computer c2 = null;
	try {
	    cD.delete(c2);
	    fail();
	} catch (IllegalArgumentException e) {
	*/
}
