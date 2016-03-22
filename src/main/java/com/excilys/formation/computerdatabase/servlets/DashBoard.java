package com.excilys.formation.computerdatabase.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.formation.computerdatabase.model.Computer;
import com.excilys.formation.computerdatabase.model.Pager;
import com.excilys.formation.computerdatabase.service.GenericService;
import com.excilys.formation.computerdatabase.service.impl.ComputerServiceImpl;

public class DashBoard extends HttpServlet {
    private static final String VUE = "/WEB-INF/views/dashboard.jsp"; 

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	Pager pager = createComputerPager(request);
	request.setAttribute("pager", pager);
	this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
    }

    public static Pager createComputerPager(HttpServletRequest request) {
	Pager pager;
	int count = ComputerServiceImpl.getComputerService().count();
	request.setAttribute("count", count);
	GenericService<Computer> service = ComputerServiceImpl.getComputerService();
	int page = 1;
	String search = null;

	if (request.getParameter("search") != "" && request.getParameter("search") != null) {
	  search = request.getParameter("search");
	    
	}
	
	pager = new Pager(10, page, service, search);
	if (request.getParameter("Page") != "" && request.getParameter("Page") != null) {
	    page = Integer.parseInt(request.getParameter("Page"));
	    pager.setPageActuelle(page);
	}
	if (request.getParameter("Nb") != "" && request.getParameter("Nb") != null) {
	    page = Integer.parseInt(request.getParameter("Nb"));
	    pager.setNbParPage(page);
	}
	
	if (request.getParameter("search") != "" && request.getParameter("search") != null) {
	    pager.setSearch(request.getParameter("search"));
	    
	}
	
	return pager;
    }
}
