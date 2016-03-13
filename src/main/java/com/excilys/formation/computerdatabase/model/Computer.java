package com.excilys.formation.computerdatabase.model;

import java.time.LocalDate;

public class Computer {

	private long id;
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

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((company == null) ? 0 : company.hashCode());
		result = prime * result + ((discontinued == null) ? 0 : discontinued.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((introduced == null) ? 0 : introduced.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Computer other = (Computer) obj;
		if (company == null) {
			if (other.company != null)
				return false;
		} else if (!company.equals(other.company))
			return false;
		if (discontinued == null) {
			if (other.discontinued != null)
				return false;
		} else if (!discontinued.equals(other.discontinued))
			return false;
		if (id != other.id)
			return false;
		if (introduced == null) {
			if (other.introduced != null)
				return false;
		} else if (!introduced.equals(other.introduced))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Computer [id=" + id + ", name=" + name + ", introduced=" + introduced + ", company=" + company
				+ ", discontinued=" + discontinued + "]";
	}
}
