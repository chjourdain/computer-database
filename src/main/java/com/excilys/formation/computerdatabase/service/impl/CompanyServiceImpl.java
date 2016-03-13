package com.excilys.formation.computerdatabase.service.impl;

import java.util.List;

import com.excilys.formation.computerdatabase.model.Company;
import com.excilys.formation.computerdatabase.persist.dao.CompanyDao;
import com.excilys.formation.computerdatabase.persist.dao.impl.CompanyDaoImpl;
import com.excilys.formation.computerdatabase.service.CompanyService;
import com.excilys.formation.computerdatabase.service.GenericService;

public class CompanyServiceImpl implements CompanyService{
	private CompanyDao companyDao = CompanyDaoImpl.getCompanyDaoImpl();
	private static CompanyServiceImpl instance = new CompanyServiceImpl();

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

	public static GenericService getCompanyService() {
		return instance;
	}
}
