package com.excilys.formation.computerdatabase.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.excilys.formation.computerdatabase.connection.ConnectionFactory;
import com.excilys.formation.computerdatabase.dao.exception.DAOException;
import com.excilys.formation.computerdatabase.modele.Computer;

public class ComputerDao extends GenericDao<Computer> {

	private Logger daoLogger = Logger.getLogger(this.getClass());
	private final PreparedStatement mCreateStatement;
	private final PreparedStatement mUpdateStatement;
	private final PreparedStatement mDeleteStatement;
	private final PreparedStatement mFindStatement;

	public ComputerDao(Connection conn) {
		super(conn);
		daoLogger.info("initialisation de computer dao");
		try {

			mCreateStatement = connect.prepareStatement(
					"INSERT INTO `computer` (name, introduced, discontinued, company_id) VALUES (?, ?, ?, ?);",
					Statement.RETURN_GENERATED_KEYS);
			mUpdateStatement = connect.prepareStatement(
					"UPDATE `computer` SET name=?, introduced=?, discontinued=?, company_id=? WHERE id = ?;");
			mDeleteStatement = connect.prepareStatement("DELETE FROM `computer` WHERE id = ?");
			mFindStatement = connect.prepareStatement("SELECT * FROM `computer` WHERE id = ?");
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	private void prepareStatement(Computer pT, PreparedStatement pPreparedStatement) throws SQLException {
		pPreparedStatement.setString(1, pT.getName());
		if (pT.getIntroduced() != null) {
			pPreparedStatement.setString(2, pT.getIntroduced().toString());
		} else {
			pPreparedStatement.setString(2, null);
		}
		if (pT.getDiscontinued() != null) {
			pPreparedStatement.setString(3, pT.getIntroduced().toString());
		} else {
			pPreparedStatement.setString(3, null);
		}
		if (pT.getCompanieName() != null) {
			pPreparedStatement.setInt(4, pT.getCompanieName().getId());
		} else {
			pPreparedStatement.setNull(4, Types.BIGINT);
		}
	}

	public List<Computer> findAll(int pStart, int pSize) {
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
			List<Computer> mCompanies = new ArrayList<>(resultSet.getFetchSize());

			while (resultSet.next()) {
				Computer computer = mapComputer(resultSet);
				mCompanies.add(computer);
			}
			return mCompanies;
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
	public boolean create(Computer obj) {
		try {
			mCreateStatement.clearParameters();

			prepareStatement(obj, mCreateStatement);

			int nbResult = mCreateStatement.executeUpdate();
			if (nbResult == 1) {
				ResultSet a = mCreateStatement.getGeneratedKeys();
				a.next();
				obj.setId(a.getInt(1));
				if (daoLogger.isInfoEnabled()) {
					daoLogger.info("Création de " + obj);
				}

				return true;

			}

			daoLogger.error("Erreur de création");
			return false;
		} catch (SQLException e) {
			daoLogger.error(e.getMessage());
			throw new DAOException(e);
		}
	}

	@Override
	public boolean delete(Computer obj) {

		if (obj == null || obj.getId() <= 0) {
			throw new IllegalArgumentException("Null or Not Persisted Object");
		}
		
		try {
			mDeleteStatement.setLong(1, obj.getId());
			int nbLines = mDeleteStatement.executeUpdate();
			
			daoLogger.info("Supreession de "+obj.getId()+(nbLines == 1 ? "reussi" : " raté"));
			return nbLines == 1;
		} catch (SQLException e) {
			daoLogger.error(e);
			throw new DAOException(e);
		}
	}
	
	@Override
	public boolean update(Computer obj) {
		if (obj == null || obj.getId() <= 0) {
			throw new IllegalArgumentException("Pas d'objet ou objet non enregistré");
		}
		
		try {
			prepareStatement(obj, mUpdateStatement);
			mUpdateStatement.setInt(5, obj.getId());
			int nbResult = mUpdateStatement.executeUpdate();
			if (nbResult == 1) {
				if (daoLogger.isInfoEnabled()) {
					daoLogger.info("Update de "+obj.getId()+" reussi");
				}
				
				return true;
			}
			daoLogger.error("Error on update");
			return false;
		} catch (SQLException e) {
			daoLogger.error(e);
			throw new DAOException(e);
		}
	}

	public Computer find(int id)  {
		try {
			mFindStatement.setLong(1, id);
			ResultSet resultSet = mFindStatement.executeQuery();
			
			if (!resultSet.isBeforeFirst()) {
				return null;
			}
			resultSet.next();
			return mapComputer(resultSet);
		} catch (SQLException e) {
			daoLogger.error(e);
			throw new DAOException(e);
		}
	}

	public int getNumberOfElement() {
		String query = "SELECT count(*) from computer";
		ResultSet result;
		try {
			Statement stm = connect.createStatement();
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

	private Computer mapComputer(ResultSet rs) throws SQLException {
		Computer c1 = new Computer(rs.getInt("computer.id"), rs.getString("computer.name"));

		if (rs.getTimestamp(3) != null) {
			c1.setIntroduced(rs.getDate(3).toLocalDate());
		}
		if (rs.getTimestamp(4) != null) {
			c1.setDiscontinued(rs.getDate(4).toLocalDate());
		}
		if (rs.getString("company_id") != null) {
			CompanyDao cCD = new CompanyDao((ConnectionFactory.getConnectionManager().getConn()));
			{
				c1.setCompanieName(cCD.findByName(rs.getString("company_id")));
			}
		}
		return c1;
	}

}
