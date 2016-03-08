package com.excylis.formation.test.dao;

import java.util.ArrayList;

import com.excilys.formation.computerdatabase.connection.ConnectionManager;
import com.excilys.formation.computerdatabase.dao.CompanyDao;
import com.excilys.formation.computerdatabase.dao.ComputerDao;
import com.excilys.formation.computerdatabase.modele.Company;
import com.excilys.formation.computerdatabase.modele.Computer;

import junit.framework.TestCase;



public class TestDao extends TestCase {
	
	public void testListCompanie(){
		
		CompanyDao cD= new CompanyDao((ConnectionManager.getConnectionManager().getConn()));
		ArrayList<Company> cl= cD.list();
		assertTrue(cl.size()>0);
		assertTrue(cl.get(0).getName().equals("Apple Inc.") );		
	}
	
	public void testListComputeur(){
		ComputerDao cD= new ComputerDao((ConnectionManager.getConnectionManager().getConn()));
		ArrayList<Computer> cl= cD.list();
		assertTrue(cl.size()>0);
		assertTrue(cl.get(0).getName()!=null );		
		
		
	}
	
	public void testFindComputer(){
		ComputerDao cD= new ComputerDao((ConnectionManager.getConnectionManager().getConn()));
		Computer c= cD.find(13);
		System.out.println(c.toString());
		assertTrue(c.getName()!=null);
		
	}
	public void testAjoutComputer(){
		ComputerDao cD= new ComputerDao((ConnectionManager.getConnectionManager().getConn()));
		ArrayList<Computer> cl= cD.list();
		int size1 = cl.size();
		Computer c=new Computer("testertezeez");
		cD.create(c);
		cl= cD.list();
		int size2 = cl.size();
		assertTrue(size2==(1+size1));
		assertTrue(c.getId()!=0);
		
	}
	public void testDeleteComputer(){
		ComputerDao cD= new ComputerDao((ConnectionManager.getConnectionManager().getConn()));
		ArrayList<Computer> cl= cD.list();
		int size1 = cl.size();
		Computer c2 = cD.find(575);
		cD.delete(c2);
		cl= cD.list();
		int size2 = cl.size();
		assertTrue(size2==(size1-1));
	}
	
}
