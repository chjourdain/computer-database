package com.excilys.formation.computerdatabase.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.formation.computerdatabase.model.Pager;
import com.excilys.formation.computerdatabase.model.dto.ComputerDTO;
import com.excilys.formation.computerdatabase.service.GenericService;
import com.excilys.formation.computerdatabase.service.impl.ComputerServiceDtoImpl;
import com.excilys.formation.computerdatabase.service.impl.ComputerServiceImpl;

public class DashBoard extends HttpServlet {
	private static final String VUE = "/WEB-INF/views/dashboard.jsp";
	private Pager<ComputerDTO> pager;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int count = ComputerServiceImpl.getComputerService().count();
		request.setAttribute("count", count);
		GenericService<ComputerDTO> service = ComputerServiceDtoImpl.getInstance();
		int page = 1;
		if (pager == null) {
			pager = new Pager<ComputerDTO>(10, page, service);
		}
		if (request.getParameter("Page") != "" && request.getParameter("Page") != null) {
			page = Integer.parseInt(request.getParameter("Page"));
			pager.setPageActuelle(page);
		}
		if (request.getParameter("Nb") != "" && request.getParameter("Nb") != null) {
			page = Integer.parseInt(request.getParameter("Nb"));
			pager.setNbParPage(page);
		}
		request.setAttribute("pager", pager);
		this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
	}
}
