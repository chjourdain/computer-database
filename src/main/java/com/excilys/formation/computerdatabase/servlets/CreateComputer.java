package com.excilys.formation.computerdatabase.servlets;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.excilys.formation.computerdatabase.model.Computer;
import com.excilys.formation.computerdatabase.persist.dao.mapper.ComputerMapper;
import com.excilys.formation.computerdatabase.service.impl.CompanyServiceImpl;
import com.excilys.formation.computerdatabase.service.impl.ComputerServiceImpl;

@WebServlet(name = "CreateComputer", urlPatterns = "/create")
public class CreateComputer extends HttpServlet {
    public static final String VUE = "/WEB-INF/views/addComputer.jsp";
    @Autowired
    ComputerServiceImpl service;
    @Autowired
    CompanyServiceImpl companyService;
    
    @Override
    public void init(ServletConfig config) throws ServletException {
	super.init();
	SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	CompaniesInHashMap(request);
	request.getServletContext().getRequestDispatcher(VUE).forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	Computer c1 = ComputerMapper.toComputer(request);
	String result = null;
	if (c1 != null) {
	    if (service.create(c1) == null) {
		result = "Computer not created";
	    } else {
		result = "Computer added";
	    }
	    ;
	}
	CompaniesInHashMap(request);
//	request.setAttribute("error", cM.getErreur());
	request.setAttribute("result", result);
	request.getServletContext().getRequestDispatcher(VUE).forward(request, response);
    }

    /**
     * list the companies in a hasmap, and put it as attribute of the
     * request.
     * 
     * @param request
     */
    protected void CompaniesInHashMap(HttpServletRequest request) {
	Map<Long, String> map = companyService.getMap();
	request.setAttribute("map", map);
    }
}
