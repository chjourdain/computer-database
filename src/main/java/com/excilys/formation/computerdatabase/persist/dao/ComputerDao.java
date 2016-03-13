package com.excilys.formation.computerdatabase.persist.dao;

import java.util.List;
import com.excilys.formation.computerdatabase.model.Computer;

public interface ComputerDao extends GenericDao<Computer>{
	static final String KEY = "computerDao";
	
	@Deprecated
	public List<Computer> list(int page);
	@Override
	public Computer create(Computer obj) ;
	@Override
	public boolean delete(Computer obj) ;
	@Override
	public Computer update(Computer obj) ;
	public Computer find(int id) ;
	public List<Computer> findAll(long index, int number);
	public int count() ;

	
	
}
