package com.excilys.formation.computerdatabase.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.excilys.formation.computerdatabase.modele.Computer;

public class ComputerDao extends GenericDao<Computer> {

	private Logger daoLogger = Logger.getLogger(this.getClass());

	public ComputerDao(Connection conn) {
		super(conn);
		daoLogger.info("initialisation de computer dao");
	}

	@Override
	public ArrayList<Computer> list() {
		String query = "Select computer.id, computer.name,introduced,discontinued,company_id, company.name from computer left join company on computer.company_id=company.id";
		ResultSet result;
		ArrayList<Computer> computers;

		try {
			Statement stm = super.connect.createStatement();
			result = stm.executeQuery(query);
			computers = new ArrayList<>();
			while (result.next()) {
				Computer c1 = mapComputer(result);
				computers.add(c1);
			}

		} catch (SQLException e) {

			e.printStackTrace();
			return null;
		}

		return computers;
	}

	public ArrayList<Computer> list(int page) {
		int first = page * ROW_BY_PAGE;
		String query = "Select computer.id, computer.name,introduced,discontinued,company_id, company.name from computer"
				+ " left join company on computer.company_id=company.id" + " LIMIT " + first + "," + ROW_BY_PAGE;

		ResultSet result;
		ArrayList<Computer> computers;

		try {
			Statement stm = super.connect.createStatement();
			result = stm.executeQuery(query);
			computers = new ArrayList<>();
			while (result.next()) {
				Computer c1 = mapComputer(result);
				computers.add(c1);
			}

		} catch (SQLException e) {

			e.printStackTrace();
			return null;
		}

		return computers;
	}

	@Override
	public boolean create(Computer obj) {
		String companyName = "null";
		LocalDate pIntro = obj.getIntroduced();
		LocalDate pDis = obj.getDiscontinued();
		String query = "";

		if (obj.getCompanieName() != null) {
			ResultSet result;

			try {
				Statement stm = super.connect.createStatement();
				result = stm.executeQuery("select id from company where name='" + obj.getCompanieName() + "'");
				if (result.first()) {
					companyName = result.getString("id");
					query = "INSERT into computer(name,company_id,introduced,discontinued) values ('" + obj.getName()
							+ "','" + companyName + "','" + pIntro + "','" + pDis + "'	)";
				} else
					query = "INSERT into computer(name,introduced,discontinued) values ('" + obj.getName() + "','"
							+ pIntro + "','" + pDis + "'	)";
			} catch (SQLException e) {
				System.out.println("Company inconnu de la bdd");
				daoLogger.error(e.getMessage());
			}

		} else
			query = "INSERT into computer(name,introduced,discontinued) values ('" + obj.getName() + "'," + pIntro + ","
					+ pDis + "	)";

		try {
			Statement stm = super.connect.createStatement();
			stm.executeUpdate(query);
			ResultSet rs;
			Statement stat = connect.createStatement();
			rs = stat.executeQuery("SELECT id from computer where name='" + obj.getName() + "'");
			if (rs.last())
				obj.setId(rs.getInt("id"));
			daoLogger.info("creation de " + obj);
			return true;
		} catch (SQLException e) {
			daoLogger.error(e.getMessage());
			return false;
		}

	}

	@Override
	public boolean delete(Computer obj) {

		try {
			Statement stat = connect.createStatement();
			stat.executeUpdate("DELETE FROM computer where id=" + obj.getId());
			return true;
		} catch (SQLException e) {
			daoLogger.error(e.getMessage());
			return false;
		} catch (NullPointerException e2) {
			System.out.println("Aucun ordinateur a supprimer ayant cet ID");
			return false;
		}

	}

	@Override
	public boolean update(Computer obj) {
		/**
		 * Creation de l'obj2 pour le logger
		 */
		Computer obj2=obj;
		LocalDate intro2 = obj.getIntroduced();
		String intro = (intro2 == null) ? null : "'" + intro2 + "'";
		LocalDate disc2 = obj.getDiscontinued();
		String disc = (disc2 == null) ? null : "'" + disc2 + "'";
		String companyName;
		String query = "";
		if (obj.getCompanieName() != null) {
			ResultSet result;

			try {
				Statement stm = super.connect.createStatement();
				result = stm.executeQuery("select id from company where name='" + obj.getCompanieName() + "'");
				if (result.first()) {
					companyName = result.getString("id");
					query = "UPDATE computer SET name='" + obj.getName() + "', company_id=" + companyName
							+ ",introduced=" + intro + ",discontinued=" + disc + " where	id=" + obj.getId();
				} else
					query = "UPDATE computer SET name='" + obj.getName() + "',introduced=" + intro + ",discontinued="
							+ disc + " where	id=" + obj.getId();
			} catch (SQLException e) {
				System.out.println("Exception sql lors de l'update");
				e.printStackTrace();

			}

		} else {
			query = "UPDATE computer SET name='" + obj.getName() + "',introduced=" + intro + ",discontinued=" + disc
					+ " where	id=" + obj.getId();
		}

		try {
			Statement stt = connect.createStatement();

			stt.executeUpdate(query);
		} catch (SQLException e) {
			daoLogger.error(e.getMessage());
			return false;
		}
		daoLogger.info("update de "+obj2+"  pour : "+obj);
		return true;
	}

	public Computer find(int id) throws NullPointerException {
		String query = "Select computer.id,computer.name,introduced,discontinued,company.name from computer left join company on computer.company_id=company.id where computer.id="
				+ id;
		ResultSet result;
		Computer computer = null;

		try {
			Statement stm = super.connect.createStatement();
			result = stm.executeQuery(query);

			if (result.first()) {
				computer = mapComputer(result);
			} else {
				System.out.println("Aucun ordinateur avec cet ID");
			}
		} catch (SQLException e) {
			System.out.println("Aucun ordinateur avec cet ID");
			throw new NullPointerException();

		}

		return computer;

	}

	public int getNumberOfElement() {
		String query = "SELECT count(*) from computer";
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
			daoLogger.error(e.getMessage());
			return 0;

		}
	}

	private Computer mapComputer(ResultSet rs) throws SQLException {
		Computer c1 = new Computer(rs.getInt("computer.id"), rs.getString("computer.name"));

		if (rs.getTimestamp(3) != null) {
			c1.setIntroduced(rs.getDate(3).toLocalDate());
		}
		if (rs.getTimestamp(4) != null) {
			c1.setDiscontinued(rs.getDate(4).toLocalDate());
		}
		if (rs.getString("company.name") != null) {

			{
				c1.setCompanieName(rs.getString("company.name"));
			}
		}
		return c1;
	}

}
