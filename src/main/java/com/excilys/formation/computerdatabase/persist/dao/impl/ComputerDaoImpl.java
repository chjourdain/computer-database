package com.excilys.formation.computerdatabase.persist.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.List;

import org.apache.log4j.Logger;

import com.excilys.formation.computerdatabase.model.Computer;
import com.excilys.formation.computerdatabase.persist.connection.ConnectionFactory;
import com.excilys.formation.computerdatabase.persist.dao.ComputerDao;
import com.excilys.formation.computerdatabase.persist.dao.exception.DAOException;
import com.excilys.formation.computerdatabase.persist.dao.mapper.ComputerMapper;

public class ComputerDaoImpl implements ComputerDao {

    private Logger daoLogger = Logger.getLogger(this.getClass());
    public static final ComputerDaoImpl INSTANCE = new ComputerDaoImpl();

    private ComputerDaoImpl() {
    }

    public static ComputerDao getComputerDao() {
	return INSTANCE;
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
	if (computer.getCompany() != null) {
	    pPreparedStatement.setLong(4, computer.getCompany().getId());
	} else {
	    pPreparedStatement.setNull(4, Types.BIGINT);
	}
    }

    @Override
    public List<Computer> findAll(long pStart, int pSize) {
	Connection connect = ConnectionFactory.getConnectionManager().getConn();
	Statement statement;
	try {
	    statement = connect.createStatement();
	    if (pSize > 0) {
		statement.setMaxRows(pSize);
	    } else {
		statement.setMaxRows(0);
		pSize = 0;
	    }
	    ResultSet resultSet = statement.executeQuery("SELECT * FROM computer LIMIT " + pSize + " OFFSET " + pStart);
	    return (new ComputerMapper()).mapRows(resultSet);
	} catch (SQLException e) {
	    daoLogger.error(e);
	    throw new DAOException(e);
	}
    }

    @Override
    public Computer create(Computer obj) {
	Connection connect = null;
	PreparedStatement createStatement = null;
	try {
	    connect = ConnectionFactory.getConnectionManager().getConn();
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
	    ConnectionFactory.getConnectionManager().closeConnection(connect, createStatement);
	}
    }

    @Override
    public boolean delete(Computer obj) {
	PreparedStatement deleteStatement = null;
	Connection connect = ConnectionFactory.getConnectionManager().getConn();
	if (obj == null || obj.getId() <= 0) {
	    throw new IllegalArgumentException("Null or Not Persisted Object");
	}
	try {
	    deleteStatement = connect.prepareStatement("DELETE FROM computer WHERE id = ?");
	    deleteStatement.setLong(1, obj.getId());
	    int nbLines = deleteStatement.executeUpdate();
	    daoLogger.info("Supreession de " + obj.getId() + " reussi");
	    return nbLines == 1;
	} catch (SQLException e) {
	    daoLogger.error(e);
	    throw new DAOException(e);
	} finally {
	    ConnectionFactory.getConnectionManager().closeConnection(connect, deleteStatement);
	}
    }

    @Override
    public Computer update(Computer obj) {
	Connection connect = ConnectionFactory.getConnectionManager().getConn();
	PreparedStatement updateStatement = null;
	if (obj == null || obj.getId() <= 0) {
	    throw new IllegalArgumentException("Pas d'objet ou objet non enregistré");
	}
	try {
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
	    daoLogger.error(e);
	    throw new DAOException(e);
	} finally {
	    ConnectionFactory.getConnectionManager().closeConnection(connect, updateStatement);
	}
    }

    public Computer find(long id) {
	Connection connect = ConnectionFactory.getConnectionManager().getConn();
	PreparedStatement findStatement = null;
	try {
	    findStatement = connect.prepareStatement("SELECT * FROM `computer` WHERE id = ?");
	    findStatement.setLong(1, id);
	    ResultSet resultSet = findStatement.executeQuery();
	    if (!resultSet.isBeforeFirst()) {
		return null;
	    }
	    resultSet.next();
	    return (new ComputerMapper()).mapRow(resultSet);
	} catch (SQLException e) {
	    daoLogger.error(e);
	    throw new DAOException(e);
	} finally {
	    ConnectionFactory.getConnectionManager().closeConnection(connect, findStatement);
	}
    }

    public int count() {
	Connection connect = ConnectionFactory.getConnectionManager().getConn();
	String query = "SELECT count(*) from computer";
	Statement stm = null;
	ResultSet result = null;
	try {
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
	} finally {
	    ConnectionFactory.getConnectionManager().closeConnection(connect, stm, result);
	}
    }
}
