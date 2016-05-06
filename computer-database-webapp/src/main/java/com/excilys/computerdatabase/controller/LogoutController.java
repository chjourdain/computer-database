package com.excilys.computerdatabase.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/logout")
public class LogoutController {

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView get() {
        SecurityContextHolder.getContext().setAuthentication(null);
        ModelAndView model = new ModelAndView("redirect:/login");
        model.addObject("logout", "out");
        return model;
    }
}
