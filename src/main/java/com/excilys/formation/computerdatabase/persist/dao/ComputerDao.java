package com.excilys.formation.computerdatabase.persist.dao;

import java.util.List;
import com.excilys.formation.computerdatabase.model.Computer;

public interface ComputerDao extends GenericDao <Computer> {
	final String KEY = "computerDao";	
	@Override
	public Computer create(Computer obj);
	@Override
	public boolean delete(Computer obj);
	@Override
	Computer update(Computer obj);
	Computer find(int id);
	List<Computer> findAll(long index, int number);
	int count();
}
