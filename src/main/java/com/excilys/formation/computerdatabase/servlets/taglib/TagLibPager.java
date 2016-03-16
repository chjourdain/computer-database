package com.excilys.formation.computerdatabase.servlets.taglib;

public class TagLibPager {

	public static String printPageMenu() {
		String s = "";
		int page = 3;
		int nbrpage = 50;
		if (page < 3) {
			int i = 1;
			while (i <= 5 && i <= nbrpage) {
				s += "<li><a href=\"dashboard?Page=" + i + "\">" + i + "</a></li>";
				i++;
			}
		} else {
			int i = page - 2;
			while (i <= (page + 2) && i <= nbrpage) {
				s += "<li><a href=\"dashboard?Page=" + i + "\">" + i + "</a></li>";
				i++;
			}

		}
		return s;
	}
}