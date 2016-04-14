package com.excilys.formation.computerdatabase.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.excilys.formation.computerdatabase.model.Computer;
import com.excilys.formation.computerdatabase.model.Pager;
import com.excilys.formation.computerdatabase.persist.dao.impl.ComputerDaoImpl;
import com.excilys.formation.computerdatabase.persist.dao.mapper.ComputerMapper;
import com.excilys.formation.computerdatabase.service.ComputerService;

@Service
public class ComputerServiceImpl implements ComputerService {

    @Autowired
    ComputerDaoImpl computerDao;

    private ComputerServiceImpl() {
    }

    public int count() {
	return computerDao.count();
    }

    public Computer create(Computer c) {
	if (c == null) {
	    return null;
	}
	return computerDao.create(c);
    }

    public Computer update(Computer c) {
	if (c == null) {
	    return null;
	}
	return computerDao.update(c);
    }

    public void delete(Computer c) {
	if (c != null) {
	    computerDao.delete(c);
	}
    }

    @Override
    public List<Computer> findAll(Long index, int nbrElement) {
	if (index < 0 || nbrElement <= 0) {
	    return null;
	}
	return computerDao.findAll(index, nbrElement);
    }

    @Override
    public Computer find(long id) {
	if (id != 0) {
	    return computerDao.find(id);
	}
	return null;
    }

    @Override
    public List<Computer> findAll(Pager pager) {
	return computerDao.findWithSearch(pager);
    }

    @Override
    public void fillPage(Pager pager) {
	pager.setList(ComputerMapper.toDTOs(this.findAll(pager)));
	if (pager.getCurrentPage() > pager.getNbPages()) {
	    pager.setCurrentPage(1);
	}

    }
}
