package com.excilys.computerdatabase.cli;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.excilys.computerdatabase.service.impl.CompanyServiceImpl;
import com.excilys.computerdatabase.service.impl.ComputerServiceImpl;

public class Launcher {

    public static void main(String[] args) {
	CmdLineInterface cli = null;
	try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml")) {
	    ComputerServiceImpl compuService = context.getBean(ComputerServiceImpl.class);
	    CompanyServiceImpl compaService = context.getBean(CompanyServiceImpl.class);
	    cli = new CmdLineInterface(compuService, compaService);
	}
	cli.startCmdLineInterface();
    }
}
