package com.excilys.formation.computerdatabase.servlets;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.formation.computerdatabase.service.impl.CompanyServiceImpl;
import com.excilys.formation.computerdatabase.service.impl.ComputerServiceJspImpl;

public class CreateComputer extends HttpServlet {
	public static final String VUE = "/WEB-INF/views/addComputer.jsp";

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<Integer, String> map= CompanyServiceImpl.getMap();
		 request.setAttribute("map", map );	
		this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ComputerServiceJspImpl service = new ComputerServiceJspImpl();
		service.create(request);
		 request.setAttribute("service", service );	
		this.getServletContext().getRequestDispatcher(VUE).forward(request,response);

	}

}
