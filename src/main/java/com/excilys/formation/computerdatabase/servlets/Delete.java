package com.excilys.formation.computerdatabase.servlets;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

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
import com.excilys.formation.computerdatabase.service.ComputerService;
import com.excilys.formation.computerdatabase.service.impl.ComputerServiceImpl;

@WebServlet(name = "DeleteComputer", urlPatterns = "/delete")
public class Delete extends HttpServlet {
    public static final String VUE = "/WEB-INF/views/dashboard.jsp";
    @Autowired
    ComputerServiceImpl service;
    
    @Override
    public void init(ServletConfig config) throws ServletException {
	super.init();
	SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	List<Integer> deleteList =  mapDelete(request);
	for (Integer i : deleteList){
	    service.delete(service.find(i));
	}
	// redirect to dashboard, fill the list of computer with a pager define by the others parameters (page, nbByPage, search, order by)
	Pager pager = PagerRequestMapper.get(request);
	service.fillPage(pager);
	request.setAttribute("pager", pager);
	request.getServletContext().getRequestDispatcher(VUE).forward(request, response);

    }   
    /**
     * Method to transform the Parameter String selection (format : "1,2,4,5,7") into an array.
     * @param request of the delete method.
     */
    private static List<Integer> mapDelete(HttpServletRequest request) {
	String selection = request.getParameter("selection");
	LinkedList<Integer> deleteList = new LinkedList<>();
	for (String s : selection.split(",")) {
	    deleteList.add(Integer.valueOf(s));
	}
	return deleteList;
    }
}
