package org.cegep.gg.modele;

public class Categorie {
    private int id;
    private String nom;
    private int responsable;

    public Categorie() {
		// TODO Auto-generated constructor stub
	}
	// Getters et Setters
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
	public int getResponsable() {
		return responsable;
	}
	public void setResponsable(int responsable) {
		this.responsable = responsable;
	}
}