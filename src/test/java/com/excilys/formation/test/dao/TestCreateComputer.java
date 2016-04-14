package com.excilys.formation.test.dao;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import javax.sql.DataSource;
import org.junit.AfterClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.excilys.formation.computerdatabase.model.Company;
import com.excilys.formation.computerdatabase.model.Computer;
import com.excilys.formation.computerdatabase.persist.dao.CompanyDao;
import com.excilys.formation.computerdatabase.persist.dao.impl.ComputerDaoImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/resources/applicationContext.xml" })
public class TestCreateComputer {

    @Autowired
    ComputerDaoImpl computerDao;
    @Autowired
    CompanyDao companydao;
    @Autowired
    DataSource dataSource;

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

    @Test
    public void testCreateComplet() {
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
    }

    @Test
    public void testCreateDateFausse() {
	Company company = companydao.find(10);
	LocalDate d = LocalDate.parse("2040-02-02");
	Computer newOne = null;
	try {
	    newOne = computerDao.create(new Computer("newOne", d, d, company));
	} catch (Exception e) {
	}
	assertNull(newOne);
    }

  /*  @AfterClass
    public static void cleanbdd() {
	try {
	    Statement st = dataSource.getConnection().createStatement();
	    st.executeUpdate("DELETE from computer where name='newOne' ");
	} catch (SQLException e) {
	    e.printStackTrace();
	}

    }*/
}
