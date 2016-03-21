package com.excilys.formation.computerdatabase.persist.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface RowMapper<T> {

    T mapRow(ResultSet rs) throws SQLException;

    List<T> mapRows(ResultSet rs) throws SQLException;
}
