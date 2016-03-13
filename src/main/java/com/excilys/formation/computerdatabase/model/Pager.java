package com.excilys.formation.computerdatabase.model;

import java.util.List;
import com.excilys.formation.computerdatabase.service.GenericService;

public class Pager<T> {

	List<T> liste;
	int nbEntries;
	int nbParPage;
	int nbPages;
	int pageActuelle;
	GenericService<T> service;

	public Pager(int nbParPage, int page, GenericService<T> service) {
		System.out.println(service);
			this.nbEntries = service.count();
			this.nbParPage = nbParPage;
			this.nbPages = (int) Math.ceil(nbEntries/nbParPage);
			pageActuelle=page;
			this.service = service;
			updateListe();
		}

	public void printListe(){
			for (Object object : liste) {
				System.out.println(object);
			}
		}

	public void next() {
			if (pageActuelle < nbPages){
				pageActuelle  += 1;
				updateListe();
			}
		}

	public void prev() {
			if (pageActuelle > 1){
				pageActuelle  -= 1;
				updateListe();
			}
		}

	public void updateListe(){
			System.out.println(service);
			this.liste = service.findAll((long)(pageActuelle - 1)*nbParPage, nbParPage);
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

	public List<T> getListe() {
			return liste;
		}

}
