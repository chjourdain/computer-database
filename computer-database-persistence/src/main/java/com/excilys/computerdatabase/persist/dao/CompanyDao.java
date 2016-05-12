package com.excilys.computerdatabase.persist.dao;

import org.springframework.data.repository.PagingAndSortingRepository;
import com.excilys.computerdatabase.model.Company;

public interface CompanyDao extends PagingAndSortingRepository<Company, Long> {

    Company findByName(String id);
}
