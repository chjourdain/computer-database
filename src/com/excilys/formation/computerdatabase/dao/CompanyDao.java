package com.excilys.formation.computerdatabase.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.excilys.formation.computerdatabase.modele.Company;

public class CompanyDao extends GenericDao<Company> {
	
	public CompanyDao(Connection con){
		super(con);
	}

	public  ArrayList<Company> list() {
		
		String query = "Select id, name from company";
		ResultSet result;
		ArrayList<Company> companies;

		try {
			Statement stm = super.connect.createStatement();
			result = stm.executeQuery(query);
			companies = new ArrayList<>();
			while (result.next()) {
				Company c1=new Company(result.getString("name"), result.getInt("id"));
				companies.add(c1);

			}

		} catch (SQLException e) {

			e.printStackTrace();
			return null;
		}

		return companies;
	}

	@Override
	public Company find(int id) {
		String query = "Select id, name from company where id="+id;
		ResultSet result;
		Company company=null;

		try {
			Statement stm = connect.createStatement();
			result = stm.executeQuery(query);
			
			while (result.next()) {
				company=new Company(result.getString("name"), result.getInt("id"));
			

			}

		} catch (SQLException e) {

			e.printStackTrace();
			return null;
		}

		return company;
		
		
	}

	

	
}
