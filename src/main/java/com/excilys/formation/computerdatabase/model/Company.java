package com.excilys.formation.computerdatabase.model;

public class Company {

	private final int id;
	private final String name;

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
	public int hashCode() {
		return (id * 11);
	}

	@Override
	public String toString() {
		return "Company [Id=" + id + ", Name=" + name + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj.getClass() != this.getClass()) {
			return false;
		}
		Company autre = (Company) obj;
		if (this.getId() == autre.getId()) {
			return true;
		}
		return false;

	}
}
