package com.excilys.formation.computerdatabase.persist.dao;

import java.util.ArrayList;

import com.excilys.formation.computerdatabase.model.Company;

public interface CompanyDao extends GenericDao<Company>{
	static final String KEY = "companyDao";

	public Company findByName(String id);
}
