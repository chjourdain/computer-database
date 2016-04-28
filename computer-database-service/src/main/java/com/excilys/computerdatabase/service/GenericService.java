package com.excilys.computerdatabase.service;

public interface GenericService<T> {

 long count();

    default T find(long id) {
	throw new UnsupportedOperationException();
    }

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
