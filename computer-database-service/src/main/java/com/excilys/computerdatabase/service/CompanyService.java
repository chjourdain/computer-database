package com.excilys.computerdatabase.service;

import com.excilys.computerdatabase.model.Company;

public interface CompanyService extends GenericService<Company> {

    Company findByName(String companyName);
}
