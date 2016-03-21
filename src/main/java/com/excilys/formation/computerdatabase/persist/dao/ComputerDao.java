package com.excilys.formation.computerdatabase.persist.dao;

import java.util.List;
import com.excilys.formation.computerdatabase.model.Computer;

public interface ComputerDao extends GenericDao<Computer> {
    String KEY = "computerDao";

    @Override
    Computer create(Computer obj);

    @Override
    boolean delete(Computer obj);

    @Override
    Computer update(Computer obj);

    Computer find(int id);

    List<Computer> findAll(long index, int number);

    int count();
}
