package org.cegep.gg.modele;


public class PanierItem{
    private int idProduit;
    private String nomProduit;
    private int quantite;
    private double prixUnitaire;
    private double prixTotal;

    //compteur d'objet panier
    private static int compteurProduitPanier = 0;

    /*********************/
    /*	CONSTRUCTEUR	 */
    /*********************/
    public PanierItem(int idProduit, String nomProduit, int quantite, double prixUnitaire) {
		  this.idProduit = idProduit;
		  this.quantite = quantite;
		  this.nomProduit = nomProduit;
		  this.prixUnitaire = prixUnitaire;
		  this.prixTotal = quantite * prixUnitaire;

		  compteurProduitPanier++;
    }

    public PanierItem() {
        compteurProduitPanier++;
    }

    /*********************/
    /*		GETTER 		 */
    /*********************/
    public int getIdProduit() {
        return idProduit;
    }

    public String getNomProduit() {
        return nomProduit;
    }

    public int getQuantite() {
        return quantite;
    }

    public double getPrixUnitaire() {
        return prixUnitaire;
    }

   public double getPrixTotal() {
        return prixTotal;
    }

    public int getCompteurProduitPanier() {
        return compteurProduitPanier;
    }

    /*********************/
    /*		SETTER 		 */
    /*********************/

    public void setIdProduit(int idProduit) {
        this.idProduit = idProduit;
    }

    public void setNomProduit(String nomProduit) {
        this.nomProduit = nomProduit;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public void setPrixUnitaire(double prixUnitaire) {
        this.prixUnitaire = prixUnitaire;
    }


    /*********************/
    /*		MÉTHODES	 */
    /*********************/

    @Override
    public String toString() {
        return "Panier{idProduit=" + idProduit +
                ", nom=" + nomProduit +
                ", quantite=" + quantite +
                ", prixUnitaire=" + prixUnitaire +
                '}';
    }
}

