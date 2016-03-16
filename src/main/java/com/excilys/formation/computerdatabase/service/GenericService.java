package com.excilys.formation.computerdatabase.service;

import java.util.List;

public interface GenericService<T> {

	public int count();

	public default T find(int id) {
		throw new UnsupportedOperationException();
	}

	public List<T> findAll(Long index, int nbrElement);

	public default T create(T c) {
		throw new UnsupportedOperationException();
	}

	public default T update(T c) {
		throw new UnsupportedOperationException();
	}

	public default void delete(T c) {
		throw new UnsupportedOperationException();
	}

}
