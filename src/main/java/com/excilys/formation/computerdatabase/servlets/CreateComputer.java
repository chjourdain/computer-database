package com.excilys.formation.computerdatabase.servlets;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.formation.computerdatabase.model.Computer;
import com.excilys.formation.computerdatabase.persist.dao.mapper.ComputerMapper;
import com.excilys.formation.computerdatabase.service.ComputerService;
import com.excilys.formation.computerdatabase.service.impl.CompanyServiceImpl;
import com.excilys.formation.computerdatabase.service.impl.ComputerServiceImpl;

public class CreateComputer extends HttpServlet {
    public static final String VUE = "/WEB-INF/views/addComputer.jsp";

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	Map<Integer, String> map = CompanyServiceImpl.getMap();
	request.setAttribute("map", map);
	this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	ComputerService service = ComputerServiceImpl.getComputerService();
	ComputerMapper cM = new ComputerMapper();
	Computer c1 = cM.mapRow(request);
	String result = null;
	if (c1 != null) {
	    if(service.create(c1) == null){
		result="Computer not created";
	    }
	    else {
		result="Computer added";
	    };
	}
	Map<Integer, String> map = CompanyServiceImpl.getMap();
	request.setAttribute("map", map);
	request.setAttribute("error", cM.getErreur());
	request.setAttribute("result", result);
	this.getServletContext().getRequestDispatcher(VUE).forward(request, response);

    }

}
