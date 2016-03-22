package com.excilys.formation.computerdatabase.service;

import java.util.List;

import com.excilys.formation.computerdatabase.model.Computer;
import com.excilys.formation.computerdatabase.model.Pager;

public interface GenericService<T> {

    int count();

    default T find(long id) {
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

    List<Computer> findAll(Pager pager);

}
