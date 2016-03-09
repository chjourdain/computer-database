package com.excilys.formation.computerdatabase.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import com.excilys.formation.computerdatabase.connection.ConnectionManager;
import com.excilys.formation.computerdatabase.modele.Computer;

public class ComputerDao extends GenericDao<Computer> {

	public ComputerDao(Connection conn) {
		super(conn);
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

	@Override
	public boolean create(Computer obj) {
		String companyName = "null";
		String pIntro = (obj.getIntroduced() == null) ? "0000-00-00" : obj.getIntroduced();
		String pDis = (obj.getDiscontinued() == null) ? "0000-00-00" : obj.getDiscontinued();
		String query = "";

		if (obj.getCompanieName() != null) {
			ResultSet result;

			try {
				Statement stm = super.connect.createStatement();
				result = stm.executeQuery("select id from company where name='" + obj.getCompanieName()+"'");
				if (result.first()) {
					companyName = result.getString("id");
					query = "INSERT into computer(name,company_id,introduced,discontinued) values ('" + obj.getName()
							+ "','" + companyName + "','" + pIntro + "','" + pDis + "'	)";
				} else
					query = "INSERT into computer(name,introduced,discontinued) values ('" + obj.getName() + "','"
							+ pIntro + "','" + pDis + "'	)";
			} catch (SQLException e) {
				System.out.println("Company inconnu de la bdd");
				e.printStackTrace();
			}

		} else
			query = "INSERT into computer(name,introduced,discontinued) values ('" + obj.getName() + "','" + pIntro
					+ "','" + pDis + "'	)";

		try {
			Statement stm = super.connect.createStatement();
			stm.executeUpdate(query);
			ResultSet rs;
			Statement stat = connect.createStatement();
			rs = stat.executeQuery("SELECT id from computer where name='" + obj.getName() + "'");
			if (rs.last())
				obj.setId(rs.getInt("id"));

			return true;
		} catch (SQLException e) {
			System.out.println("Aucun ordinateur avec cet ID");
			e.printStackTrace();
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

			e.printStackTrace();
			return false;
		}

	}

	@Override
	public boolean update(Computer obj) {
		String intro= (obj.getIntroduced()==null)? "0000-00-00" : obj.getIntroduced();
		String disc= (obj.getDiscontinued()==null)? "0000-00-00" : obj.getDiscontinued();
		String companyName;
		String query;
		if (obj.getCompanieName() != null) {
			ResultSet result;

			try {
				Statement stm = super.connect.createStatement();
				result = stm.executeQuery("select id from company where name='" + obj.getCompanieName()+"'");
				if (result.first()) {
					companyName = result.getString("id");
					query = "UPDATE computer SET name='" + obj.getName()
							+ "', company_id=" + companyName + ",introduced='" + intro + "',discontinued='" + disc + "' where	id="+obj.getId();
				} else
					query = "UPDATE computer SET name='" + obj.getName()
					+ "',introduced='" + intro + "',discontinued='" + disc + "' where	id="+obj.getId();
			} catch (SQLException e) {
				System.out.println("Exception sql lors de l'update");
				e.printStackTrace();
			}

		} else
			query = "UPDATE computer SET name='" + obj.getName()
			+ "',introduced='" + intro + "',discontinued='" + disc + "' where	id="+obj.getId();
		
		
		
		return super.update(obj);
	}

	@Override
	public Computer find(int id) {
		String query = "Select computer.id,computer.name,introduced,discontinued,company.name from computer left join company on computer.company_id=company.id where computer.id="
				+ id;
		ResultSet result;
		Computer computer;

		try {
			Statement stm = super.connect.createStatement();
			result = stm.executeQuery(query);

			result.first();
			computer = mapComputer(result);

		} catch (SQLException e) {
			System.out.println("Aucun ordinateur avec cet ID");
			e.printStackTrace();
			return null;
		}

		return computer;

	}

	private Computer mapComputer(ResultSet rs) throws SQLException {
		Computer c1 = new Computer(rs.getInt("computer.id"), rs.getString("computer.name"));

		if (rs.getTimestamp(3) != null) {
			c1.setIntroduced(rs.getTimestamp(3).toString());
		}
		if (rs.getTimestamp(4) != null) {
			c1.setDiscontinued(rs.getTimestamp(4).toString());
		}
		if (rs.getString("company.name") != null) {

			{
				c1.setCompanieName(rs.getString("company.name"));
			}
		}
		return c1;
	}

}
