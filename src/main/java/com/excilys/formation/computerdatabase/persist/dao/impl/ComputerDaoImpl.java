package com.excilys.formation.computerdatabase.persist.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.excilys.formation.computerdatabase.dao.persist.exception.DAOException;
import com.excilys.formation.computerdatabase.dao.persist.mapper.RowMapper;
import com.excilys.formation.computerdatabase.model.Computer;
import com.excilys.formation.computerdatabase.persist.connection.ConnectionFactory;
import com.excilys.formation.computerdatabase.persist.dao.CompanyDao;
import com.excilys.formation.computerdatabase.persist.dao.ComputerDao;

public class ComputerDaoImpl implements ComputerDao {

	private Logger daoLogger = Logger.getLogger(this.getClass());
	public static final ComputerDaoImpl INSTANCE = new ComputerDaoImpl();
	
	private ComputerDaoImpl(){}
	public static ComputerDao getComputerDao(){
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
			pPreparedStatement.setString(3, computer.getIntroduced().toString());
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

	@Deprecated
	public List<Computer> list(int page) {
		int first = page * ROW_BY_PAGE;
		List<Computer> computers;
		computers = findAll(first, ROW_BY_PAGE);
		return computers;
	}

	@Override
	public Computer create(Computer obj) {
		Connection connect = null;
		PreparedStatement CreateStatement = null;
		try {
			connect = ConnectionFactory.getConnectionManager().getConn();
			CreateStatement = connect.prepareStatement(
					"INSERT INTO `computer` (name, introduced, discontinued, company_id) VALUES (?, ?, ?, ?);",
					Statement.RETURN_GENERATED_KEYS);
			CreateStatement.clearParameters();
			prepareStatement(obj, CreateStatement);
			int nbResult = CreateStatement.executeUpdate();
			if (nbResult == 1) {
				ResultSet a = CreateStatement.getGeneratedKeys();
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
			throw new DAOException(e);
		} finally {
			ConnectionFactory.getConnectionManager().closeConnection(connect, CreateStatement);
		}
	}

	@Override
	public boolean delete(Computer obj) {
		PreparedStatement DeleteStatement = null;
		Connection connect = ConnectionFactory.getConnectionManager().getConn();
		if (obj == null || obj.getId() <= 0) {
			throw new IllegalArgumentException("Null or Not Persisted Object");
		}
		try {
			DeleteStatement = connect.prepareStatement("DELETE FROM `computer` WHERE id = ?");
			DeleteStatement.setLong(1, obj.getId());
			int nbLines = DeleteStatement.executeUpdate();
			daoLogger.info("Supreession de " + obj.getId()+" reussi" );
			return nbLines == 1;
		} catch (SQLException e) {
			daoLogger.error(e);
			throw new DAOException(e);
		} finally {
			ConnectionFactory.getConnectionManager().closeConnection(connect, DeleteStatement);
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

	public Computer find(int id) {
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

	private class ComputerMapper implements RowMapper<Computer> {

		@Override
		public Computer mapRow(ResultSet rs) throws SQLException {
			Computer c1 = new Computer(rs.getInt("computer.id"), rs.getString("computer.name"));

			if (rs.getTimestamp(3) != null) {
				c1.setIntroduced(rs.getDate(3).toLocalDate());
			}
			if (rs.getTimestamp(4) != null) {
				c1.setDiscontinued(rs.getDate(4).toLocalDate());
			}
			if (rs.getString("company_id") != null) {
				CompanyDao cCD = CompanyDaoImpl.getCompanyDaoImpl();
				{
					c1.setCompany(cCD.findByName(rs.getString("company_id")));
				}
			}
			return c1;
		}

		@Override
		public List<Computer> mapRows(ResultSet rs) throws SQLException {
			List<Computer> computers = new ArrayList<>();
			while(rs.next()){
				Computer c1 = new Computer(rs.getInt("computer.id"), rs.getString("computer.name"));

				if (rs.getTimestamp(3) != null) {
					c1.setIntroduced(rs.getDate(3).toLocalDate());
				}
				if (rs.getTimestamp(4) != null) {
					c1.setDiscontinued(rs.getDate(4).toLocalDate());
				}
				if (rs.getString("company_id") != null) {
					CompanyDao cCD = CompanyDaoImpl.getCompanyDaoImpl();
					{
						c1.setCompany(cCD.findByName(rs.getString("company_id")));
					}
				}
				computers.add(c1);	
			}	
			return computers;
		}		
	}

	
}
