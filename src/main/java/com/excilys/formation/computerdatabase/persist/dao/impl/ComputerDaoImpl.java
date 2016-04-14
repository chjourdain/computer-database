package com.excilys.formation.computerdatabase.persist.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.formation.computerdatabase.model.Computer;
import com.excilys.formation.computerdatabase.model.Pager;
import com.excilys.formation.computerdatabase.persist.connection.ConnectionFactory;
import com.excilys.formation.computerdatabase.persist.connection.ThreadLocals;
import com.excilys.formation.computerdatabase.persist.dao.ComputerDao;
import com.excilys.formation.computerdatabase.persist.dao.exception.DAOException;
import com.excilys.formation.computerdatabase.persist.dao.mapper.ComputerMapper;

@Repository
public class ComputerDaoImpl implements ComputerDao {

    private Logger daoLogger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    DataSource dataSource;

    private ComputerDaoImpl() {
    }

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
	if (computer.getCompany() != null && computer.getCompany().getId() !=0) {
	    pPreparedStatement.setLong(4, computer.getCompany().getId());
	} else {
	    pPreparedStatement.setNull(4, Types.BIGINT);
	}
    }

    @Override
    public List<Computer> findAll(long pStart, int pSize) {
	Connection connect = null;
	Statement statement = null;
	try {
	    connect = dataSource.getConnection();
	    statement = connect.createStatement();
	    if (pSize > 0) {
		statement.setMaxRows(pSize);
	    } else {
		statement.setMaxRows(0);
		pSize = 0;
	    }
	    ResultSet resultSet = statement
		    .executeQuery("SELECT * FROM computer LEFT JOIN company on computer.company_id=company_id"
			    + " LIMIT " + pSize + " OFFSET " + pStart);
	    return ComputerMapper.toComputers(resultSet);
	} catch (SQLException e) {
	    daoLogger.error(e.toString());
	    throw new DAOException(e);
	} finally {
	    try {
		if (connect.getAutoCommit() == true) {
		    ConnectionFactory.closeConnection(connect, statement);
		}
	    } catch (Exception e) {
		daoLogger.error(e.toString());
	    }
	}
    }

    @Override
    public Computer create(Computer obj) {
	Connection connect = null;
	PreparedStatement createStatement = null;
	try {
	    connect = dataSource.getConnection();
	    createStatement = connect.prepareStatement(
		    "INSERT INTO `computer` (name, introduced, discontinued, company_id) VALUES (?, ?, ?, ?);",
		    Statement.RETURN_GENERATED_KEYS);
	    createStatement.clearParameters();
	    prepareStatement(obj, createStatement);
	    int nbResult = createStatement.executeUpdate();
	    if (nbResult == 1) {
		ResultSet a = createStatement.getGeneratedKeys();
		a.next();
		obj.setId(a.getInt(1));
		if (daoLogger.isInfoEnabled()) {
		    daoLogger.info("Création de " + obj);
		}
		return obj;
	    }
	    daoLogger.error("Erreur de création");
	    return null;
	} catch (SQLException e) {
	    daoLogger.error(e.getMessage());
	    return null;
	} finally {
	    try {
		if (connect.getAutoCommit() == true) {
		    ConnectionFactory.closeConnection(connect, createStatement);
		}
	    } catch (Exception e) {
		daoLogger.error(e.toString());
	    }
	}
    }

    @Override
    public boolean delete(Computer obj) {
	PreparedStatement deleteStatement = null;//
	Connection connect = null;
	if (obj == null || obj.getId() <= 0) {
	    throw new IllegalArgumentException("Null or Not Persisted Object");
	}
	try {
	    connect = dataSource.getConnection();
	    deleteStatement = connect.prepareStatement("DELETE FROM computer WHERE id = ?");
	    deleteStatement.setLong(1, obj.getId());
	    int nbLines = deleteStatement.executeUpdate();
	    daoLogger.info("Supreession de " + obj.getId() + " reussi");
	    return nbLines == 1;
	} catch (SQLException e) {
	    daoLogger.error(e.toString());
	    throw new DAOException(e);
	} finally {
	    try {
		if (connect.getAutoCommit() == true) {
		    ConnectionFactory.closeConnection(connect, deleteStatement);
		}
	    } catch (Exception e) {
		daoLogger.error(e.toString());
	    }
	}
    }

    @Override
    public Computer update(Computer obj) {
	Connection connect = null;
	PreparedStatement updateStatement = null;
	if (obj == null || obj.getId() <= 0) {
	    throw new IllegalArgumentException("Pas d'objet ou objet non enregistré");
	}
	try {
	    connect = dataSource.getConnection();
	    updateStatement = connect.prepareStatement(
		    "UPDATE `computer` SET name=?, introduced=?, discontinued=?, company_id=? WHERE id = ?;");
	    prepareStatement(obj, updateStatement);
	    updateStatement.setLong(5, obj.getId());
	    int nbResult = updateStatement.executeUpdate();
	    if (nbResult == 1) {
		if (daoLogger.isInfoEnabled()) {
		    daoLogger.info("Update de " + obj.getId() + " reussi");
		}
		return obj;
	    }
	    daoLogger.error("Error on update");
	    return null;
	} catch (SQLException e) {
	    daoLogger.error(e.toString());
	    e.printStackTrace();
	    throw new DAOException(e);
	} finally {
	    try {
		if (connect.getAutoCommit() == true) {
		    ConnectionFactory.closeConnection(connect, updateStatement);
		}
	    } catch (Exception e) {
		daoLogger.error(e.toString());
	    }
	}
    }
 
    public Computer find(long id) {
	Connection connect = null;
	PreparedStatement findStatement = null;
	try {
	    connect = dataSource.getConnection();
	    findStatement = connect.prepareStatement("SELECT * FROM computer LEFT JOIN company ON company.id=computer.company_id WHERE computer.id = ?");
	    findStatement.setLong(1, id);
	    ResultSet resultSet = findStatement.executeQuery();
	    if (!resultSet.isBeforeFirst()) {
		return null;
	    }
	    resultSet.next();
	    return ComputerMapper.toComputer(resultSet);
	} catch (SQLException e) {
	    daoLogger.error(e.toString());
	    throw new DAOException(e);
	} finally {
	    try {
		if (connect.getAutoCommit() == true) {
		    ConnectionFactory.closeConnection(connect, findStatement);
		}
	    } catch (Exception e) {
		daoLogger.error(e.toString());
	    }
	}
    }

    public int count() {
	Connection connect = null;
	String query = "SELECT count(*) from computer";
	Statement stm = null;
	ResultSet result = null;
	try {
	    connect = dataSource.getConnection();
	    stm = connect.createStatement();
	    result = stm.executeQuery(query);
	    if (result.first()) {
		return result.getInt(1);
	    } else {
		return 0;
	    }
	} catch (SQLException e) {
	    daoLogger.error(e.getMessage());
	    return 0;
	} 
    }

    public List<Computer> findWithSearch(Pager pager) {
	Connection connect = null;
	PreparedStatement statement = null, statement2;
	try {
	    connect = dataSource.getConnection();
	    statement = connect.prepareStatement(queryOrdered(pager));
	    if (pager.search != null) {
		statement2 = connect.prepareStatement(
			"SELECT count(*) FROM computer where name LIKE ? OR company_id IN (SELECT id FROM company where name LIKE ?)");
		statement.setInt(3, pager.nbByPage);
		statement.setInt(4, pager.nbByPage * (pager.currentPage - 1));
		statement.setString(1, "%" + pager.getSearch() + "%");
		statement.setString(2, "%" + pager.getSearch() + "%");
		statement2.setString(1, "%" + pager.getSearch() + "%");
		statement2.setString(2, "%" + pager.getSearch() + "%");
	    } else {
		statement2 = connect.prepareStatement("SELECT count(*) FROM computer");
		statement.setInt(1, pager.nbByPage);
		statement.setInt(2, pager.nbByPage * (pager.currentPage - 1));
	    }
	    ResultSet resultSet = statement.executeQuery();
	    ResultSet resultSetCount = statement2.executeQuery();
	    resultSetCount.first();
	    pager.setTotalCount(resultSetCount.getInt(1));
	    return ComputerMapper.toComputers(resultSet);
	} catch (SQLException e) {
	    daoLogger.error(e.toString());
	    throw new DAOException(e);
	} finally {  //  private static CompanyDaoImpl instance = new CompanyDaoImpl();
	    try {
		if (connect.getAutoCommit()) {
		    ConnectionFactory.closeConnection(connect, statement);
		}
	    } catch (Exception e) {
		e.printStackTrace();
		daoLogger.error(e.toString());
	    }
	}
    }

    @Transactional()
    public boolean deleteAll(long id) {
	Connection con = null;
	Statement stm = null;
	try {
	    con = dataSource.getConnection();
	    stm = con.createStatement();
	    stm.executeUpdate("DELETE from computer where company_id=" + id);
	} catch (SQLException e) {
	    daoLogger.error(e.toString());
	    return false;
	} finally {
	    try {
		if (con.getAutoCommit() == true) {
		    ConnectionFactory.closeConnection(con, stm);
		}
	    } catch (Exception e) {
		daoLogger.error(e.toString());
	    }
	}
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
	return "SELECT * FROM computer LEFT JOIN company on computer.company_id=company.id " + whereSentence + " LIMIT ? OFFSET ?";
    }
}
