package com.excilys.formation.test.dao;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.excilys.formation.computerdatabase.model.Computer;
import com.excilys.formation.computerdatabase.persist.connection.ConnectionFactory;
import com.excilys.formation.computerdatabase.persist.dao.impl.ComputerDaoImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/resources/applicationContext.xml" })
public class TestListComputer {
    @Autowired
    ComputerDaoImpl cD;

    @Test
    public void lister2() {
	List<Computer> test = cD.findAll(0, 2);
	assertTrue(test.size() == 2);
	assertTrue(test.get(0).equals(cD.find(2)));
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
}
