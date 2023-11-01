package org.deuce.starwarsService;

import java.util.Date;

public class PersonnageStarWars {
	private int id;
	private String nom;
	private Date dateEntree;
	public int getId() {
	return id;
	}
	public void setId(int id) {
	this.id = id;
	}
	public String getNom() {
	return nom;
	}
	public void setNom(String nom) {
	this.nom = nom;
	}
	public Date getJoinDate() {
	return dateEntree;
	}
	public void setJoinDate(Date dateEntree) {
	this.dateEntree = dateEntree;
	}
	public PersonnageStarWars(int id, String nom, Date dateEntree) {
	super();
	this.id = id;
	this.nom = nom;
	this.dateEntree =dateEntree;
	}
	public PersonnageStarWars() {
	super();
	}
}