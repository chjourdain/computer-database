package com.excilys.formation.computerdatabase.persist.dao;

import java.util.List;

public interface GenericDao<T> {
	static int ROW_BY_PAGE = 20;

	default T create(T obj) {
		throw new UnsupportedOperationException();
	}

	default boolean delete(T obj) {
		throw new UnsupportedOperationException();
	}

	default T update(T obj) {
		throw new UnsupportedOperationException();
	}

	default T find(int id) {
		throw new UnsupportedOperationException();
	}

	List<T> findAll(long index, int nbrElement);
	default int count() {
		throw new UnsupportedOperationException();
	}
}
