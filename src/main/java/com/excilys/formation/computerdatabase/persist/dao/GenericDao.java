package com.excilys.formation.computerdatabase.persist.dao;

import java.util.List;

/**
 * CRUD interface.
 * 
 * @author charles
 */
public interface GenericDao<T> {

    default T create(T obj) {
	throw new UnsupportedOperationException();
    }

    default boolean delete(T obj) {
	throw new UnsupportedOperationException();
    }

    default T update(T obj) {
	throw new UnsupportedOperationException();
    }

    default T find(long id) {
	throw new UnsupportedOperationException();
    }

    List<T> findAll(long index, int nbrElement);

    default int count() {
	throw new UnsupportedOperationException();
    }
}
