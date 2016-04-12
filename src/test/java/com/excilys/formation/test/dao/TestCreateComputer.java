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
import com.excilys.formation.computerdatabase.persist.dao.CompanyDao;
import com.excilys.formation.computerdatabase.persist.dao.impl.CompanyDaoImpl;
import com.excilys.formation.computerdatabase.persist.dao.impl.ComputerDaoImpl;

public class TestCreateComputer {
   /* static ComputerDaoImpl computerDao;
    static CompanyDao companydao;

    @Before
    public void initialize() {
	computerDao = ComputerDaoImpl.INSTANCE;
	companydao = CompanyDaoImpl.getCompanyDaoImpl();
    }

    @Test
    public void testCreateStandart() {
	ConnectionFactory.getConnectionManager().iniTransaction();
	int a = computerDao.count();
	Computer newOne = new Computer("newOne");
	computerDao.create(newOne);
	assertTrue(1 + a == computerDao.count());
	ConnectionFactory.getConnectionManager().rollback();
    }

    @Test
    public void testCreateNoCompany() {
	ConnectionFactory.getConnectionManager().iniTransaction();
	int a = computerDao.count();
	Company pCompany = null;
	Computer newOne = new Computer("newOne", LocalDate.parse("2015-02-02"), LocalDate.parse("2015-02-02"),
		pCompany);
	computerDao.create(newOne);
	assertTrue(1 + a == computerDao.count());
	ConnectionFactory.getConnectionManager().rollback();
    }

    @Test
    public void testCreateVoid() {
	ConnectionFactory.getConnectionManager().iniTransaction();
	int a = computerDao.count();
	Company pCompany = null;
	Computer newOne = null;
	try {
	    computerDao.create(newOne);
	} catch (Exception e) {
	}
	assertTrue(a == computerDao.count());
	ConnectionFactory.getConnectionManager().rollback();
    }

    @Test
    public void testCreateComplet() {
	 ConnectionFactory.getConnectionManager().iniTransaction();;
	int a = computerDao.count();
	Company company = companydao.find(10);
	LocalDate d = LocalDate.parse("2015-02-02");
	Computer newOne = new Computer("newOne", d, d, company);
	try {
	    newOne = computerDao.create(newOne);
	} catch (Exception e) {
	}
	assertTrue(a + 1 == computerDao.count());
	assertTrue(newOne.getId() != 0);
	 ConnectionFactory.getConnectionManager().rollback();
    }

    @Test
    public void testCreateDateFausse() {
	ConnectionFactory.getConnectionManager().iniTransaction();
	Company company = companydao.find(10);
	LocalDate d = LocalDate.parse("2040-02-02");
	Computer newOne = null;
	try {
	    newOne = computerDao.create(new Computer("newOne", d, d, company));
	} catch (Exception e) {
	}
	assertNull(newOne);
	ConnectionFactory.getConnectionManager().rollback();
    }

    @AfterClass
    public static void cleanbdd() {
	try {
	    Statement st = ConnectionFactory.getConnectionManager().getConn().createStatement();
	    st.executeUpdate("DELETE from computer where name='newOne' ");
	} catch (SQLException e) {
	    e.printStackTrace();
	}

    }*/
}
