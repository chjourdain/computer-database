package com.excilys.formation.computerdatabase.modele;

public class Company {

	protected final int id;
	protected final String name;

	public Company(String pName, int pId) {
		this.name = pName;
		this.id = pId;

	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {

		return "Société : " + name + "  Enregistrée sous l'ID : " + id;
	}

}
