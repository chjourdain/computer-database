package com.excilys.formation.computerdatabase.persist.dao.mapper;

import javax.servlet.http.HttpServletRequest;

import com.excilys.formation.computerdatabase.model.Pager;

public class PagerRequestMapper {

    public static Pager get(HttpServletRequest request) {
	Pager pager;
	int page = 1;
	String search = null;

	if (request.getParameter("search") != null && !request.getParameter("search").isEmpty()) {
	    search = request.getParameter("search");
	}
	pager = new Pager(10, page, search);
	if (request.getParameter("Page") != null && !request.getParameter("Page").isEmpty()) {
	    page = Integer.parseInt(request.getParameter("Page"));
	    pager.setCurrentPage(page);
	}
	if (request.getParameter("Nb") != null && !request.getParameter("Nb").isEmpty()) {
	    page = Integer.parseInt(request.getParameter("Nb"));
	    pager.setNbByPage(page);
	}

	if (request.getParameter("search") != null && !request.getParameter("search").isEmpty()) {
	    pager.setSearch(request.getParameter("search"));
	}
	if (request.getParameter("order") != null && !request.getParameter("order").isEmpty()) {
	    pager.setSort(request.getParameter("order"));
	}
	return pager;
    }
}
