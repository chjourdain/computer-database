package com.excilys.formation.computerdatabase.model;

import java.util.List;

import com.excilys.formation.computerdatabase.model.dto.ComputerDTO;
import com.excilys.formation.computerdatabase.persist.dao.mapper.ComputerDtoMapper;
import com.excilys.formation.computerdatabase.service.GenericService;

public class Pager {

    List<ComputerDTO> list;
    int nbEntries;
    int nbParPage;
    int nbPages;
    int pageActuelle;
    GenericService<Computer> service;

    public Pager(int nbParPage, int page, GenericService<Computer> service) {
	this.nbEntries = service.count();
	this.nbParPage = nbParPage;
	this.nbPages = (int) Math.ceil(nbEntries / nbParPage);
	pageActuelle = page;
	this.service = service;
	updateListe();
    }

    public void printListe() {
	for (Object object : list) {
	    System.out.println(object);
	}
    }

    public void next() {
	if (pageActuelle < nbPages) {
	    pageActuelle += 1;
	    updateListe();
	}
    }

    public void prev() {
	if (pageActuelle > 1) {
	    pageActuelle -= 1;
	    updateListe();
	}
    }

    public void updateListe() {
	List<Computer> listCompu = service.findAll((long) (pageActuelle - 1) * nbParPage, nbParPage);
	list = ComputerDtoMapper.mapRows(listCompu);
    }

    public int getNbEntries() {
	return nbEntries;
    }

    public void setNbEntries(int nbEntries) {
	this.nbEntries = nbEntries;
    }

    public int getNbParPage() {
	return nbParPage;
    }

    public void setNbParPage(int nbParPage) {
	this.nbParPage = nbParPage;
	updateListe();
	this.nbPages = (int) Math.ceil(nbEntries / nbParPage);
    }

    public int getNbPages() {
	return nbPages;
    }

    public void setNbPages(int nbPages) {
	this.nbPages = nbPages;
    }

    public int getPageActuelle() {
	return pageActuelle;
    }

    public void setPageActuelle(int pageActuelle) {
	this.pageActuelle = pageActuelle;
	updateListe();
    }

    public List<ComputerDTO> getListe() {
	return list;
    }

}
