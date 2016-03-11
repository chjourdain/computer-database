package com.excilys.formation.computerdatabase.service;

import java.util.List;

import com.excilys.formation.computerdatabase.connection.ConnectionFactory;
import com.excilys.formation.computerdatabase.dao.CompanyDao;
import com.excilys.formation.computerdatabase.model.Company;

public class CompanyService {
 static CompanyDao companyDao = new CompanyDao((ConnectionFactory.getConnectionManager().getConn()));

public static List<Company> list(Integer pageA) {
	
	return companyDao.list(pageA);
}

public static Company findByName(String companyName) {
	return companyDao.findByName(companyName);
}
}
