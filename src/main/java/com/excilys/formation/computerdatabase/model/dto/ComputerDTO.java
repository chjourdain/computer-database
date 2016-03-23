package com.excilys.formation.computerdatabase.model.dto;

/**
 * DTO of the POJO computer, used to transfer data to jsp pages
 * 
 * @author charles
 *
 */
public class ComputerDTO {

    public String id;
    public String name;
    public String introduced;
    public String companyName;
    public String discontinued;

    public ComputerDTO(String id, String name, String introduced, String companyName, String companyId,
	    String discontinued) {
	this.id = id;
	this.name = name;
	this.introduced = introduced;
	this.companyName = companyName;
	this.discontinued = discontinued;
    }

    public ComputerDTO() {
    }

    private ComputerDTO(ComputerDTOBuilder computerDTOBuilder) {
	this.id = computerDTOBuilder.id;
	this.name = computerDTOBuilder.name;
	this.introduced = computerDTOBuilder.introduced;
	this.companyName = computerDTOBuilder.companyName;
	this.discontinued = computerDTOBuilder.discontinued;
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

    public String getDiscontinued() {
	return discontinued;
    }

    public void setId(String id) {
	this.id = id;
    }

    public void setName(String name) {
	this.name = name;
    }

    public void setIntroduced(String introduced) {
	this.introduced = introduced;
    }

    public void setCompanyName(String companyName) {
	this.companyName = companyName;
    }

    public void setDiscontinued(String discontinued) {
	this.discontinued = discontinued;
    }

    public static class ComputerDTOBuilder {
	private String id;
	private String name;
	private String introduced;
	private String companyName;
	private String discontinued;

	
	public ComputerDTOBuilder() {
	}
	
	public ComputerDTOBuilder name(String name) {
	    this.name = name;
	    return this;
	}
	
	
	public ComputerDTOBuilder introduced(String intro) {
	    introduced = intro;
	    return this;
	}

	public ComputerDTOBuilder discontinued(String disco) {
	   discontinued = disco;
	    return this;
	}

	public ComputerDTOBuilder companyName(String company) {
	   companyName = company;
	    return this;
	}

	public ComputerDTOBuilder id(String id) {
	    this.id = id; 
	    return this;
	}
	public ComputerDTO build() {
	    return new ComputerDTO(this);
	}
    }
}