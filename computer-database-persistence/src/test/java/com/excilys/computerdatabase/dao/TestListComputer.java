package com.excilys.computerdatabase.dao;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.excilys.computerdatabase.model.Computer;
import com.excilys.computerdatabase.persist.dao.ComputerDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/resources/persistApplicationContext.xml" })
public class TestListComputer {
   @Autowired
   ComputerDao cD;

    @Test
    public void listerPage10() {
	PageRequest pr = new PageRequest(1, 10);
	Page<Computer> Page = cD.findAll(pr);
	List<Computer> list = Page.getContent();
	assertTrue(list.size() == 10);
    }

    @Test
    public void listerSearch() {
	PageRequest pr = new PageRequest(1, 20);
	Page<Computer> Page = cD.findByNameCompanySearch("%Appl%", pr);
	List<Computer> list = Page.getContent();
	assertTrue(list.size() > 1);
    }

    @Test
    public void listerSearchDTO() {
	PageRequest pr = new PageRequest(1, 20);
	Page<Computer> Page = cD.findByNameCompanySearch("%Appl%", pr);
    }
    
    /*@Test
    public void listerNegatifdeux() {
	try {
	    List<Computer> test = cD.findAll(-4, 5);
	    fail();
	} catch (Exception e) {
	}

    }*/
}
