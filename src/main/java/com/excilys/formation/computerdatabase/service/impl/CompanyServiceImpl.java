package com.excilys.formation.computerdatabase.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import com.excilys.formation.computerdatabase.model.Company;
import com.excilys.formation.computerdatabase.model.Computer;
import com.excilys.formation.computerdatabase.model.Pager;
import com.excilys.formation.computerdatabase.persist.connection.ConnectionFactory;
import com.excilys.formation.computerdatabase.persist.dao.CompanyDao;
import com.excilys.formation.computerdatabase.persist.dao.impl.CompanyDaoImpl;
import com.excilys.formation.computerdatabase.service.CompanyService;
import com.excilys.formation.computerdatabase.service.GenericService;


public class CompanyServiceImpl implements CompanyService {
    private static CompanyDao companyDao = CompanyDaoImpl.getCompanyDaoImpl();
    private static CompanyServiceImpl instance = new CompanyServiceImpl();
    private static Map<Integer, String> map;
    private Logger logger = org.apache.log4j.Logger.getLogger(this.getClass());

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

    public Map<Integer, String> getMap() {
	if (map == null) {
	    map = new HashMap();
	    List tempo = companyDao.findAll(0, 500);
	    for (Object c : tempo) {
		map.put((Integer) (int) ((Company) c).getId(), ((Company) c).getName());
	    }
	}
	return map;
    }

    public static GenericService getCompanyService() {
	return instance;
    }

    @Override
    public List<Computer> findAll(Pager pager) {
	throw new UnsupportedOperationException();
    }

    @Override
    public void delete(Company c) {
	Connection con = ConnectionFactory.getConnectionManager().getConn();
	try {
	    con.setAutoCommit(false);
	    Statement stm = con.createStatement();
	    stm.executeUpdate("DELETE from computer where company_id="+c.getId());
	    
	    stm.executeUpdate("DELETE from company where id="+c.getId());
	    con.commit();
	} catch (SQLException e) {
	   try {
	    con.rollback();
	    con.close();
	} catch (SQLException e1) {

	   logger.error(e1);
	}
	   logger.error(e);
	}
    }
}
