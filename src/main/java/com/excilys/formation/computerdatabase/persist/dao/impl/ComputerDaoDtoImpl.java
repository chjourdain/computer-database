package com.excilys.formation.computerdatabase.persist.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import org.apache.log4j.Logger;
import com.excilys.formation.computerdatabase.model.dto.ComputerDTO;
import com.excilys.formation.computerdatabase.persist.connection.ConnectionFactory;
import com.excilys.formation.computerdatabase.persist.dao.GenericDao;
import com.excilys.formation.computerdatabase.persist.dao.exception.DAOException;
import com.excilys.formation.computerdatabase.persist.dao.mapper.ComputerDtoMapper;

/**
 * DAO which works directly with computerDTO
 * @author charles
 *
 */
public class ComputerDaoDtoImpl implements GenericDao<ComputerDTO> {

	private Logger daoLogger = Logger.getLogger(this.getClass());

	private static ComputerDaoDtoImpl instance;

	private ComputerDaoDtoImpl() {
	}

	public static ComputerDaoDtoImpl getComputerDTOImpl() {
		if (instance == null) {
			instance = new ComputerDaoDtoImpl();
		}
		return instance;
	}

	@Override
	public List<ComputerDTO> findAll(long pStart, int pSize) {
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
			return (new ComputerDtoMapper()).mapRows(resultSet);
		} catch (SQLException e) {
			daoLogger.error(e);
			throw new DAOException(e);
		}
	}
}
