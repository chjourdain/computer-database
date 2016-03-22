package com.excilys.formation.computerdatabase.persist.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.excilys.formation.computerdatabase.model.Company;
import com.excilys.formation.computerdatabase.persist.connection.ConnectionFactory;
import com.excilys.formation.computerdatabase.persist.dao.CompanyDao;

public class CompanyDaoImpl implements CompanyDao {

    private Logger daoLogger = Logger.getLogger(this.getClass());
    private static CompanyDaoImpl instance = new CompanyDaoImpl();

    private CompanyDaoImpl() {
	daoLogger.info("Initialisation du DAO Company");
    }

    public static CompanyDao getCompanyDaoImpl() {
	return instance;
    }

    public ArrayList<Company> list() {
	String query = "Select id, name from company";
	ResultSet result;
	ArrayList<Company> companies;
	Statement stm = null;
	Connection connect = null;
	try {
	    connect = ConnectionFactory.getConnectionManager().getConn();
	    stm = connect.createStatement();
	    result = stm.executeQuery(query);
	    companies = new ArrayList<>();
	    while (result.next()) {
		Company c1 = new Company(result.getString("name"), result.getInt("id"));
		companies.add(c1);
	    }
	} catch (SQLException e) {
	    daoLogger.error(e);
	    return null;
	} finally {
	    ConnectionFactory.getConnectionManager().closeConnection(connect, stm);
	}
	return companies;
    }

    @Override
    public Company find(long id) {
	String query = "Select id, name from company where id=" + id;
	ResultSet result;
	Company company = null;
	Connection connect = ConnectionFactory.getConnectionManager().getConn();
	Statement stm = null;
	try {
	    stm = connect.createStatement();
	    result = stm.executeQuery(query);
	    if (result.next()) {
		company = new Company(result.getString("name"), result.getInt("id"));
	    }
	} catch (SQLException e) {
	    daoLogger.error(e);
	    return null;
	} finally {
	    ConnectionFactory.getConnectionManager().closeConnection(connect, stm);
	}
	return company;
    }

    public int count() {
	Connection connect = ConnectionFactory.getConnectionManager().getConn();
	String query = "SELECT count(*) from company";
	ResultSet result;
	Statement stm = null;
	try {
	    stm = connect.createStatement();
	    result = stm.executeQuery(query);
	    if (result.first()) {
		return result.getInt(1);
	    } else {
		return 0;
	    }
	} catch (SQLException e) {
	    e.printStackTrace();
	    return 0;
	} finally {
	    ConnectionFactory.getConnectionManager().closeConnection(connect, stm);
	}
    }

    @Override
    public List<Company> findAll(long index, int nbrElement) {
	Connection connect = ConnectionFactory.getConnectionManager().getConn();
	String query = "Select * from company" + " LIMIT " + index + "," + nbrElement;
	ResultSet result;
	ArrayList<Company> companies;
	Statement stm = null;
	try {
	    stm = connect.createStatement();
	    result = stm.executeQuery(query);
	    companies = new ArrayList<>();
	    while (result.next()) {
		Company c1 = new Company(result.getString("name"), result.getInt("id"));
		companies.add(c1);
	    }
	} catch (SQLException e) {
	    daoLogger.error(e);
	    return null;
	} finally {
	    ConnectionFactory.getConnectionManager().closeConnection(connect, stm);
	}
	return companies;
    }

    public Company findByName(String id) {
	Connection connect = ConnectionFactory.getConnectionManager().getConn();
	String query = "Select id, name from company where name=" + id;
	ResultSet result;
	Company company = null;
	Statement stm = null;
	try {
	    stm = connect.createStatement();
	    result = stm.executeQuery(query);
	    if (result.next()) {
		company = new Company(result.getString("name"), result.getInt("id"));
	    }
	} catch (SQLException e) {
	    daoLogger.error(e.getMessage());
	    return null;
	} finally {
	    ConnectionFactory.getConnectionManager().closeConnection(connect, stm);
	}
	return company;
    }
}
