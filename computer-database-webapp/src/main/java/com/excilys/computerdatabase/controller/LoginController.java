package com.excilys.computerdatabase.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.excilys.computerdatabase.persist.dao.UserDao;


@Controller
@RequestMapping("/login")
public class LoginController {
    
    @Autowired
    private UserDao userDao;
    
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView get() {
	System.out.println(userDao.findOne("admin"));
	
	return new ModelAndView("login");

    }
}
