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
import com.excilys.formation.computerdatabase.model.dto.ComputerDTO;
import com.excilys.formation.computerdatabase.persist.dao.mapper.ComputerMapper;
import com.excilys.formation.computerdatabase.service.impl.CompanyServiceImpl;
import com.excilys.formation.computerdatabase.service.impl.ComputerServiceImpl;

@WebServlet(name = "EditComputer", urlPatterns = "/edit")
public class EditComputer extends HttpServlet {
    @Autowired
    ComputerServiceImpl service2;
    
    @Autowired
    CompanyServiceImpl companyService;
    private static final long serialVersionUID = 1L;
    private static final String VUE = "/WEB-INF/views/dashboard.jsp";
    private static final String VUE_EDIT = "/WEB-INF/views/editComputer.jsp";
    
    @Override
    public void init(ServletConfig config) throws ServletException {
	super.init();
	SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	// get the computer id, send back the user to dashboard.jsp if not
	// valid.
	String id = request.getParameter("id");
	if (id == null || id.equals("0") || id.isEmpty()) {
	    request.getServletContext().getRequestDispatcher(VUE).forward(request, response);
	    return;
	}
	// get the computer to send its data to the jsp.
	System.out.println(id);
	System.out.println(    service2.toString());
	System.out.println(service2.find(Long.valueOf(id)));
	ComputerDTO computer = ComputerMapper.toDTO(service2.find(Long.valueOf(id)));
	request.setAttribute("computer", computer);
	// pass the list of companies in a hashmap as attribute.
	CompaniesInHashMap(request);
	request.getServletContext().getRequestDispatcher(VUE_EDIT).forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	Computer c1 = ComputerMapper.toComputer(request);
	if ((c1 == null )) {
	    // request invalid.
	    long i = Long.valueOf(request.getParameter("id").toString());
	    request.setAttribute("computer", ComputerMapper.toDTO(service2.find(Long.valueOf(i))));
	    request.setAttribute("result", "failure in updating Computer");
	//    request.setAttribute("error", cM.getErreur());
	    request.getServletContext().getRequestDispatcher(VUE_EDIT).forward(request, response);
	    return;
	}
	c1 = service2.update(c1);
	response.sendRedirect("dashboard");
    }

    protected void CompaniesInHashMap(HttpServletRequest request) {
	Map<Long, String> map = companyService.getMap();
	request.setAttribute("map", map);
    }
}