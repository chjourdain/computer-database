package com.excilys.formation.computerdatabase.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import org.apache.log4j.Logger;

import com.excilys.formation.computerdatabase.modele.Company;


public class CompanyDao extends GenericDao<Company> {
	
	Logger daoLogger=Logger.getLogger(this.getClass());
	
	public CompanyDao(Connection con){	
		super(con);
		daoLogger.info("Initialisation du DAO Company");
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
			
			if (result.next()) {
				company=new Company(result.getString("name"), result.getInt("id"));
			

			}

		} catch (SQLException e) {

			e.printStackTrace();
			return null;
		}

		return company;
		
		
	}
	public int getNumberOfElement(){
		String query="SELECT count(*) from company";
		ResultSet result;
		try {
			Statement stm = connect.createStatement();
			result = stm.executeQuery(query);

			if (result.first()) {
				return result.getInt(1);
			} else {
				return 0;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;

		}
	}


	public ArrayList<Company> list(int page) {
		int first=page*ROW_BY_PAGE;
		String query = "Select * from company"
				+ " LIMIT "+first+","+ROW_BY_PAGE;
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

	public Company findByName(String id) {
		String query = "Select id, name from company where name="+id;
		ResultSet result;
		Company company=null;

		try {
			Statement stm = connect.createStatement();
			result = stm.executeQuery(query);
			
			if (result.next()) {
				company=new Company(result.getString("name"), result.getInt("id"));
			

			}

		} catch (SQLException e) {

			daoLogger.error(e.getMessage());
			return null;
		}

		return company;
		
		
	}
}
