package com.excilys.computerdatabase.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.excilys.computerdatabase.model.Computer;
import com.excilys.computerdatabase.persist.dao.ComputerDao;
import com.excilys.computerdatabase.service.ComputerService;

@Service
public class ComputerServiceImpl implements ComputerService {

    @Autowired
    ComputerDao computerDao;

    private ComputerServiceImpl() {
    }

    public long count() {
	return computerDao.count();
    }

    public Computer create(Computer c) {
	if (c == null) {
	    return null;
	}
	return computerDao.save(c);
    }

    public Computer update(Computer c) {
	if (c == null) {
	    return null;
	}
	return computerDao.save(c);
    }

    public void delete(Computer c) {
	if (c != null) {
	    computerDao.delete(c);
	}
    }

    @Override
    public Computer find(long id) {
	if (id != 0) {
	    return computerDao.findOne(id);
	}
	return null;
    }

    @Override
    public Page<Computer> findAll(Pageable pager, String search) {
	if (search !=null && !search.isEmpty()) {
	   return computerDao.findByNameCompanySearch("%"+search+"%",pager);
	}
	return computerDao.findAll(pager);
    }
}
