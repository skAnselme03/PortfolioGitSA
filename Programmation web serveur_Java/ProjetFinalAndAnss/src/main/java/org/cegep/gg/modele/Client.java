package org.cegep.gg.modele;

public class Client {
	private int idClient;
	private int codeInternaute;
    private String nom ;
    private String prenom ;
    private String username ;
    private String dateNaissance;
    private String telephone ;
    private String courriel ;
    private String confirmCourriel ;
    private String motDePasse ;
    private int idAdressePersonnelle ;
    private int idAdresseLivraison;


    /*********************/
    /*	CONSTRUCTEUR	 */
    /*********************/
    public Client(int codeInternaute, String nom, String prenom,String username,
    		      String dateNaissance, String motDePasse, String courriel,
    		      String telephone) {
	  this.codeInternaute = codeInternaute;
	  this.nom = nom;
	  this.prenom = prenom;
	  this.username = username;
	  this.motDePasse = motDePasse;
	  this.courriel = courriel;
	  this.telephone = telephone;
      this.dateNaissance = dateNaissance;

	}

    public Client(int codeInternaute, String nom, String prenom,String username,
    			  String dateNaissance,String courriel, String telephone) {
	  this.codeInternaute = codeInternaute;
	  this.nom = nom;
	  this.prenom = prenom;
	  this.username = username;
	  this.courriel = courriel;
	  this.telephone = telephone;
      this.dateNaissance = dateNaissance;
	}
    public Client(String username, String motDePasse, String courriel) {
  	  this.username = username;
	  this.motDePasse = motDePasse;
	  this.courriel = courriel;
	}

    public Client(int idClient, int codeInternaute, String nom, String prenom, String username, String dateNaissance,
            String motDePasse, String courriel, String telephone, int idAdressePersonnelle, int idAdresseLivraison) {
        this.idClient = idClient;
        this.codeInternaute = codeInternaute;
        this.nom = nom;
        this.prenom = prenom;
        this.username = username;
        this.motDePasse = motDePasse;
        this.courriel = courriel;
        this.telephone = telephone;
        this.dateNaissance = dateNaissance;
        this.idAdressePersonnelle = idAdressePersonnelle;
        this.idAdresseLivraison = idAdresseLivraison;
    }
    public Client() {
        // Constructeur par défaut sans arguments
    }

    /*********************/
    /*		GETTER 		 */
    /*********************/

    public int getidClient() {
        return idClient;
    }


    public int getCodeInternaute() {
        return codeInternaute;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getUsername() {
        return username;}

    public String getDateNaissance() {
        return dateNaissance;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public String getCourriel() {
        return courriel;
    }

    public String getConfirmCourriel() {
        return confirmCourriel;
    }

    public String getTelephone() {
        return telephone;
    }

    public int getIdAdressePersonnelle() {
        return idAdressePersonnelle;
    }

    public int getIdAdresseLivraison() {
        return idAdresseLivraison;
    }

    /*********************/
    /*		SETTER 		 */
    /*********************/

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public void setCodeInternaute(int codeInternaute) {
        this.codeInternaute = codeInternaute;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setUsername(String username) {
        this.username = username;}

    public void setDateNaissance(String dateNaissance) {
        this.dateNaissance = dateNaissance;
    }


    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public void setCourriel(String courriel) {
        this.courriel = courriel;
    }

    public void setconfirmCourriel(String courrielConfirm) {
        this.confirmCourriel = courrielConfirm;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public void setIdAdressePersonnelle(int idAdressePersonnelle) {
        this.idAdressePersonnelle = idAdressePersonnelle;
    }

    public void setIdAdresseLivraison(int idAdresseLivraison) {
        this.idAdresseLivraison = idAdresseLivraison;
    }

    /*********************/
    /*		MÉTHODES	 */
    /*********************/
    /**
     * Comparer deux objet client
     * @param autreClient l'objet client comparer
     * @return vrai si un des champs est different, faux sinon
     */
    public boolean estDifferentDe(Client autreClient) {
        // Comparez les champs du client, en excluant idClient, idAdressePersonnelle et idAdresseLivraison
        if ((codeInternaute != autreClient.codeInternaute) || !nom.equals(autreClient.nom) || !prenom.equals(autreClient.prenom) || !username.equals(autreClient.username)) return true;
        if (!dateNaissance.equals(autreClient.dateNaissance)) return true;
        if (!motDePasse.equals(autreClient.motDePasse)) return true;
        if (!courriel.equals(autreClient.courriel)) return true;
        if (!telephone.equals(autreClient.telephone)) return true;

        // Si tous les champs sont identiques, retournez false
        return false;
    }

	@Override
	public String toString() {
	  return "Client{" +
	          ", nom : " + nom + "\n" +
	          ", prenom : " + prenom + "\n" +
	          ", username : " + username + "\n" +
	          ", date de naissance : " + dateNaissance + "\n" +
	          ", courriel : " + courriel + "\n" +
	          ", telephone : " + telephone + "\n" +
              ", idAdressePersonnelle=" + idAdressePersonnelle +
              ", idAdresseLivraison=" + idAdresseLivraison +
	          '}';
	}
}
