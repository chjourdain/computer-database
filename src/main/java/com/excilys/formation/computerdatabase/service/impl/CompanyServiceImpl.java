package com.excilys.formation.computerdatabase.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.excilys.formation.computerdatabase.model.Company;
import com.excilys.formation.computerdatabase.model.Computer;
import com.excilys.formation.computerdatabase.model.Pager;
import com.excilys.formation.computerdatabase.persist.connection.ConnectionFactory;
import com.excilys.formation.computerdatabase.persist.dao.CompanyDao;
import com.excilys.formation.computerdatabase.persist.dao.impl.CompanyDaoImpl;
import com.excilys.formation.computerdatabase.persist.dao.impl.ComputerDaoImpl;
import com.excilys.formation.computerdatabase.service.CompanyService;
import com.excilys.formation.computerdatabase.service.GenericService;


public class CompanyServiceImpl implements CompanyService {
    private static CompanyDao companyDao = CompanyDaoImpl.getCompanyDaoImpl();
    private static CompanyServiceImpl instance = new CompanyServiceImpl();
    private static Map<Long, String> map;
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public Company findByName(String companyName) {
	if (companyName == null || companyName.isEmpty()) {
	    return null;
	}
	ConnectionFactory.getConnectionManager().initConnection();
	return companyDao.findByName(companyName);
    }

    @Override
    public List<Company> findAll(Long index, int nbrElement) {
	if (index < 0 || nbrElement <= 0) {
	    return null;
	}
	ConnectionFactory.getConnectionManager().initConnection();
	return companyDao.findAll(index, nbrElement);
    }

    @Override
    public int count() {
	ConnectionFactory.getConnectionManager().initConnection();
	return companyDao.count();
    }

    @Override
    public Company find(long id) {
	if (id == 0) {
	    return null;
	}
	ConnectionFactory.getConnectionManager().initConnection();
	return companyDao.find(id);
    }

    
    /**
     * Method which return a hashmap with <company id, company name>
     * @return
     */
    public Map<Long, String> getMap() {
	if (map == null) {
	    map = new HashMap<>();
	    ConnectionFactory.getConnectionManager().initConnection();
	    List<Company> tempo = companyDao.findAll(0, 500);
	    for (Object c : tempo) {
		map.put((Long)  ((Company) c).getId(), ((Company) c).getName());
	    }
	}
	return map;
    }

    public static GenericService<Company> getCompanyService() {
	return instance;
    }

    @Override
    public List<Computer> findAll(Pager pager) {
	throw new UnsupportedOperationException();
    }

    
    @Override
    public void delete(Company c) {
	ConnectionFactory.getConnectionManager().iniTransaction();
	ComputerDaoImpl.getComputerDao().deleteAll(c.getId());
	CompanyDaoImpl.getCompanyDaoImpl().delete(c);
	ConnectionFactory.getConnectionManager().commit();
	
    }
}
