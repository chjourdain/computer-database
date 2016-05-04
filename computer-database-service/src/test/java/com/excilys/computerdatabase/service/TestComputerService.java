package com.excilys.computerdatabase.service;

import static org.junit.Assert.assertTrue;

import java.time.LocalDate;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.excilys.computerdatabase.model.Company;
import com.excilys.computerdatabase.model.Computer;
import com.excilys.computerdatabase.persist.dao.ComputerDao;
import com.excilys.computerdatabase.service.impl.ComputerServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/resources/serviceApplicationContext.xml" })
public class TestComputerService {

    @Autowired
    ComputerDao cD;

    @Autowired
    ComputerServiceImpl service;

    @Test
    public void createFromService() {
	long a = cD.count();
	Computer newOne = new Computer("newOne");
	service.create(newOne);
	assertTrue(1 + a == cD.count());

    }

    @Test
    public void testCreateNoCompany() {
	long a = cD.count();
	Company pCompany = null;
	Computer newOne = new Computer("newOne", LocalDate.parse("2015-02-02"), LocalDate.parse("2015-02-02"),
		pCompany);
	service.create(newOne);
	assertTrue(1 + a == cD.count());
    }

    @Test
    public void testCreateVoid() {
	long a = cD.count();
	Computer newOne = null;
	try {
	    service.create(newOne);
	} catch (Exception e) {
	}
	assertTrue(a == cD.count());
    }
    /*
     * @AfterClass public static void cleanbdd() {
     * 
     * try { Statement st =
     * ConnectionFactory.getConnectionManager().getConn().createStatement();
     * st.executeUpdate("DELETE from computer where name='newOne' "); } catch
     * (SQLException e) { // TODO Auto-generated catch block
     * e.printStackTrace(); }
     * 
     * }
     */
}