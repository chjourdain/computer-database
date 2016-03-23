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

    /**
     * Method which create the Pager with the requests parameters (page, nbPage, order by , search). 
     * @param request contains parameters of the pager
     * @return Pager with the updated list of ComputerDTO. 
     */
    public static Pager createComputerPager(HttpServletRequest request) {
	Pager pager;
	int count = ComputerServiceImpl.getComputerService().count();
	request.setAttribute("count", count);
	GenericService<Computer> service = ComputerServiceImpl.getComputerService();
	int page = 1;
	String search = null;

	if (request.getParameter("search") != null && !request.getParameter("search").isEmpty()) {
	    search = request.getParameter("search");
	}
	pager = new Pager(10, page, service, search);
	if (request.getParameter("Page") != null && !request.getParameter("Page").isEmpty()) {
	    page = Integer.parseInt(request.getParameter("Page"));
	    pager.setPageActuelle(page);
	}
	if (request.getParameter("Nb") != null && !request.getParameter("Nb").isEmpty()) {
	    page = Integer.parseInt(request.getParameter("Nb"));
	    pager.setNbParPage(page);
	}

	if (request.getParameter("search") != null &&!request.getParameter("search").isEmpty()) {
	    pager.setSearch(request.getParameter("search"));
	}
	if (request.getParameter("order") != null && !request.getParameter("order").isEmpty()) {
	    pager.setOrderBy(request.getParameter("order"));
	}
	pager.updateListe();
	return pager;
    }
}
