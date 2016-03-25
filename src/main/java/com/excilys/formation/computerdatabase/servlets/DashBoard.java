package com.excilys.formation.computerdatabase.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.formation.computerdatabase.model.Computer;
import com.excilys.formation.computerdatabase.model.Pager;
import com.excilys.formation.computerdatabase.persist.dao.mapper.PagerRequestMapper;
import com.excilys.formation.computerdatabase.service.GenericService;
import com.excilys.formation.computerdatabase.service.impl.ComputerServiceImpl;

public class DashBoard extends HttpServlet {
    private static final String VUE = "/WEB-INF/views/dashboard.jsp";

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	Pager pager = PagerRequestMapper.get(request);
	ComputerServiceImpl.getComputerService().fillPage(pager);
	request.setAttribute("pager", pager);
	this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
    }

}
