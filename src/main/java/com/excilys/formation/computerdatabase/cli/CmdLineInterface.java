package com.excilys.formation.computerdatabase.cli;

import java.io.IOException;
import java.util.Scanner;

import com.excilys.formation.computerdatabase.service.impl.CompanyServiceImpl;
import com.excilys.formation.computerdatabase.service.impl.ComputerServiceImpl;

public class CmdLineInterface {

    private ComputerServiceImpl computerService;
    private CompanyServiceImpl service;

    private CLIUtils utils;
    private static final Scanner SC;

    static {
	SC = new Scanner(System.in);
    }

    public CmdLineInterface(ComputerServiceImpl cService, CompanyServiceImpl service) {
	computerService = cService;
	this.service = service;
	utils = new CLIUtils(SC);
    }

    public void startCmdLineInterface() {

		boolean doContinue = true;

		try {
			while (doContinue) {
				utils.displayMenu();

				String cmd = SC.next();

				switch (cmd) {
				case "listcomputer":
				case "1":
					utils.listComputer(computerService);
					;
					break;

				case "deletecompanie":
				case "2":
					utils.deleteCompanie(service);
					;
					break;

				case "show":
				case "3":
					utils.showComputer(computerService);
					break;

				case "create":
				case "4":
					utils.createComputer(computerService);
					break;

				case "update":
				case "5":
					utils.updateComputer(computerService);
					break;
				
				case "deleteComputer":
				case "6":
					utils.deleteComputer(computerService);
					break;
					
				case "listCompany":
				case "7":
					utils.listCompanie();
					break;
	
				case "exit":
				case "8":
					doContinue = false;
					System.out.println("Operation terminated.");
					break;

				default:
					break;
				}

				System.out.println("\nPress 'enter' to continue.");
				System.in.read();
			}
		} catch (IOException ioe) {
		} finally {
			SC.close();
		}
	}
}