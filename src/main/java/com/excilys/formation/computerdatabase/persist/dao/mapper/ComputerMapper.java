package com.excilys.formation.computerdatabase.persist.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.excilys.formation.computerdatabase.model.Computer;
import com.excilys.formation.computerdatabase.persist.dao.CompanyDao;
import com.excilys.formation.computerdatabase.persist.dao.impl.CompanyDaoImpl;

public class ComputerMapper implements RowMapper<Computer> {

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
			c1.setCompany(cCD.find(Integer.parseInt(rs.getString("company_id"))));
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
					c1.setCompany(cCD.find(Integer.parseInt(rs.getString("company_id"))));
				}
			}
			computers.add(c1);	
		}	
		return computers;
	}		
}
