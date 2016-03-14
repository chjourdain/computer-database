package com.excilys.formation.computerdatabase.servlets;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.formation.computerdatabase.model.Company;
import com.excilys.formation.computerdatabase.model.Computer;
import com.excilys.formation.computerdatabase.service.impl.CompanyServiceImpl;
import com.excilys.formation.computerdatabase.service.impl.ComputerServiceImpl;

public class CreateComputer extends HttpServlet {
	public static final String VUE = "/WEB-INF/static/views/addComputer.html";
	public static final String ATT_NAME = "computerName";
	public static final String ATT_COMPANY = "companyId";
	public static final String ATT_INTRODUCED = "introduced";
	public static final String ATT_DISCONTINUED = "discontinued";

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		String a = request.getParameter(ATT_COMPANY);
		Integer i=null;
//		if (a != null){
		i = Integer.valueOf(request.getParameter(ATT_COMPANY));
		//}	
		Company company;
//		if (i == null) {
//			company = null;
//		} else {
			company = ((CompanyServiceImpl) CompanyServiceImpl.getCompanyService()).find(i);
//		}
		Computer computer = new Computer(request.getParameter(ATT_NAME),
			LocalDate.parse(request.getParameter(ATT_INTRODUCED)),
			LocalDate.parse(request.getParameter(ATT_DISCONTINUED)), company);
		ComputerServiceImpl.getComputerService().create(computer);
		this.getServletContext().getRequestDispatcher(VUE).forward(request,response);

	}

}
