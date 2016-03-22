package com.excilys.formation.computerdatabase.persist.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import com.excilys.formation.computerdatabase.model.Company;
import com.excilys.formation.computerdatabase.model.Computer;
import com.excilys.formation.computerdatabase.persist.dao.CompanyDao;
import com.excilys.formation.computerdatabase.persist.dao.impl.CompanyDaoImpl;
import com.excilys.formation.computerdatabase.persist.dao.impl.ComputerDaoImpl;
import com.excilys.formation.computerdatabase.service.impl.CompanyServiceImpl;

public class ComputerMapper implements RowMapper<Computer> {
    public static final String ATT_NAME = "computerName";
    public static final String ATT_COMPANY = "companyId";
    public static final String ATT_INTRODUCED = "introduced";
    public static final String ATT_DISCONTINUED = "discontinued";
    public static final String ATT_ID = "id";
    public static final String regex = "^[0-9]{4}-[0-1][0-9]-[0-3][0-9]$|^$";
    public Map<String, String> erreur = new HashMap<>();

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

    public Computer mapRow(HttpServletRequest request) {

	String name = request.getParameter(ATT_NAME);
	String introduced = request.getParameter(ATT_INTRODUCED);
	String discontinued = request.getParameter(ATT_DISCONTINUED);
	String companyId = request.getParameter(ATT_COMPANY);
	String id = request.getParameter(ATT_ID);
	long id2=0;
	if (id != null) {
	    id2 = Long.valueOf(id);
	}
	if (name == null) {
	    return null;
	}
	if (!Pattern.matches(regex, introduced) && (introduced != "")) {
	    erreur.put("introduced", "Erreur de format, renseigner YYYY-MM-JJ");
	}
	if (!Pattern.matches(regex, discontinued) && discontinued != "") {
	    erreur.put("discontinued", "Erreur de format, renseigner YYYY-MM-JJ");
	}
	Company company = null;
	if (companyId != null || Integer.valueOf(companyId) != 0) {
	    company = (Company) CompanyServiceImpl.getCompanyService().find(Integer.valueOf(companyId));
	}
	Computer computer = null;
	if (erreur.isEmpty()) {
	    LocalDate intro = null;
	    LocalDate disco = null;
	    if (introduced != null && introduced != "") {
		intro = LocalDate.parse(introduced);
	    }
	    if (discontinued != null && discontinued != "") {
		disco = LocalDate.parse(discontinued);
	    }
	    computer = new Computer(id2,name, intro, disco, company);
	}
	return computer;
    }

    @Override
    public List<Computer> mapRows(ResultSet rs) throws SQLException {
	List<Computer> computers = new ArrayList<>();
	while (rs.next()) {
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
    
    public Map<String, String> getErreur() {
	return erreur;
    }
}
