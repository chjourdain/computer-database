package com.excilys.formation.computerdatabase.model;

import java.util.List;

import com.excilys.formation.computerdatabase.model.dto.ComputerDTO;
import com.excilys.formation.computerdatabase.persist.dao.mapper.ComputerDtoMapper;
import com.excilys.formation.computerdatabase.service.GenericService;

public class Pager {

    public List<ComputerDTO> list;
    public int nbEntries = 0;
    public int nbParPage;
    public int nbPages;
    public int pageActuelle;
    public String search = null;

    public String getSearch() {
	return search;
    }

    public void setSearch(String search) {
	this.search = search;
    }

    GenericService<Computer> service;

    public Pager(int nbParPage, int page, GenericService<Computer> service) {
	nbEntries = service.count();
	this.nbParPage = nbParPage;
	this.nbPages = (int) Math.ceil(nbEntries / nbParPage) + 1;
	pageActuelle = page;
	this.service = service;
	updateListe();
    }
    
    public Pager(int nbParPage, int page, GenericService<Computer> service, String search) {
	this.search = search;
	nbEntries = service.count();
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
	if (search == null) {
	    List<Computer> listCompu = service.findAll((long) (pageActuelle - 1) * nbParPage, nbParPage);
	    list = ComputerDtoMapper.mapRows(listCompu);
	} else {
	    list = ComputerDtoMapper.mapRows(service.findAll(this));
	}
    }

    public int getNbEntries() {
	return nbEntries;
    }

    public void setNbEntries(int nbEntries) {
	this.nbEntries = nbEntries;
	this.nbPages = (int) Math.ceil(nbEntries / nbParPage);
    }

    public int getNbParPage() {
	return nbParPage;
    }

    public void setNbParPage(int nbParPage) {
	this.nbParPage = nbParPage;
	updateListe();
	this.nbPages = (int) Math.ceil(nbEntries / nbParPage) + 1;
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

    @Override
    public String toString() {
	return "Pager [nbEntries=" + nbEntries + ", nbParPage=" + nbParPage + ", nbPages=" + nbPages + ", pageActuelle="
		+ pageActuelle + ", search=" + search + "]";
    }

}
