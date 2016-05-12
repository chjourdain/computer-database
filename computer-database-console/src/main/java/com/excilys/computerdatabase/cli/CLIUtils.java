package com.excilys.computerdatabase.cli;

import java.util.List;
import java.util.Scanner;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.glassfish.jersey.jackson.JacksonFeature;
import com.excilys.computerdatabase.dto.ComputerDTO;
import com.excilys.computerdatabase.model.Company;

public class CLIUtils {
    private Scanner sc;

    final static String URL = "http://localhost:8080/computer-database-rest/rest/computer/";
    final static String URL_COMPANY = "http://localhost:8080/computer-database-rest/rest/company/";
    Client client = ClientBuilder.newBuilder().register(new JacksonFeature()).build();

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

    protected void listComputer() {

        int page = 0;
        int number = 10;
        List<ComputerDTO> list = getPage(page, number);
        int choice = printPage(list);
        while (choice == 1 || choice == 2) {
            page = changePage(choice, page);
            list = getPage(page, number);
            choice = printPage(list);
        }
    }

    protected List<ComputerDTO> getPage(int page, int number) {
        WebTarget webTarget = client.target(URL + "/" + page + "/" + number);
        Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
        Response response = invocationBuilder.get();
        if (response.getStatus() == 200) {
            return response.readEntity(new GenericType<List<ComputerDTO>>() {});

        } else {
            System.out.println("Error with rest api");
            return null;
        }
    }

    protected List<Company> getPageCompany(int page, int number) {
        WebTarget webTarget = client.target(URL_COMPANY + "/" + page + "/" + number);
        Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
        Response response = invocationBuilder.get();
        if (response.getStatus() == 200) {
            return response.readEntity(new GenericType<List<Company>>() {});

        } else {
            System.out.println("Error with rest api");
            return null;
        }
    }

    protected int printPage(List<?> list) {
        for (Object dto : list) {
            System.out.println(dto);
        }
        System.out.println("\n1. Next Page");
        System.out.println("2. Previous Page");
        System.out.println("3. Return menu");
        return askInt("");
    }

    protected int changePage(int choice, int page) {
        if (choice == 1) {
            page++;
        } else if (choice == 2 && choice != 0) {
            page--;
        }
        return page;
    }

    protected ComputerDTO showComputer() {
        int choice = askInt("Computer Id to select");
        Response response = client.target(URL + "/" + choice).request(MediaType.APPLICATION_JSON).get();
        if (response.getStatus() == 200) {
            ComputerDTO dto = response.readEntity(ComputerDTO.class);
            System.out.println(dto.toString());
            return dto;
        } else {
            System.out.println("error");
            return null;
        }
    }

    protected ComputerDTO showComputer(int choice) {
        Response response = client.target(URL + choice).request(MediaType.APPLICATION_JSON).get();
        if (response.getStatus() == 200) {
            ComputerDTO dto = response.readEntity(ComputerDTO.class);
            System.out.println(dto.toString());
            return dto;
        } else {
            System.out.println("error");
            return null;
        }
    }

    protected void createComputer() {
        ComputerDTO dto = new ComputerDTO();
        dto.setName(askString("Name?"));
        dto.setIntroduced(askNullOrString("introduced, format YYYY-MM-DD?"));
        dto.setDiscontinued(askNullOrString("discontinued, format YYYY-MM-DD?"));
        dto.setCompanyId(Long.valueOf(askNullOrString("Company id ?")));
        WebTarget webTarget = client.target(URL);
        Response response = webTarget.request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(dto, MediaType.APPLICATION_JSON));
        if (response.getStatus() == 200) {
            System.out.println("Added!");
        } else {
            System.out.println("error");
        }
    }

    protected void updateComputer() {

        ComputerDTO dto = showComputer();
        if (dto != null) {
            System.out.println(dto.toString());
            dto.setName(askString("Name?"));
            dto.setIntroduced(askNullOrString("introduced, format YYYY-MM-DD?"));
            dto.setDiscontinued(askNullOrString("discontinued, format YYYY-MM-DD?"));
            dto.setCompanyId(Long.valueOf(askNullOrString("Company id ?")));
            WebTarget webTarget = client.target(URL);
            Response response = webTarget.request(MediaType.APPLICATION_JSON)
                    .put(Entity.entity(dto, MediaType.APPLICATION_JSON));
            if (response.getStatus() == 200) {
                System.out.println("Updated!");
            } else {
                System.out.println("error");
            }
        }
    }

    protected void deleteComputer() {
        System.out.println("delete Computer");
        int choice = askInt("select the comuter to delete");
        WebTarget webTarget = client.target(URL + choice);
        Response response = webTarget.request().delete();
        if (response.getStatus() == 200) {
            System.out.println("Deleted!");
        } else {
            System.out.println("error");
        }
    }

    protected void deleteCompany() {
        System.out.println("delete Company");
        int choice = askInt("select the company to delete");
        WebTarget webTarget = client.target(URL_COMPANY + choice);
        Response response = webTarget.request().delete();
        System.out.println(response.getStatus());
        if (response.getStatus() == 200) {
            System.out.println("Deleted!");
        } else {
            System.out.println("error");
        }
    }

    protected void listCompanies() {

        int page = 0;
        int number = 10;
        List<Company> list = getPageCompany(page, number);
        int choice = printPage(list);
        while (choice == 1 || choice == 2) {
            page = changePage(choice, page);
            list = getPageCompany(page, number);
            choice = printPage(list);
        }
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