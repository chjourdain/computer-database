package com.excilys.formation.computerdatabase.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.formation.computerdatabase.model.Computer;
import com.excilys.formation.computerdatabase.model.dto.ComputerDTO;
import com.excilys.formation.computerdatabase.persist.dao.mapper.ComputerDtoMapper;
import com.excilys.formation.computerdatabase.persist.dao.mapper.ComputerMapper;
import com.excilys.formation.computerdatabase.service.ComputerService;
import com.excilys.formation.computerdatabase.service.impl.ComputerServiceImpl;

public class EditComputer extends HttpServlet {
    private static final String VUE = "/WEB-INF/views/dashboard.jsp";
    private static final String VUE_EDIT = "/WEB-INF/views/editComputer.jsp";

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	// get the computer id, send back the user to dashboard.jsp if not valid.
	String id = request.getParameter("id");
	if (id == null || id.equals("0") || id.isEmpty()) {
	    this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
	    return;
	}
	// get the computer to send its data to the jsp.
	ComputerService service = ComputerServiceImpl.getComputerService();
	ComputerDTO computer = ComputerDtoMapper.mapRow(service.find(Long.valueOf(id)));
	request.setAttribute("computer", computer);
	// pass the list of companies in a hashmap as attribute.
	CreateComputer.CompaniesInHashMap(request);
	this.getServletContext().getRequestDispatcher(VUE_EDIT).forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	ComputerService service = ComputerServiceImpl.getComputerService();
	ComputerMapper cM = new ComputerMapper();
	Computer c1 = cM.mapRow(request);
	if ((c1 == null | (!cM.erreur.isEmpty()))) {
	    //request invalid.
	   long i = Long.valueOf(request.getParameter("id").toString());
	   request.setAttribute("computer", ComputerDtoMapper.mapRow(service.find(Long.valueOf(i))));
	   request.setAttribute("result", "failure in updating Computer");
	   request.setAttribute("error", cM.getErreur());
	this.getServletContext().getRequestDispatcher(VUE_EDIT).forward(request, response);
	return;
	}
	 c1 = service.update(c1) ;
	response.sendRedirect("dashboard");
    }
}