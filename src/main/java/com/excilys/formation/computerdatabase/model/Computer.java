package com.excilys.formation.computerdatabase.model;

import java.time.LocalDate;

public class Computer {

	private int id;
	private String name;
	private LocalDate introduced;
	private Company company;
	private LocalDate discontinued;

	public void setName(String name) {
		this.name = name;
	}

	public Computer(String pName) {
		this.name = pName;
	}

	public Computer(int pId, String pName) {
		this.id = pId;
		this.name = pName;
	}

	public Computer(String pName, LocalDate pIntroduced, LocalDate pDiscontinued, Company pcompany) {
		this.name = pName;
		this.introduced = pIntroduced;
		this.discontinued = pDiscontinued;
		this.company = pcompany;
	}

	public Computer(int pId, String pName, LocalDate pIntroduced, LocalDate pDiscontinued, Company pcompany) {
		this.id = pId;
		this.name = pName;
		this.introduced = pIntroduced;
		this.discontinued = pDiscontinued;
		this.company = pcompany;
	}

	public LocalDate getIntroduced() {
		return introduced;
	}

	public void setIntroduced(LocalDate introduced) {
		this.introduced = introduced;
	}

	public LocalDate getDiscontinued() {
		return discontinued;
	}

	public void setDiscontinued(LocalDate discontinued) {
		this.discontinued = discontinued;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	@Override
	public int hashCode() {
		return (id*11);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (obj.getClass() != this.getClass()) {
			return false;
		}
		Computer autre = (Computer) obj;
		if (this.getId() == autre.getId()) {
			return true;
		}
		return false;
	}

	@Override
	public String toString() {
		return "Computer [mId=" + id + ", mName=" + name + ", introduced=" + introduced + ", discontinued="
				+ discontinued + ", company=" + company + "]";
	}
}
