package com.excilys.formation.computerdatabase.ui;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.excilys.formation.computerdatabase.model.Company;
import com.excilys.formation.computerdatabase.model.Computer;
import com.excilys.formation.computerdatabase.model.Pager;
import com.excilys.formation.computerdatabase.service.GenericService;
import com.excilys.formation.computerdatabase.service.impl.CompanyServiceImpl;
import com.excilys.formation.computerdatabase.service.impl.ComputerServiceImpl;

public class CommandLineInterface {

	private final static Scanner SC = new Scanner(System.in);

	public static void main(String[] args) {
		int i = 0;
		while (i != 7) {
			showMenu();
			try {
				i = SC.nextInt();
			} catch (InputMismatchException e) {
				System.out.println("Entrée non va1lide");
				SC.nextLine();
				i = -1;
			}

			switch (i) {
			case 1:
				listComputer(null);
				break;
			case 2:
				listCompanies(null);
				break;
			case 3:
				showComputer();
				break;
			case 4:
				createComputer();
				break;
			case 5:
				updateComputer();
				break;
			case 6:
				deleteComputer();
				break;
			case 7:
				exit();
				break;
			default:
			}
		}

	}

	public static void showMenu() {
		System.out.println("---------------------------------------");
		System.out.println("               MENU                    ");
		System.out.println("---------------------------------------");
		System.out.println("1. List computers");
		System.out.println("2. List companies");
		System.out.println("3. Show computer details");
		System.out.println("4. Create computer");
		System.out.println("5. Update computer");
		System.out.println("6. Delete computer");
		System.out.println("7. Exit");

	}

	public static void listComputer(Pager<Computer> pager) {
		if (pager == null) {
			GenericService<Computer> service = ComputerServiceImpl.getComputerService();
			pager = new Pager<Computer>(20,1, service);
		}
		pager.printListe();
		System.out.println("Page suivante : n | Page précédente : p Quitter : q ");
		Scanner scanner = new Scanner(System.in);
		String choix = null;

		choix = scanner.next().trim();
		if ("n".equals(choix)) {
			pager.next();
			listComputer(pager);
		} else if ("p".equals(choix)) {
			pager.prev();
			listComputer(pager);
		} else if ("q".equals(choix)) {
		} else {
			listComputer(pager);
		}
	}

	public static void listCompanies(Pager<Company> pager) {
		if (pager == null) {
			GenericService<Company> service = CompanyServiceImpl.getCompanyService();
		
			pager = new Pager<>(20, 1, service);
		}
		pager.printListe();
		System.out.println("Page suivante : n | Page précédente : p Quitter : q ");
		Scanner scanner = new Scanner(System.in);
		String choix = null;

		choix = scanner.next().trim();
		if ("n".equals(choix)) {
			pager.next();
			listCompanies(pager);
		} else if ("p".equals(choix)) {
			pager.prev();
			listCompanies(pager);
		} else if ("q".equals(choix)) {
		} else {
			listCompanies(pager);
		}
	}

	public static void showComputer() {
		System.out.println("---------------------------------------");
		System.out.println("            SHOW COMPUTER              ");
		System.out.println(
				"-------	private final static PrintStream System.out = System.out;--------------------------------");
		System.out.println("Veuillez saisir l'id de l'ordinateur à afficher");
		System.out.println("(-1 pour annuler)");
		int id = SC.nextInt();
		if (id != -1) {

			try {
				Computer c = ComputerServiceImpl.getComputerService().find(id);
				System.out.println(c.toString());
			} catch (NullPointerException e2) {
				System.out.println("Aucun ordinateur avec cet ID");
			}
		}
	}

