package com.excilys.formation.computerdatabase.servlets;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.excilys.formation.computerdatabase.model.Pager;
import com.excilys.formation.computerdatabase.service.ComputerService;
import com.excilys.formation.computerdatabase.service.impl.ComputerServiceImpl;

public class Delete extends HttpServlet {
    public static final String VUE = "/WEB-INF/views/dashboard.jsp";

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	List<Integer> deleteList =  mapDelete(request);
	ComputerService service = ComputerServiceImpl.getComputerService();
	for (Integer i : deleteList){
	    service.delete(service.find(i));
	}
	Pager pager = DashBoard.createComputerPager(request);
	request.setAttribute("pager", pager);
	this.getServletContext().getRequestDispatcher(VUE).forward(request, response);

    }   
    
    private static List<Integer> mapDelete(HttpServletRequest request) {
	String selection = request.getParameter("selection");
	LinkedList<Integer> deleteList = new LinkedList<>();
	for (String s : selection.split(",")) {
	    deleteList.add(Integer.valueOf(s));
	}
	return deleteList;
    }
}
