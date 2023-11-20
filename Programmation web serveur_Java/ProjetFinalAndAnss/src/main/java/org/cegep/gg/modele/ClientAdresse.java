package org.cegep.gg.modele;

public class ClientAdresse extends Client {

	private int idAdresse;
	private String adresse;
    private String ville;
    private String province;
    private String pays;
    private String codePostal;


    /*********************/
    /*	CONSTRUCTEUR	 */
    /*********************/
      public ClientAdresse(String adresse, String ville, String province, String pays, String codePostal) {
        super(); // Appel au constructeur par défaut de la classe parent (Client)
        this.adresse = adresse;
        this.ville = ville;
        this.province = province;
        this.pays = pays;
        this.codePostal = codePostal;
    }

      public ClientAdresse() {
          // Constructeur par défaut sans arguments
      }

      /*********************/
      /*	  GETTER 	   */
      /*********************/

    // Ajoutez les getters et les setters pour chaque attribut spécifique à l'adresse du client
    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getVille() {
        return ville;
    }

    public String getProvince() {
        return province;
    }

    public String getPays() {
        return pays;
    }

    public String getCodepost() {
        return codePostal;
    }

    public int getIdAdresse() {
        return idAdresse;
    }

    /*********************/
    /*		SETTER 		 */
    /*********************/

    public void setVille(String ville) {
        this.ville = ville;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    public void setCodepost(String codePostal) {
        this.codePostal = codePostal;
    }

    /*public void setIdAdresse (int idAdresse) {
        this.idAdresse = idAdresse;
    }*/


    /*********************/
    /*		MÉTHODES	 */
    /*********************/
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