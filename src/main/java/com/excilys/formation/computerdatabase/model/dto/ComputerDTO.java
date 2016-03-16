package com.excilys.formation.computerdatabase.model.dto;

public class ComputerDTO {

	public String id;
	public String name;
	public String introduced;
	public String companyName;
	public String companyId;
	public String discontinued;

	public ComputerDTO(String id, String name, String introduced, String companyName, String companyId,
			String discontinued) {
		this.id = id;
		this.name = name;
		this.introduced = introduced;
		this.companyName = companyName;
		this.companyId = companyId;
		this.discontinued = discontinued;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getIntroduced() {
		return introduced;
	}

	public String getCompanyName() {
		return companyName;
	}

	public String getCompanyId() {
		return companyId;
	}

	public String getDiscontinued() {
		return discontinued;
	}		
}
