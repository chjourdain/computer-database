package com.excilys.computerdatabase.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.excilys.computerdatabase.model.Computer;

public interface ComputerService extends GenericService<Computer> {

    long count();

    default Computer find(long id) {
	throw new UnsupportedOperationException();
    }

    default Computer create(Computer c) {
	throw new UnsupportedOperationException();
    }

    default Computer update(Computer c) {
	throw new UnsupportedOperationException();
    }

    default void delete(Computer c) {
	throw new UnsupportedOperationException();
    }
    Page<Computer> findAll(Pageable pager, String search);

}
