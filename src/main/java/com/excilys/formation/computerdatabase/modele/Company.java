package com.excilys.formation.computerdatabase.modele;

public class Company {

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this==obj) {return true;}
		if (obj.getClass() !=this.getClass()){return false;}
		Company autre=(Company) obj;
		if (this.getId()==autre.getId()){return true;}
		return false;
		
	}

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
