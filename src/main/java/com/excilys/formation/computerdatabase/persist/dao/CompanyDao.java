package com.excilys.formation.computerdatabase.persist.dao;

import com.excilys.formation.computerdatabase.model.Company;

public interface CompanyDao extends GenericDao <Company> {
	final String KEY = "companyDao";
	Company findByName(String id);
}
