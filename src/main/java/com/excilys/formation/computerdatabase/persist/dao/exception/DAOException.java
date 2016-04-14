package com.excilys.formation.computerdatabase.persist.dao.exception;

import java.sql.SQLException;

public class DAOException extends RuntimeException {

    public DAOException(SQLException e) {

    }

}
