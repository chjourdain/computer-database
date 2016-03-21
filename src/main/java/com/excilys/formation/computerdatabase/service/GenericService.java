package com.excilys.formation.computerdatabase.service;

import java.util.List;

public interface GenericService<T> {

    int count();

    default T find(int id) {
	throw new UnsupportedOperationException();
    }

    List<T> findAll(Long index, int nbrElement);

    default T create(T c) {
	throw new UnsupportedOperationException();
    }

    default T update(T c) {
	throw new UnsupportedOperationException();
    }

    default void delete(T c) {
	throw new UnsupportedOperationException();
    }

}
