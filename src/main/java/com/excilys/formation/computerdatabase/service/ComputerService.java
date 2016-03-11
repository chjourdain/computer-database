package com.excilys.formation.computerdatabase.service;

import java.util.List;

import com.excilys.formation.computerdatabase.connection.ConnectionFactory;
import com.excilys.formation.computerdatabase.dao.ComputerDao;
import com.excilys.formation.computerdatabase.model.Computer;

public class ComputerService {
	static ComputerDao cD = new ComputerDao((ConnectionFactory.getConnectionManager().getConn()));
	
	public static List<Computer> listComputer(int page){
		return cD.list(page);
		
	}

	public static int getNumberOfElement() {
		return cD.getNumberOfElement();
	}

	public static Computer find(int id) {

		return cD.find(id);
	}

	public static void create(Computer c) {
		cD.create(c);
		
	}

	public static void update(Computer c) {
		cD.update(c);
		
	}

	public static void delete(Computer c) {
		cD.delete(c);
		
	}
	

}
