package com.excilys.formation.computerdatabase.modele;

public class Computer {
	
	protected int id;
	protected final String name;
	protected String introduced;
	protected String discontinued;
	protected String companieName;
	
	public Computer(String pName){
		
		this.name=pName;
		
	}
	
	public Computer(int pId, String pName){
		this.id=pId;
		this.name=pName;
		
	}
	
	public Computer(int pId, String pName, String pIntroduced,String pDiscontinued, String pCompanieName  ){
		this.id=pId;
		this.name=pName;
		this.introduced=pIntroduced;
		this.discontinued=pDiscontinued;
		this.companieName=pCompanieName;
		
	}

	public String getIntroduced() {
		return introduced;
	}

	public void setIntroduced(String introduced) {
		this.introduced = introduced;
	}

	public String getDiscontinued() {
		return discontinued;
	}

	public void setDiscontinued(String discontinued) {
		this.discontinued = discontinued;
	}

	public String getCompanieName() {
		return companieName;
	}

	public void setCompanieName(String companieName) {
		this.companieName = companieName;
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
	public String toString() {
		StringBuilder sb = new StringBuilder("Ordianteur : "+name);
		sb.append((companieName==null) ? ", de marque non renseignée" : ",  de marque : "+companieName);
		sb.append((introduced==null) ? ", Aucun Renseignement sur l'introduction" : ",  introduit le : "+introduced);
		sb.append((discontinued==null) ? ", Aucun Renseignement sur la fin de production" : ",   Fin de production : "+discontinued);
		
		
		return sb.toString();
	}
	
	
}
