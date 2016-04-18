package com.excilys.formation.computerdatabase.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.excilys.formation.computerdatabase.model.Company;
import com.excilys.formation.computerdatabase.model.Computer;
import com.excilys.formation.computerdatabase.model.Pager;
import com.excilys.formation.computerdatabase.persist.dao.impl.CompanyDaoImpl;
import com.excilys.formation.computerdatabase.persist.dao.impl.ComputerDaoImpl;
import com.excilys.formation.computerdatabase.service.CompanyService;

@Service
public class CompanyServiceImpl implements CompanyService {
    @Autowired
    CompanyDaoImpl companyDao;
    @Autowired
    ComputerDaoImpl computerDao;
    private static Map<Long, String> map;

    public Company findByName(String companyName) {
	if (companyName == null || companyName.isEmpty()) {
	    return null;
	}
	return companyDao.findByName(companyName);
    }

    @Override
    public List<Company> findAll(Long index, int nbrElement) {
	if (index < 0 || nbrElement <= 0) {
	    return null;
	}
	return companyDao.findAll(index, nbrElement);
    }

    @Override
    public int count() {
	return companyDao.count();
    }

    @Override
    public Company find(long id) {
	if (id == 0) {
	    return null;
	}
	return companyDao.find(id);
    }

    /**
     * Method which return a hashmap with <company id, company name>
     * 
     * @return
     */
    public Map<Long, String> getMap() {
	if (map == null) {
	    map = new HashMap<>();
	    List<Company> tempo = companyDao.findAll(0, 500);
	    for (Company c : tempo) {
		map.put((Long) ((Company) c).getId(), ((Company) c).getName());
	    }
	}
	return map;
    }

    @Override
    public List<Computer> findAll(Pager pager) {
	throw new UnsupportedOperationException();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void delete(Company c) {
	computerDao.deleteAll(c.getId());
	companyDao.delete(c);
    }
}
