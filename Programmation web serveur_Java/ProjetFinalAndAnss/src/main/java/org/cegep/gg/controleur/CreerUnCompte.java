package org.cegep.gg.controleur;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Random;

import javax.sql.DataSource;

import org.cegep.gg.modele.Adresse;
import org.cegep.gg.modele.Client;
import org.cegep.gg.service.ClientDBServices;

import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


import org.apache.log4j.Logger;

/**
 * Servlet implementation class CreerUnCompte
 */
@WebServlet("/signUp")
public class CreerUnCompte extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ClientDBServices clientDBServices;

	static final Logger LOGGER = Logger.getLogger(CreerUnCompte.class);

    private static final String CHEMIN_PAGE_CREER_UN_COMPTE = "/creerUnCompte.jsp";
    private static final String CHEMIN_PAGE_CONNEXION = "/client/seConnecter.jsp"; // Page de connexion
    private static final String CHEMIN_PAGE_ERREUR404 = "/erreur404.jsp"; // Page d'acceuil du client



    @Resource(name="jdbc/sql9641461")
	private DataSource dataSource;

    @Override
	public void init() throws ServletException {
    	super.init();

		try {
			clientDBServices = new ClientDBServices(dataSource);

			LOGGER.info("init() - OK");

		}
		catch (Exception exc) {
			LOGGER.error("init() - erreur = " + exc.getMessage());
			throw new ServletException(exc);
		}
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	   // Traiter la demande de déconnexion pour la méthode GET
        signUpPage(request, response);
	}

	 @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
		 // Récupérer les valeurs du formulaire de connexion
		String action = request.getParameter("action");

		try {

				if (action == null) {
				action = "SIGNUP";
			}

			switch (action) {
			case "SIGNUP":
					signUpClient(request, response);
					break;
			//profil modifier
			case "PROFIL":
		        modifierProfil(request, response);
				break;
			}
		}
		catch(Exception ex) {
			ex.printStackTrace();
            response.sendRedirect(CHEMIN_PAGE_ERREUR404);
		}

	}

	 	/************************************************/
	    /*  Modifier les données de profil d'un client  */
		/************************************************/

		/**
	  * Modifier les données de profil d'un client
	  *
	  * @param request   L'objet HttpServletRequest pour récupérer les données de la requête.
	  * @param response  L'objet HttpServletResponse pour gérer la réponse.
	  * @throws Exception En cas d'erreur, une exception est levée.
	 */
	private void modifierProfil(HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession(false);
		Boolean clientConnecte = (Boolean) session.getAttribute("clientConnecte");
		Client clientDonneesConnecter = (Client) request.getAttribute("clientDonnees");
        Adresse adresseClientConnecter = (Adresse) request.getAttribute("clientAdresse");
        Adresse adresseLivraionConnecter = (Adresse) request.getAttribute("clientAdresseL");
		int idClient = clientDonneesConnecter.getidClient();//id du client à modifier connecter
		int idAdresse = adresseClientConnecter.getIdAdresse();//id du client à modifier connecter
		int idAdresseL = adresseLivraionConnecter.getIdAdresse();//id du client à modifier connecter


		//données modifier du formulaire
	 	Client client = extraireFormDataClient(request);
	 	client.setIdClient(clientDonneesConnecter.getidClient());
	 	client.setUsername(extraireParametre(request, "username"));
	 	Adresse adresse = extraireFormDataAdresse(request);
	 	Adresse adresseLivraison = extraireFormDataAdresseLivraison(request);

	 	//si les données du formulaire reste inchangé, on ne fait pas de modification
	 	if((!clientDonneesConnecter.estDifferentDe(client)) ||
	 			(!adresseClientConnecter.estDifferentDe(adresse)) ||
	 			(!adresseLivraionConnecter.estDifferentDe(adresseLivraison))) {
	 		return;
	 	}

 		//si un des champs de l'adresse diffèrent, on fait une mise à jour direct
 		//sinon, on créer la nouvelle adresse
 		if(adresseClientConnecter.estDifferentDe(adresse)) {
 			clientDBServices.modifierAdresse(adresse);
 			client.setIdAdressePersonnelle(idAdresse);

 			//set l'id de l'adresse du client si restent inchangé
 			client.setIdAdresseLivraison(adresseClientConnecter.getIdAdresse());
 		}else {
 			clientDBServices.insererAdresse(adresse);
 			//récupérer l'id de la nouvelle adresse
 			idAdresse = clientDBServices.getIdAdresseByValues(adresse);
 			//set l'id de l'adresse dans client
 			client.setIdAdressePersonnelle(idAdresse);
		}

 		//si un des champs de l'adresse diffèrent, on fait une mise à jour direct
 		//sinon, on créer la nouvelle adresse
 		if(adresseLivraionConnecter.estDifferentDe(adresseLivraison)) {
 			clientDBServices.modifierAdresse(adresseLivraison);
 			//set l'id de l'adresse de livraison client si restent inchangé
 			client.setIdAdresseLivraison(adresseLivraionConnecter.getIdAdresse());
 		}else {
 			clientDBServices.insererAdresse(adresseLivraison);
 			//récupérer l'id de la nouvelle adresse
 			idAdresseL = clientDBServices.getIdAdresseByValues(adresseLivraison);
 			//set l'id de l'adresse de livraison dans client
 			client.setIdAdresseLivraison(idAdresseL);
 		}


	 	//mettre à jour la table client si un des attributs est différent
	 	if(clientDonneesConnecter.estDifferentDe(client)) {
	 		clientDBServices.modifierClient(client);
	 	}
	 	//message de confirmation de changement
        request.setAttribute("msgClientModifier", "Modifications enregistrer");


	}


				 	/************************************************/
				    /*  		Ajouter un nouveau client		    */
					/************************************************/

	 /**
	  * Méthode pour ajouter un nouveau client.
	  *
	  * @param request   L'objet HttpServletRequest pour récupérer les données de la requête.
	  * @param response  L'objet HttpServletResponse pour gérer la réponse.
	  * @throws Exception En cas d'erreur, une exception est levée.
	  */
	 private void signUpClient(HttpServletRequest request, HttpServletResponse response)
				throws Exception {

	        try {
				/******************************************************************************************/
			    /*  		Création des objest client et adresses perso et adresse de livraison  		  */
			    /*  Vérifier que l'adresse de livraison ou l'adresse de livraison existe déjà dans la bdd */
		        /******************************************************************************************/

			 	Client client = extraireFormDataClient(request);
			 	//génerer un username pour le client
			 	client.setUsername(generateUniqueUsername(client.getPrenom(), client.getNom()));
			 	Adresse adresse = extraireFormDataAdresse(request);
			 	Adresse adresseLivraison = extraireFormDataAdresseLivraison(request);

			 	/****************************************/
			    /*  Valider les données du formulaires  */
				/****************************************/

			 	if(!donneesFormEsValide(request, client)) {
		            //request.getRequestDispatcher("/creerUnCompte.jsp").forward(request, response);
		            request.getRequestDispatcher(CHEMIN_PAGE_CREER_UN_COMPTE).forward(request, response);
			 	}

		        // set l'adresse personnel du client
		        client.setIdAdressePersonnelle(getIdAdresse(adresse));

		        // set l'adresse de livarison du client
		        if(adresseLivraison.getAdresse() == "") {
		        	client.setIdAdresseLivraison(client.getIdAdressePersonnelle());
		        }else {
		        	client.setIdAdresseLivraison(getIdAdresse(adresseLivraison));
		        }

		        // Vérifiez si l'adresse personnelle existe déjà dans la base de données
		        //int idClient = clientDBServices.getClient(client.getCourriel());

		        boolean emailClientExiste = clientDBServices.emailExiste(client.getCourriel());

		        // Si le cllient n'existe pas dans la bdd, en créer un nouveau
		        if (emailClientExiste) {
		        	 // Si un client existe déjà, envoyer un message
		            request.setAttribute("msgClientExiste", "Ce compte existe déjà, veuillez vous connecter avec vos identifiant!");
		            // Redirigez l'utilisateur vers une page de confirmation ou de succès
		            //.getRequestDispatcher("login").forward(request, response);

	            }else {
			        // Enregistrez le client dans la base de données
	            	//clientDBServices.enregistrerClient(client);
	            	boolean clientAjoute = clientDBServices.ajouterClient(client);

	            	if(clientAjoute) {
			        	 // Si un client existe déjà, envoyer un message
			            request.setAttribute("msgClientExiste", "Binevenue parmi nous, veuillez vous connecter!");
			        }else {
		                request.setAttribute("msgClientExiste", "Erreur lors de l'ajout du client.");
	            	}

	            }

            	//request.getRequestDispatcher("/client/seConnecter.jsp").forward(request, response);
            	request.getRequestDispatcher(CHEMIN_PAGE_CONNEXION).forward(request, response);

	        } catch (Exception e) {
	            e.printStackTrace();
	            // Gérez les erreurs (par exemple, renvoyez l'utilisateur vers une page d'erreur)
	            //response.sendRedirect("erreur404.jsp");
	            response.sendRedirect(CHEMIN_PAGE_ERREUR404);
	        }

	}


									 	/****************************************/
									    /*  Valider les données du formulaires  */
										/****************************************/

	 /**
	  * Valider les données du formulaire du nouveau client
	  * @param client
	  * @return true si le formulaire est valide, false sinon
	  */
	 private boolean donneesFormEsValide(HttpServletRequest request, Client client) {
		   // Valider l'adresse email et la confirmation
	        if (!validerCourriel(client.getCourriel()) || !validerCourriel(client.getConfirmCourriel())) {
	            request.setAttribute("errorMsgSignUp", "Invalid confirmation email");
	            return false;
	        }

	        // Valider que l'adresse email et la confirmation sont les mêmes
	        if (!client.getCourriel().equals(client.getConfirmCourriel())) {
	            request.setAttribute("errorMsgSignUp", "L'adresse email et la confirmation ne correspondent pas.");
	            return false;
	        }

	        // Valider le numéro de téléphone
	        if (!validerTelephone(client.getTelephone())) {
	        	 // En cas d'échec de connexion, affiche un message d'erreur sur la page de connexion (CréerUnCompte)
	            request.setAttribute("errorMsgSignUp", "Invalid téléphone");
	            return  false;
	        }

		     // Valider l'âge
		     if (!validerAge(client.getDateNaissance())) {
	        	 // En cas d'échec de connexion, affiche un message d'erreur sur la page de connexion (CréerUnCompte)
	            request.setAttribute("errorMsgSignUp", "Vous devez être agée de 18ans et plus!");
		         return false;
		     }
		 return true;

	 }

										/****************************************/
										/* Récupérez les données du formulaire  */
										/****************************************/

	 /**
	  * Extrait les données d'adresse du formulaire à partir de la requête HTTP.
	  *
	  * @param request L'objet HttpServletRequest.
	  * @return Un objet ClientFormData contenant les données d'adress du formulaire.
	  */
	 private Client extraireFormDataClient(HttpServletRequest request) {
		Client donneesClient = new Client();
		donneesClient.setPrenom(extraireParametre(request, "prenom"));
	    donneesClient.setNom(extraireParametre(request, "nom"));
	    donneesClient.setDateNaissance(extraireParametre(request, "dateNaiss"));
	    donneesClient.setTelephone(extraireParametre(request, "telephone"));
	    donneesClient.setCourriel(extraireParametre(request, "email"));
	    donneesClient.setconfirmCourriel(extraireParametre(request, "confirmerEmail"));
	    donneesClient.setMotDePasse(extraireParametre(request, "password"));
	    //set code interanute à 2 (pour client et 1 pour admin Voir BDD)
	    donneesClient.setCodeInternaute(2);


		return donneesClient;
	 }

	 /**
	  * Vérifier si une adresse existe dans la bdd. Si elle n'existe pas, on le créer et on retourne son id
	  * @param adresse adresse fourni
	  * @return id de l'adresse dans la bdd
	  */
	 private int getIdAdresse(Adresse adresse){
		int idAdresse = -1;

		try {
			// Vérifiez si l'adresse personnelle existe déjà dans la base de données
			idAdresse = clientDBServices.getIdAdresseByValues(adresse);

			// Si l'adresse personnelle n'existe pas, insérez-la dans la bdd
			if (idAdresse == -1) {
			    clientDBServices.insererAdresse(adresse);
			    idAdresse = clientDBServices.getIdAdresseByValues(adresse);
		    }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return idAdresse;
	}

	 /**
	  * Extrait les données d'adresse du formulaire à partir de la requête HTTP.
	  *
	  * @param request L'objet HttpServletRequest.
	  * @return Un objet ClientFormData contenant les données d'adresse du formulaire.
	  */
	 private Adresse extraireFormDataAdresse(HttpServletRequest request) {
		Adresse donneesAdresse = new Adresse();
		donneesAdresse.setAdresse(extraireParametre(request, "adresse"));
		donneesAdresse.setVille(extraireParametre(request, "ville"));
		donneesAdresse.setProvince(extraireParametre(request, "province"));
		donneesAdresse.setPays(extraireParametre(request, "pays"));
		donneesAdresse.setCodePostal(extraireParametre(request, "postalCode"));

		return donneesAdresse;
	 }
	 /**
	  * Extrait les données d'adresse de livraion du formulaire à partir de la requête HTTP.
	  *
	  * @param request L'objet HttpServletRequest.
	  * @return Un objet ClientFormData contenant les données d'adresse de livraison du formulaire.
	  */
	 private Adresse extraireFormDataAdresseLivraison(HttpServletRequest request) {
		Adresse donneesAdresseLivraion = new Adresse();
		donneesAdresseLivraion.setAdresse(extraireParametre(request, "adresseLivraison"));
	    donneesAdresseLivraion.setVille(extraireParametre(request, "villeLivraison"));
	    donneesAdresseLivraion.setProvince(extraireParametre(request, "provinceLivraison"));
	    donneesAdresseLivraion.setPays(extraireParametre(request, "paysLivraison"));
	    donneesAdresseLivraion.setCodePostal(extraireParametre(request, "postalCodeLivraison"));

		return donneesAdresseLivraion;
	 }


	 /**
	  * Extrait un paramètre de la requête HTTP en utilisant son nom.
	  *
	  * @param request L'objet HttpServletRequest.
	  * @param paramName Le nom du paramètre à extraire.
	  * @return La valeur du paramètre, ou une chaîne vide si le paramètre est absent.
	  */
	 private String extraireParametre(HttpServletRequest request, String paramName) {
	     String value = request.getParameter(paramName);
	     return (value != null) ? value : "";
	 }

	 /**
	  * Génère un username unique en utilisant les deux premières lettres du nom et du prénom
	  * suivi de 4 chiffres aléatoires. Vérifie la base de données pour s'assurer que le username est unique.
	  *
	  * @param prenom Le prénom de l'utilisateur.
	  * @param nom    Le nom de l'utilisateur.
	  * @return Un username unique.
	  * @throws SQLException Si une erreur SQL survient lors de l'accès à la base de données.
	  */
	 private String generateUniqueUsername(String prenom, String nom) throws SQLException {
	     String baseUsername = (prenom.substring(0, 2) + nom.substring(0, 2)).toLowerCase();
	     String username = baseUsername + generer4NumberAleatoire(4);

	     boolean usernameExist = clientDBServices.usernameExistsInDatabase(username);
	     // Vérifie si le username existe déjà dans la base de données
	     while (usernameExist) {
	         username = baseUsername + generer4NumberAleatoire(4); // Change les 4 chiffres aléatoires
	     }

	     return username;
	 }

	 /**
	  * Génère une chaîne de chiffres aléatoires de la longueur spécifiée.
	  *
	  * @param length La longueur de la chaîne de chiffres aléatoires.
	  * @return Une chaîne de chiffres aléatoires.
	  */
	 private String generer4NumberAleatoire(int length) {
	     Random random = new Random();
	     StringBuilder digits = new StringBuilder();

	     for (int i = 0; i < length; i++) {
	         digits.append(random.nextInt(10)); // Ajoute un chiffre aléatoire entre 0 et 9
	     }

	     return digits.toString();
	 }


	/**
	 * Méthode pour valider le format de l'adresse e-mail
	 * @param courriel L'adresse e-mail à valider
	 * @return true si l'adresse e-mail est valide, sinon false
	 */
	private void signUpPage(HttpServletRequest request, HttpServletResponse response) throws IOException {
		   // Efface les cookies en les remplaçant par des cookies expirés
	    Cookie[] cookies = request.getCookies();
	    if (cookies != null) {
	        for (Cookie cookie : cookies) {
	            cookie.setMaxAge(0); // Expiration immédiate
	            response.addCookie(cookie);
	        }
	    }

	    // Redirige vers la page de signUp
	    //response.sendRedirect(request.getContextPath() + "/creerUnCompte.jsp");
	    response.sendRedirect(request.getContextPath() + CHEMIN_PAGE_CREER_UN_COMPTE);

	}



	/**
	 * Méthode pour valider le format du numéro de téléphone (format canadien)
	 * @param telephone Le numéro de téléphone à valider
	 * @return true si le numéro de téléphone est valide, sinon false
	 */
	private boolean validerCourriel(String courriel) {
	    // Utilisez une expression régulière pour valider le format de l'adresse e-mail
	    String regex = "^[A-Za-z0-9+_.-]+@(.+)$";

	    return courriel.matches(regex);
	}

	/**
	 * Méthode pour valider le format du numéro de téléphone (format canadien)
	 * @param telephone
	 * @return
	 */
	private boolean validerTelephone(String telephone) {
	    // Utilisez une expression régulière pour valider le format du numéro de téléphone
		 String regex = "^(\\+\\d{1,2}\\s?)?(\\(\\d{3}\\)|\\d{3})[-.\\s]?\\d{3}[-.\\s]?\\d{4}$";

	    return telephone.matches(regex);
	}

	/**
	 * Méthode pour valider si la personne a au moins 18 ans.
	 *
	 * @param dateNaissance La date de naissance de la personne au format "yyyy-MM-dd".
	 * @return true si la personne a au moins 18 ans, sinon false.
	 */
	private boolean validerAge(String dateNaissance) {
	    // Formattez la date de naissance en un objet LocalDate
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	    LocalDate dateDeNaissance = LocalDate.parse(dateNaissance, formatter);

	    // Obtenez la date actuelle
	    LocalDate dateActuelle = LocalDate.now();

	    // Calculez la différence d'âge
	    Period difference = Period.between(dateDeNaissance, dateActuelle);

	    // Vérifiez si la personne a au moins 18 ans
	    return difference.getYears() >= 18;
	}


}
