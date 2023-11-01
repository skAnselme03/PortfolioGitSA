package org.cegep.gg.modele;

public class Client {
	private int codeInternaute;
    private String nom ;
    private String prenom ;
    private String username ;
    private String motDePasse ;
    private String courriel ;
    private String telephone ;


    /*********************/
    /*	CONSTRUCTEUR	 */
    /*********************/
    public Client(int codeInternaute, String nom, String prenom, String username, String motDePasse,
            String courriel, String telephone) {
	  this.codeInternaute = codeInternaute;
	  this.nom = nom;
	  this.prenom = prenom;
	  this.username = username;
	  this.motDePasse = motDePasse;
	  this.courriel = courriel;
	  this.telephone = telephone;
	}
    
    public Client(int codeInternaute, String nom, String prenom, String username, String courriel, String telephone) {
	  this.codeInternaute = codeInternaute;
	  this.nom = nom;
	  this.prenom = prenom;
	  this.username = username;
	  this.courriel = courriel;
	  this.telephone = telephone;
	}
    public Client( String username, String motDePasse, String courriel) {
	  this.username = username;
	  this.motDePasse = motDePasse;
	  this.courriel = courriel;
	}
    
    /*********************/
    /*	GETTER ET SETTER */
    /*********************/
    
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
        return username;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public String getCourriel() {
        return courriel;
    }

    public String getTelephone() {
        return telephone;
    }

    /*********************/
    /*		SETTER 		 */
    /*********************/
    
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
        this.username = username;
    }
    
    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }
    
    public void setCourriel(String courriel) {
        this.courriel = courriel;
    }
    
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    /*********************/
    /*		MÉTHODES	 */
    /*********************/
	@Override
	public String toString() {
	  return "Client{" +
	          ", nom : " + nom + ", " +
	          ", prenom : " + prenom + ", " +
	          ", username : " + username + ", " +
	          ", courriel : " + courriel + ", " +
	          ", telephone : " + telephone + ", " +
	          '}';
	}
}
