package com.excilys.formation.test.dao;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.excilys.formation.computerdatabase.model.Company;
import com.excilys.formation.computerdatabase.model.Computer;
import com.excilys.formation.computerdatabase.persist.dao.CompanyDao;
import com.excilys.formation.computerdatabase.persist.dao.ComputerDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/resources/applicationContext.xml" })
public class TestCreateComputer {

    @Autowired
    ComputerDao computerDao;
    @Autowired
    CompanyDao companydao;
    @Autowired
    DataSource dataSource;

    @Test
    public void testCreateStandart() {
	long a = computerDao.count();
	Computer newOne = new Computer("newOne");
	computerDao.save(newOne);
	assertTrue(1 + a == computerDao.count());
    }

    @Test
    public void testCreateNoCompany() {
	long a = computerDao.count();
	Company Company = null;
	Computer newOne = new Computer("newOne", LocalDate.parse("2015-02-02"), LocalDate.parse("2015-02-02"),
		Company);
	computerDao.save(newOne);
	assertTrue(1 + a == computerDao.count());
    }

    @Test
    public void testCreateVoid() {
	long a = computerDao.count();
	Computer newOne = null;
	try {  //  Computer(Computer obj);

	    //  boolean delete(Computer obj);

	    //  Computer update(Computer obj);

//	      Computer find(long id);
	    computerDao.save(newOne);
	} catch (Exception e) {
	}
	assertTrue(a == computerDao.count());
    }

    @Test
    public void testCreateComplet() {
	long a = computerDao.count();
	Company company = companydao.findOne(10L);
	LocalDate d = LocalDate.parse("2015-02-02");
	Computer newOne = new Computer("newOne", d, d, company);
	try {
	    newOne = computerDao.save(newOne);
	} catch (Exception e) {
	}
	assertTrue(a + 1 == computerDao.count());
	assertTrue(newOne.getId() != 0);
    }

    @Test
    public void testCreateDateFausse() {
	Company company = companydao.findOne(10L);
	LocalDate d = LocalDate.parse("2040-02-02");
	Computer newOne = null;
	try {
	    newOne = computerDao.save(new Computer("newOne", d, d, company));
	} catch (Exception e) {
	}
	assertNull(newOne);
    }
    
    @Test
    public void find() {
	Computer c = computerDao.findOne(575L);
	System.out.println("n\n\n\n\n"+c);
	assertTrue(c != null);
    }

    /*
     * @AfterClass public static void cleanbdd() { try { Statement st =
     * dataSource.getConnection().saveStatement(); st.executeUpdate(
     * "DELETE from computer where name='newOne' "); } catch (SQLException e) {
     * e.prlongStackTrace(); }
     * 
     * }
     */
}
