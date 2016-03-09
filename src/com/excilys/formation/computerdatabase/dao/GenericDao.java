package com.excilys.formation.computerdatabase.dao;

import java.sql.Connection;

import java.util.ArrayList;

public abstract class GenericDao<T> {
	public final static int ROW_BY_PAGE = 20;

		  protected Connection connect = null;
		   
		  public GenericDao(Connection conn){
		    this.connect = conn;
		  }
		   
		  public abstract ArrayList<T> list();
		  
		  public  boolean create(T obj){ return false;}

		  public  boolean delete(T obj){ return false;}

		  public boolean update(T obj){ return false;}

		  public T find(int id){ return null;}

		public int getNumberOfElement() {
			
			return 0;
		}
		}
	
	
