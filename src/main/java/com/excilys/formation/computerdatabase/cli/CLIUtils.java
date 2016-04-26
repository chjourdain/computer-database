package com.excilys.formation.computerdatabase.cli;

import java.util.Scanner;

import com.excilys.formation.computerdatabase.model.Company;
import com.excilys.formation.computerdatabase.model.Computer;
import com.excilys.formation.computerdatabase.model.dto.ComputerDTO;
import com.excilys.formation.computerdatabase.model.mapper.ComputerMapper;
import com.excilys.formation.computerdatabase.service.impl.CompanyServiceImpl;
import com.excilys.formation.computerdatabase.service.impl.ComputerServiceImpl;

public class CLIUtils {
    private Scanner sc;

    public CLIUtils(Scanner sc) {
	this.sc = sc;
    }

    protected void displayMenu() {
	System.out.println("---------------------------------------");
	System.out.println("               MENU                    ");
	System.out.println("---------------------------------------");
	System.out.println("1. List computers");
	System.out.println("2. Delete companie");
	System.out.println("3. Show computer details");
	System.out.println("4. Create computer");
	System.out.println("5. Update computer");
	System.out.println("6. Delete computer");
	System.out.println("7. List companies");
	System.out.println("8. Exit");
    }

    protected void listComputer(ComputerServiceImpl computerService) {
	//Pager<Computer> pager = new Pager<Computer>(10, 1);
//	System.out.println(pager.toString());
//	computerService.fillPage(pager);
	//int choice = printPage(pager);
//	while (choice == 1 || choice == 2) {
//	    changePage(pager, choice, computerService);
//	    choice = printPage(pager);
//	}
    }

    protected <Computer> int printPage(){//Pager<Computer> pager) {
//	for (Computer element : pager.getList()) {
	//    System.out.println(element.toString());
//	}
	System.out.println("\n1. Next Page");
	System.out.println("2. Previous Page");
	System.out.println("3. Return menu");
	return askInt("");
    }

    protected void changePage(){//Pager pager, int choice, ComputerServiceImpl computerService) {
//	if (choice == 1) {
//	    pager.setCurrentPage(pager.getCurrentPage() + 1);
//	}
//	if (choice == 2) {
//	    pager.setCurrentPage(pager.getCurrentPage() - 1);
//	}
	//computerService.fillPage(pager);
    }

    protected void deleteCompanie(CompanyServiceImpl service) {
	int choice = askInt("Company Id to delete \n 0 to cancel");
	if (choice > 0) {
	    Company c = new Company();
	    c.setId(choice);
	    service.delete(c);
	}
    }

    protected void showComputer(ComputerServiceImpl computerService) {
	int choice = askInt("Computer Id to show");
	System.out.println(computerService.find(choice).toString());
    }

    protected void createComputer(ComputerServiceImpl computerService) {
	ComputerDTO dto = new ComputerDTO();
	dto.setName(askString("Name?"));
	dto.setIntroduced(askNullOrString("introduced, format YYYY-MM-DD?"));
	dto.setDiscontinued(askNullOrString("discontinued, format YYYY-MM-DD?"));
	dto.setCompanyId(Long.valueOf(askNullOrString("Company id ?")));
	computerService.create(ComputerMapper.toComputer(dto));
    }

    protected void updateComputer(ComputerServiceImpl computerService) {
	int id = askInt("Computer id to update");
	if (id > 0) {
	    ComputerDTO dto = ComputerMapper.toDTO(computerService.find(id));
	    System.out.println(dto.toString());
	    dto.setName(askString("Name?"));
	    dto.setIntroduced(askNullOrString("introduced, format YYYY-MM-DD?"));
	    dto.setDiscontinued(askNullOrString("discontinued, format YYYY-MM-DD?"));
	    dto.setCompanyId(Long.valueOf(askNullOrString("Company id ?")));
	    computerService.update(ComputerMapper.toComputer(dto));
	}
    }

    protected void deleteComputer(ComputerServiceImpl service) {
	int id = askInt("Computer id to delete");
	if (id > 0) {
	    Computer c = new Computer();
	    c.setId(id);
	    service.delete(c);
	}
    }

    protected void listCompanie() {

    }

    protected String askString(String msg) {
	System.out.println(msg);
	String buffer = null;
	while ("".equals(buffer) || buffer == null) {
	    buffer = sc.nextLine();
	}
	return buffer;
    }

    protected String askNullOrString(String msg) {
	System.out.println(msg);
	String buffer = sc.nextLine();
	if (buffer == null || buffer.isEmpty()) {
	    return null;
	}
	return buffer;
    }

    protected int askInt(String msg) {
	System.out.println(msg);
	int buffer = sc.nextInt();
	return buffer;
    }

}
