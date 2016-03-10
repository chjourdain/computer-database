package com.excilys.formation.computerdatabase.service;

import java.util.List;

import com.excilys.formation.computerdatabase.connection.ConnectionFactory;
import com.excilys.formation.computerdatabase.dao.CompanyDao;
import com.excilys.formation.computerdatabase.modele.Company;

public class CompanyService {
 static CompanyDao cD = new CompanyDao((ConnectionFactory.getConnectionManager().getConn()));

public static List<Company> list(Integer pageA) {
	
	return cD.list(pageA);
}
}
