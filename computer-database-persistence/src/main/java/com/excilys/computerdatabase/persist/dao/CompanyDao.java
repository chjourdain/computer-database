package com.excilys.computerdatabase.persist.dao;

import org.springframework.data.repository.CrudRepository;
import com.excilys.computerdatabase.model.Company;

public interface CompanyDao extends CrudRepository<Company,Long> {

    Company findByName(String id);
}
