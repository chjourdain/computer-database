package com.excilys.formation.computerdatabase.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.formation.computerdatabase.service.impl.ComputerServiceImpl;

public class DashBoard extends HttpServlet {
	private static final String VUE = "/WEB-INF/views/dashboard.jsp";
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		int count = ComputerServiceImpl.getComputerService().count();
		request.setAttribute("count", count);
		this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
	}
}