	public static void createComputer() {
		Computer c;
		System.out.println("---------------------------------------");
		System.out.println("            CREATE COMPUTER            ");
		System.out.println("---------------------------------------");
		System.out.println("Veuillez Saisir le nom de L'ordianteur ");
		SC.nextLine();
		String name = SC.nextLine();
		System.out.println("Saisir les autres champs ? o/n");
		if (SC.next().charAt(0) == 'o') {
			SC.nextLine();

			System.out.println("Veuillez Saisir le construteur");
			String companyName = SC.nextLine();
			System.out.println("Veuillez Saisir la Date d'introduction, format (0000-00-00)");
			boolean b = true;
			LocalDate introduced = null;
			do {
				String sIntroduced = SC.nextLine();
				try {
					introduced = LocalDate.parse(sIntroduced);
					b = false;
				} catch (DateTimeParseException e) {
					System.out.println("FORMAT INCORRECT,reessayer (0000-00-00)");
				}

			} while (b);
			System.out.println("Veuillez Saisir la Date de discontinued, format (0000-00-00)");

			b = true;
			LocalDate discontinued = null;
			do {
				String sDiscontinued = SC.nextLine();
				try {
					discontinued = LocalDate.parse(sDiscontinued);
					b = false;
				} catch (DateTimeParseException e) {
					System.out.println("FORMAT INCORRECT,reessayer (0000-00-00)");
				}

			} while (b);
			Company company = (new CompanyServiceImpl()).findByName(companyName);
			c = new Computer(name, introduced, discontinued, company);

			ComputerServiceImpl.getComputerService().create(c);
		} else {
			c = new Computer(name);
			ComputerServiceImpl.getComputerService().create(c);
		}

	}

	public static void updateComputer() {
		Computer c;
		System.out.println("---------------------------------------");
		System.out.println("            UPDATE COMPUTER            ");
		System.out.println("---------------------------------------");
		System.out.println("Veuillez Saisir l'ID de l'ordinateur");
		System.out.println("-1 pour annuler");
		int id = SC.nextInt();
		SC.nextLine();
		if (id != -1) {
			try {
				c = ComputerServiceImpl.getComputerService().find(id);
				System.out.println(c.toString());
				System.out.println("Entrez dans l'ordre nom,introduced,discontinued,company_name ");
				System.out.println("vide pour inchangé");

				String name = SC.nextLine();
				if (name != "") {
					c.setName(name);
				}
				boolean b = true;
				do {
					String sIntroduced = SC.nextLine();
					try {
						c.setIntroduced(LocalDate.parse(sIntroduced));
						b = false;
					} catch (DateTimeParseException e) {
						System.out.println("FORMAT INCORRECT,reessayer (0000-00-00)");
					}

				} while (b);

				do {
					String sDiscontinued = SC.nextLine();
					if (sDiscontinued == "") {
						b = false;
					} else {
						try {
							c.setDiscontinued((LocalDate.parse(sDiscontinued)));
							b = false;
						} catch (DateTimeParseException e) {
							System.out.println("FORMAT INCORRECT,reessayer (0000-00-00)");
						}
					}
				} while (b);

				String companyName = SC.nextLine();
				if (companyName != "") {
					Company company = (new CompanyServiceImpl()).findByName(companyName);

					c.setCompany(company);
				}
				ComputerServiceImpl.getComputerService().update(c);
			} catch (NullPointerException e2) {
				System.out.print("Aucune modification faite");
			} // TODO Auto-generated method stub
		}

	}

	public static void deleteComputer() {
		Computer c;
		System.out.println("---------------------------------------");
		System.out.println("            DELETE COMPUTER            ");
		System.out.println("---------------------------------------");
		System.out.println("Veuillez Saisir l'ID de l'ordinateur");
		System.out.println("-1 pour annuler");
		int id = SC.nextInt();
		if (id != -1) {

			c = ComputerServiceImpl.getComputerService().find(id);
			ComputerServiceImpl.getComputerService().delete(c);
		}
	}

	public static void exit() {
		SC.close();
	}

//	public static int pagination(Integer page) {
//		System.out.print("(0) |< ");
//		System.out.print("(1) << ");
//		System.out.print("(2) >> ");
//		System.out.print("(3) |> ");
//		System.out.print("(-1) X ");
//		int b = 0;
//		try {
//			b = SC.nextInt();
//		} catch (InputMismatchException e) {
//			b = -1;
//		}
//		switch (b) {
//		case 0:
//			page = 0;
//			break;
//		case 1:
//			page -= 1;
//			break;
//		case 2:
//			page += 1;
//			System.out.println(page);
//			break;
//		case 3:
//			page = ComputerServiceImpl.getComputerService().count() / 20;
//			break;
//		default:
//			page = -1;
//
//		}
//		return page;
//	}
}