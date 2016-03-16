package com.excilys.formation.computerdatabase.service;

import java.util.List;

import com.excilys.formation.computerdatabase.model.Computer;

public interface ComputerService extends GenericService<Computer> {

	public int count();

	public default Computer find(int id) {
		throw new UnsupportedOperationException();
	}

	public List<Computer> findAll(Long index, int nbrElement);

	public default Computer create(Computer c) {
		throw new UnsupportedOperationException();
	}

	public default Computer update(Computer c) {
		throw new UnsupportedOperationException();
	}

	public default void delete(Computer c) {
		throw new UnsupportedOperationException();
	}

}
