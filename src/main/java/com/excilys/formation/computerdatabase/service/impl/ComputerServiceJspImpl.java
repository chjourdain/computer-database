package com.excilys.formation.computerdatabase.service.impl;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import com.excilys.formation.computerdatabase.model.Company;
import com.excilys.formation.computerdatabase.model.Computer;
import com.excilys.formation.computerdatabase.persist.dao.impl.ComputerDaoImpl;
import com.excilys.formation.computerdatabase.service.ComputerService;

public class ComputerServiceJspImpl implements ComputerService {

	public static final String ATT_NAME = "computerName";
	public static final String ATT_COMPANY = "companyId";
	public static final String ATT_INTRODUCED = "introduced";
	public static final String ATT_DISCONTINUED = "discontinued";
	public static final String regex = "^[0-9]{4}-[0-1][0-9]-[0-3][0-9]$";
	private ComputerDaoImpl computerDao = ComputerDaoImpl.INSTANCE;
	public Map<String, String> erreur = new HashMap<>();
	public String resultat;
	public String color="red";

	public int count() {
		return computerDao.count();
	}

	public Computer find(int id) {
		if (id < 0) {
			return null;
		}
		return computerDao.find(id);
	}

	public Computer create(HttpServletRequest request) {

		String Name = request.getParameter(ATT_NAME);
		String introduced = request.getParameter(ATT_INTRODUCED);
		String discontinued = request.getParameter(ATT_DISCONTINUED);
		String companyId = request.getParameter(ATT_COMPANY);

		if (Name == null) {
			return null;
		}
		if (!Pattern.matches(regex, introduced) && introduced != null) {
			erreur.put("introduced", "Erreur de format, renseigner YYYY-MM-JJ");
		}
		if (!Pattern.matches(regex, discontinued) && discontinued != null) {
			erreur.put("discontinued", "Erreur de format, renseigner YYYY-MM-JJ");
		}
		Company company = null;
		if (Integer.valueOf(companyId) != 0) {
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
			computer = new Computer(Name, intro, disco, company);
			computer = computerDao.create(computer);
		}
		if (computer == null) {
			resultat = "Ordinateur non crée";
		} else {
			resultat = "Ordinateur crée : " + computer;
			color="green";
		}
		return computer;
	}

	public Computer update(Computer c) {
		if (c == null) {
			return null;
		}
		return computerDao.update(c);

	}

	public void delete(Computer c) {
		if (c != null) {
			computerDao.delete(c);
		}

	}

	@Override
	public List<Computer> findAll(Long index, int nbrElement) {
		if (index < 0 || nbrElement <= 0) {
			return null;
		}
		return computerDao.findAll(index, nbrElement);
	}

	public Map<String, String> getErreur() {
		return erreur;
	}

	public String getResultat() {
		return resultat;
	}

	public String getColor() {
		return color;
	}

}
