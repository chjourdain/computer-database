package com.excilys.formation.computerdatabase.persist.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.excilys.formation.computerdatabase.model.Company;
import com.excilys.formation.computerdatabase.model.dto.ComputerDTO;
import com.excilys.formation.computerdatabase.persist.dao.CompanyDao;
import com.excilys.formation.computerdatabase.persist.dao.impl.CompanyDaoImpl;

public class ComputerDtoMapper implements RowMapper<ComputerDTO> {

	@Override
	public ComputerDTO mapRow(ResultSet rs) throws SQLException {

		String companyName = null;
		String companyId = null;
		if ((companyId = rs.getString("company_id")) != null) {
			CompanyDao cCD = CompanyDaoImpl.getCompanyDaoImpl();
			Company company = cCD.find(Integer.parseInt(rs.getString("company_id")));
			companyName = company.getName();
		}
		ComputerDTO c1 = new ComputerDTO(rs.getString("id"), rs.getString("name"), rs.getString("introduced"),
				companyName, companyId , rs.getString("discontinued"));
		return c1;
	}

	@Override
	public List<ComputerDTO> mapRows(ResultSet rs) throws SQLException {
		List<ComputerDTO> computers = new ArrayList<>();
		while (rs.next()) {
			String companyName = null;
			String companyId = null;
			if ((companyId = rs.getString("company_id")) != null) {
				CompanyDao cCD = CompanyDaoImpl.getCompanyDaoImpl();
				Company company = cCD.find(Integer.parseInt(rs.getString("company_id")));
				companyName = company.getName();
			}
			ComputerDTO c1 = new ComputerDTO(rs.getString("id"), rs.getString("name"), rs.getString("introduced"),
					companyName, companyId , rs.getString("discontinued"));
			computers.add(c1);
		}
		return computers;
	}
}