package com.excilys.computerdatabase.cli;

import java.io.IOException;
import java.util.Scanner;

public class CmdLineInterface {

    private static final Scanner SC;

    static {
        SC = new Scanner(System.in);
    }

    private CLIUtils utils = new CLIUtils(SC);

    public void startCmdLineInterface() {

        boolean doContinue = true;

        try {
            while (doContinue) {
                utils.displayMenu();

                String cmd = SC.next();

                switch (cmd) {
                case "listcomputer":
                case "1":
                    utils.listComputer();
                    ;
                    break;

                case "deletecompanie":
                case "2":
                    utils.deleteCompany();
                    break;

                case "show":
                case "3":
                    utils.showComputer();
                    break;

                case "create":
                case "4":
                    utils.createComputer();
                    break;

                case "update":
                case "5":
                    utils.updateComputer();
                    break;

                case "deleteComputer":
                case "6":
                    utils.deleteComputer();
                    break;

                case "listCompany":
                case "7":
                    utils.listCompanies();
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