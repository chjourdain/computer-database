package com.excilys.formation.computerdatabase.servlets;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.excilys.formation.computerdatabase.model.Pager;
import com.excilys.formation.computerdatabase.persist.dao.mapper.PagerRequestMapper;
import com.excilys.formation.computerdatabase.service.impl.ComputerServiceImpl;


@WebServlet(name = "DashBoard", urlPatterns = "/dashboard")
public class DashBoard extends HttpServlet {
    private static final String VUE = "/WEB-INF/views/dashboard.jsp";
    @Autowired
    ComputerServiceImpl service;
    
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init();
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	Pager pager = PagerRequestMapper.get(request);
	service.fillPage(pager);
	//ComputerServiceImpl.getComputerService().fillPage(pager);
	request.setAttribute("pager", pager);
	request.getServletContext().getRequestDispatcher(VUE).forward(request, response);
    }

}
