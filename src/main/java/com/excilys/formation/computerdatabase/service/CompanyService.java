package com.excilys.formation.computerdatabase.service;

import java.util.List;
import com.excilys.formation.computerdatabase.model.Company;

public interface CompanyService extends GenericService<Company>{

	public Company findByName(String companyName);
}
