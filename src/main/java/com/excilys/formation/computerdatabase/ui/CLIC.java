package com.excilys.formation.computerdatabase.ui;

import java.io.InputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.excilys.formation.computerdatabase.modele.Company;
import com.excilys.formation.computerdatabase.modele.Computer;
import com.excilys.formation.computerdatabase.service.CompanyService;
import com.excilys.formation.computerdatabase.service.ComputerService;

public class CLIC {

	private final static PrintStream _OUT = System.out;
	private final static InputStream _IN = System.in;
	private final static Scanner SC = new Scanner(_IN);

	public static void main(String[] args) {
		int i = 0;
		while (i != 7) {
			showMenu();
			try {
				i = SC.nextInt();
			} catch (InputMismatchException e) {
				_OUT.println("Entrée non valide");
				SC.nextLine();
				i = -1;
			}

			switch (i) {
			case 1:
				listComputer();
				break;
			case 2:
				listCompanies();
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
		_OUT.println("---------------------------------------");
		_OUT.println("               MENU                    ");
		_OUT.println("---------------------------------------");
		_OUT.println("1. List computers");
		_OUT.println("2. List companies");
		_OUT.println("3. Show computer details");
		_OUT.println("4. Create computer");
		_OUT.println("5. Update computer");
		_OUT.println("6. Delete computer");
		_OUT.println("7. Exit");

	}

	public static void listComputer() {
		Integer pageA = 0;

		while (pageA != -1) {
			_OUT.println("---------------------------------------");
			_OUT.println("AFFICHAGE ORDINATEURS        page : " + pageA);
			_OUT.println("---------------------------------------");

			List<Computer> cl = ComputerService.listComputer(pageA);
			for (Computer c : cl) {
				_OUT.println(c.toString());
			}
			pageA = pagination(pageA);

		}
	}

	public static void listCompanies() {
		Integer pageA = 0;
		
		while (pageA != -1) {
			_OUT.println("---------------------------------------");
			_OUT.println("AFFICHAGE ORDINATEURS        page : " + pageA);
			_OUT.println("---------------------------------------");

			List<Company> cl = CompanyService.list(pageA);
			for (Company c : cl) {
				_OUT.println(c.toString());
			}
			pageA = pagination(pageA);
		}
	}

	public static void showComputer() {
		_OUT.println("---------------------------------------");
		_OUT.println("            SHOW COMPUTER              ");
		_OUT.println("---------------------------------------");
		_OUT.println("Veuillez saisir l'id de l'ordinateur à afficher");
		_OUT.println("(-1 pour annuler)");
		int id = SC.nextInt();
		if (id != -1) {

			try {
				Computer c = ComputerService.find(id);
				System.out.println(c.toString());
			} catch (NullPointerException e2) {
				_OUT.println("Aucun ordinateur avec cet ID");
			}
		}
	}

	public static void createComputer() {
		Computer c;
		_OUT.println("---------------------------------------");
		_OUT.println("            CREATE COMPUTER            ");
		_OUT.println("---------------------------------------");
		_OUT.println("Veuillez Saisir le nom de L'ordianteur ");
		SC.nextLine();
		String name = SC.nextLine();
		_OUT.println("Saisir les autres champs ? o/n");
		if (SC.next().charAt(0) == 'o') {
			SC.nextLine();

			_OUT.println("Veuillez Saisir le construteur");
			String companyName = SC.nextLine();
			_OUT.println("Veuillez Saisir la Date d'introduction, format (0000-00-00)");
			boolean b = true;
			LocalDate introduced = null;
			do {
				String sIntroduced = SC.nextLine();
				try {
					introduced = LocalDate.parse(sIntroduced);
					b = false;
				} catch (DateTimeParseException e) {
					_OUT.println("FORMAT INCORRECT,reessayer (0000-00-00)");
				}

			} while (b);
			_OUT.println("Veuillez Saisir la Date de discontinued, format (0000-00-00)");

			b = true;
			LocalDate discontinued = null;
			do {
				String sDiscontinued = SC.nextLine();
				try {
					discontinued = LocalDate.parse(sDiscontinued);
					b = false;
				} catch (DateTimeParseException e) {
					_OUT.println("FORMAT INCORRECT,reessayer (0000-00-00)");
				}

			} while (b);

			c = new Computer(name, introduced, discontinued, companyName);

			ComputerService.create(c);
		} else {
			c = new Computer(name);
			ComputerService.create(c);
		}

	}

	public static void updateComputer() {
		Computer c;
		_OUT.println("---------------------------------------");
		_OUT.println("            UPDATE COMPUTER            ");
		_OUT.println("---------------------------------------");
		_OUT.println("Veuillez Saisir l'ID de l'ordinateur");
		_OUT.println("-1 pour annuler");
		int id = SC.nextInt();
		SC.nextLine();
		if (id != -1) {
			try {
				c = ComputerService.find(id);
				System.out.println(c.toString());
				_OUT.println("Entrez dans l'ordre nom,introduced,discontinued,company_name ");
				_OUT.println("vide pour inchangé");

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
						_OUT.println("FORMAT INCORRECT,reessayer (0000-00-00)");
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
							_OUT.println("FORMAT INCORRECT,reessayer (0000-00-00)");
						}
					}
				} while (b);

				String companyName = SC.nextLine();
				if (companyName != "") {
					c.setCompanieName(companyName);
				}
				ComputerService.update(c);
			} catch (NullPointerException e2) {
				_OUT.print("Aucune modification faite");
			} // TODO Auto-generated method stub
		}

	}

	public static void deleteComputer() {
		Computer c;
		_OUT.println("---------------------------------------");
		_OUT.println("            DELETE COMPUTER            ");
		_OUT.println("---------------------------------------");
		_OUT.println("Veuillez Saisir l'ID de l'ordinateur");
		_OUT.println("-1 pour annuler");
		int id = SC.nextInt();
		if (id != -1) {

			c = ComputerService.find(id);
			ComputerService.delete(c);
		}
	}

	public static void exit() {
		SC.close();
	}

	public static int pagination(Integer page) {
		_OUT.print("(0) |< ");
		_OUT.print("(1) << ");
		_OUT.print("(2) >> ");
		_OUT.print("(3) |> ");
		_OUT.print("(-1) X ");
		int b = 0;
		try {
			b = SC.nextInt();
		} catch (InputMismatchException e) {
			b = -1;
		}
		switch (b) {
		case 0:
			page = 0;
			break;
		case 1:
			page -= 1;
			break;
		case 2:
			page += 1;
			System.out.println(page);
			break;
		case 3:
			page = ComputerService.getNumberOfElement() / 20;
			break;
		default:
			page = -1;

		}
		return page;
	}

}