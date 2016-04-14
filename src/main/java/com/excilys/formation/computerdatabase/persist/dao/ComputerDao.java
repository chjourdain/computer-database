package com.excilys.formation.computerdatabase.persist.dao;

import java.util.List;
import com.excilys.formation.computerdatabase.model.Computer;
import com.excilys.formation.computerdatabase.model.Pager;

public interface ComputerDao extends GenericDao<Computer> {
    String KEY = "computerDao";

    @Override
    Computer create(Computer obj);

    @Override
    boolean delete(Computer obj);

    @Override
    Computer update(Computer obj);

    Computer find(long id);

    List<Computer> findAll(long index, int number);
    
    public List<Computer> findWithSearch(Pager pager);

    int count();

    boolean deleteAll(long id);
}
