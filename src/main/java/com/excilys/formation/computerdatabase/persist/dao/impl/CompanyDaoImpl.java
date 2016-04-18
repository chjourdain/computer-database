package com.excilys.formation.computerdatabase.persist.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.excilys.formation.computerdatabase.model.Company;
import com.excilys.formation.computerdatabase.persist.dao.CompanyDao;

@Repository
public class CompanyDaoImpl implements CompanyDao {

    @Autowired
    private DataSource dataSource;
    private Logger daoLogger = LoggerFactory.getLogger(CompanyDaoImpl.class.getName());
    private JdbcTemplate jdbcTemplate;

    private CompanyDaoImpl() {
	daoLogger.info("Initialisation du DAO Company");
    }

    public List<Company> list() {
	String query = "Select id, name from company";
	List<Company> companies;
	jdbcTemplate = new JdbcTemplate(dataSource);
	companies = new ArrayList<Company>();
	List<Map<String, Object>> rows = jdbcTemplate.queryForList(query);
	for (Map row : rows) {
	    Company company = new Company();
	    company.setId(Long.parseLong(String.valueOf(row.get("id"))));
	    company.setName((String) row.get("name"));
	    companies.add(company);
	}
	return companies;
    }

    @Override
    public Company find(long id) {
	String query = "Select id, name from company where id=" + id;
	jdbcTemplate = new JdbcTemplate(dataSource);
	return (Company) jdbcTemplate.queryForObject(query, new BeanPropertyRowMapper<Company>(Company.class));
	
    }

    public int count() {
	String query = "SELECT count(*) from company";
	jdbcTemplate = new JdbcTemplate(dataSource);
	return (Integer) jdbcTemplate.queryForObject(query,Integer.class);
    }

    @Override
    public List<Company> findAll(long index, int nbrElement) {
	String query = "Select * from company" + " LIMIT " + index + "," + nbrElement;
	jdbcTemplate = new JdbcTemplate(dataSource);
	List<Company> companies = new ArrayList<Company>();
	List<Map<String, Object>> rows = jdbcTemplate.queryForList(query);
	for (Map row : rows) {
	    Company company = new Company();
	    company.setId(Long.parseLong(String.valueOf(row.get("id"))));
	    company.setName((String) row.get("name"));
	    companies.add(company);
	}
	return companies; 
    }

    public Company findByName(String id) {
	String query = "Select id, name from company where name=" + id;
	jdbcTemplate = new JdbcTemplate(dataSource);
	return jdbcTemplate.queryForObject(query, new Object[]{id}, Company.class);
    }

    @Override
    public boolean delete(Company c) {
	jdbcTemplate = new JdbcTemplate(dataSource);
	jdbcTemplate.update("DELETE from company where id=" + c.getId());
	return true;
    }
}