package org.cegep.gg.modele;

public class Produit {
	private int id;
    private String nom;
    private String description;
    private int categorie;
    private float prix;
    private String image_url;
    private int responsable;
    
    public Produit(int id, String nom, String description, int categorie, float prix, String image_url, int responsable) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.categorie = categorie;
        this.prix = prix;
        this.image_url = image_url;
        this.responsable = responsable;
    }
    
	public Produit() {
		// TODO Auto-generated constructor stub
	}

	// liste des Getters et Setters
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getCategorie() {
		return categorie;
	}
	public void setCategorie(int categorie) {
		this.categorie = categorie;
	}
	public float getPrix() {
		return prix;
	}
	public void setPrix(float prix) {
		this.prix = prix;
	}
	public String getImage_url() {
		return image_url;
	}
	public void setImage_url(String image_url) {
		this.image_url = image_url;
	}
	public int getResponsable() {
		return responsable;
	}
	public void setResponsable(int responsable) {
		this.responsable = responsable;
	}
	
	// liste des ToString() - à faire...
}