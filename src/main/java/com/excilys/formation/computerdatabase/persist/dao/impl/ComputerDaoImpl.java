package com.excilys.formation.computerdatabase.persist.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.excilys.formation.computerdatabase.model.Computer;
import com.excilys.formation.computerdatabase.model.Pager;
import com.excilys.formation.computerdatabase.persist.dao.ComputerDao;
import com.excilys.formation.computerdatabase.persist.dao.mapper.ComputerMapper;

@Repository
public class ComputerDaoImpl implements ComputerDao {
    @Autowired
    DataSource dataSource;
    JdbcTemplate jdbcTemplate;

    private ComputerDaoImpl() {}

    private void prepareStatement(Computer computer, PreparedStatement pPreparedStatement) throws SQLException {
	pPreparedStatement.setString(1, computer.getName());
	if (computer.getIntroduced() != null) {
	    pPreparedStatement.setString(2, computer.getIntroduced().toString());
	} else {
	    pPreparedStatement.setString(2, null);
	}
	if (computer.getDiscontinued() != null) {
	    pPreparedStatement.setString(3, computer.getDiscontinued().toString());
	} else {
	    pPreparedStatement.setString(3, null);
	}
	if (computer.getCompany() != null && computer.getCompany().getId() != 0) {
	    pPreparedStatement.setLong(4, computer.getCompany().getId());
	} else {
	    pPreparedStatement.setNull(4, Types.BIGINT);
	}
    }

    @Override
    public List<Computer> findAll(long pStart, int pSize) {
	jdbcTemplate = new JdbcTemplate(dataSource);
	String query = "SELECT * FROM computer LEFT JOIN company on computer.company_id=company_id" + " LIMIT " + pSize
		+ " OFFSET " + pStart;
	List<Computer> computers = jdbcTemplate.query(query, new ComputerMapper());
	return computers;
    }

    @Override
    public Computer create(Computer obj) {
	String query = "INSERT INTO `computer` (name, introduced, discontinued, company_id) VALUES (?, ?, ?, ?)";
	jdbcTemplate = new JdbcTemplate(dataSource);
	KeyHolder keyHolder = new GeneratedKeyHolder();
	jdbcTemplate.update(new PreparedStatementCreator() {
	    public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
		PreparedStatement pstmt = con.prepareStatement(query, new String[] { "id" });
		prepareStatement(obj, pstmt);
		return pstmt;
	    }
	}, keyHolder);
	obj.setId(keyHolder.getKey().longValue());
	return obj;
    }

    @Override
    public boolean delete(Computer obj) {
	if (obj == null || obj.getId() <= 0) {
	    throw new IllegalArgumentException("Null or Not Persisted Object");
	}
	jdbcTemplate = new JdbcTemplate(dataSource);
	jdbcTemplate.update("DELETE FROM computer WHERE id =" + obj.getId());
	return true;
    }

    @Override
    public Computer update(Computer obj) {
	if (obj == null || obj.getId() <= 0) {
	    throw new IllegalArgumentException("Pas d'objet ou objet non enregistré");
	}
	jdbcTemplate = new JdbcTemplate(dataSource);
	jdbcTemplate.update(new PreparedStatementCreator() {
	    public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
		PreparedStatement pstmt = con.prepareStatement(
			"UPDATE `computer` SET name=?, introduced=?, discontinued=?, company_id=? WHERE id = ?;",
			new String[] { "id" });
		prepareStatement(obj, pstmt);
		pstmt.setLong(5, obj.getId());
		return pstmt;
	    }
	});
	return obj;
    }

    public Computer find(long id) {
	jdbcTemplate = new JdbcTemplate(dataSource);
	String query = "SELECT * FROM computer LEFT JOIN company ON company.id=computer.company_id WHERE computer.id="+ id;
	return jdbcTemplate.queryForObject(query, new ComputerMapper());
    }

    public int count() {
	String query = "SELECT count(*) from computer";
	jdbcTemplate = new JdbcTemplate(dataSource);
	return (Integer) jdbcTemplate.queryForObject(query, Integer.class);
    }

    public List<Computer> findWithSearch(Pager pager) {
	String query = queryOrdered(pager);
	jdbcTemplate = new JdbcTemplate(dataSource);
	if (pager.search != null) {
	    String sql = "SELECT count(*) FROM computer where name LIKE ? OR company_id IN (SELECT id FROM company where name LIKE ?)";
	    pager.setTotalCount(jdbcTemplate.queryForObject(sql, Long.class,
		    new Object[] { "%" + pager.getSearch() + "%", "%" + pager.getSearch() + "%" }));
	    return jdbcTemplate.query(query,
		    new Object[] { "%" + pager.getSearch() + "%", "%" + pager.getSearch() + "%",
			    Integer.valueOf(pager.nbByPage),
			    Integer.valueOf(pager.nbByPage * (pager.currentPage - 1)) },
		    new ComputerMapper());
	} else {
	    String sql = "SELECT count(*) FROM computer";
	    pager.setTotalCount(jdbcTemplate.queryForObject(sql, Long.class));
	    return jdbcTemplate.query(query, new Object[] { Integer.valueOf(pager.nbByPage),
		    Integer.valueOf(pager.nbByPage * (pager.currentPage - 1)) }, new ComputerMapper());
	}
    }

    @Transactional()
    public boolean deleteAll(long id) {
	jdbcTemplate = new JdbcTemplate(dataSource);
	jdbcTemplate.update("DELETE from computer where company_id=" + id);
	return true;
    }

    private static String queryOrdered(Pager pager) {
	String whereSentence = "";
	if (pager.search != null && !pager.search.isEmpty()) {
	    whereSentence = " where computer.name LIKE ? OR company_id IN (SELECT id FROM company where company.name LIKE ?)";
	}
	if ("computer".equals(pager.getSort())) {
	    return "SELECT * FROM computer LEFT JOIN company on computer.company_id=company.id " + whereSentence
		    + " ORDER BY computer.name LIMIT ? OFFSET ?";
	}
	if ("intro".equals(pager.getSort())) {
	    return "SELECT * FROM computer LEFT JOIN company on computer.company_id=company.id " + whereSentence
		    + " ORDER BY computer.introduced LIMIT ? OFFSET ?";
	}
	if ("disco".equals(pager.getSort())) {
	    return "SELECT * FROM computer LEFT JOIN company on computer.company_id=company.id " + whereSentence
		    + " ORDER BY computer.discontinued LIMIT ? OFFSET ?";
	}
	if ("company".equals(pager.getSort())) {
	    return "SELECT * FROM computer LEFT JOIN company on computer.company_id=company.id " + whereSentence
		    + " ORDER BY company.name LIMIT ? OFFSET ?";
	}
	return "SELECT * FROM computer LEFT JOIN company on computer.company_id=company.id " + whereSentence
		+ " LIMIT ? OFFSET ?";
    }
}
