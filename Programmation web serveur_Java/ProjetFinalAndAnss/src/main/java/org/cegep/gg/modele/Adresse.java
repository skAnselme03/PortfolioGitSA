package org.cegep.gg.modele;

public class Adresse {
    private int idAdresse;
    private String adresse;
    private String ville;
    private String province;
    private String pays;
    private String codePostal;

    /*********************/
    /*	CONSTRUCTEUR	 */
    /*********************/

    // Constructeur par défaut
    public Adresse() {
    }

    public Adresse(int idAdresse, String adresse, String ville, String province, String pays, String codePostal) {
		super();
		this.idAdresse = idAdresse;
		this.adresse = adresse;
		this.ville = ville;
		this.province = province;
		this.pays = pays;
		this.codePostal = codePostal;
	}

	/*********************/
    /*	  GETTER 	   */
    /*********************/

    // Getter et Setter pour idAdresse
    public int getIdAdresse() {
        return idAdresse;
    }

    // Getter et Setter pour adresse
    public String getAdresse() {
        return adresse;
    }

    // Getter et Setter pour ville
    public String getVille() {
        return ville;
    }

    // Getter et Setter pour province
    public String getProvince() {
        return province;
    }

    // Getter et Setter pour pays
    public String getPays() {
        return pays;
    }

    // Getter et Setter pour codePostal
    public String getCodePostal() {
        return codePostal;
    }


    /*********************/
    /*		SETTER 		 */
    /*********************/

    public void setIdAdresse(int idAdresse) {
        this.idAdresse = idAdresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }
    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }

    /*********************/
    /*		MÉTHODES	 */
    /*********************/
    /**
     * Vérifie que deux objet sont identique en excluant leur id
     * @param autreAdresse l'objet adresse à comparer
     * @return vrai si un des champ est différent, faux sinon
     */
    public boolean estDifferentDe(Adresse autreAdresse) {
        // Comparez les champs d'adresse, en excluant l'ID de l'adresse
        if (!adresse.equals(autreAdresse.adresse) || !ville.equals(autreAdresse.ville) || !province.equals(autreAdresse.province) || !pays.equals(autreAdresse.pays)) return true;
        if (!codePostal.equals(autreAdresse.codePostal)) return true;

        // Si tous les champs sont identiques, retournez false
        return false;
    }

    // Méthode toString pour obtenir une représentation sous forme de chaîne de caractères
    @Override
    public String toString() {
        return super.toString() + " Adresse {" +
                "Adresse : " + adresse + '\'' +
                ", Ville : " + ville + '\'' +
                ", Province : " + province + '\'' +
                ", Pays : " + pays + '\'' +
                ", CodePostal : " + codePostal + '\'' +
                '}';
    }
}

