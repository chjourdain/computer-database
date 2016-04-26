package com.excilys.formation.computerdatabase.persist.dao;

import org.springframework.data.repository.CrudRepository;
import com.excilys.formation.computerdatabase.model.Company;

public interface CompanyDao extends CrudRepository<Company,Long> {

    Company findByName(String id);
}
