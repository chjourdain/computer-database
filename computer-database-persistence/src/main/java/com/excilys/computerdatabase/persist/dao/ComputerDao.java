package com.excilys.computerdatabase.persist.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.excilys.computerdatabase.model.Company;
import com.excilys.computerdatabase.model.Computer;

@Repository
@Transactional
public interface ComputerDao extends PagingAndSortingRepository<Computer, Long> {

    public void deleteByCompany(Company company);

    // @Query("SELECT c FROM computer c LEFT JOIN c.company cpy WHERE " +
    // "c.name LIKE :search OR cpy.name LIKE :search ")
    @Query(name = "computer.search", value = "select c from computer c LEFT JOIN c.company cp ON c.company.id = cp.id "
            + "WHERE c.name LIKE :search OR cp.name LIKE :search")
    public Page<Computer> findByNameCompanySearch(@Param("search") String search, Pageable page);
}
