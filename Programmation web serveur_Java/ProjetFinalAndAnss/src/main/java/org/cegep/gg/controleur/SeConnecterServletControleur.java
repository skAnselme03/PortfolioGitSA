package org.cegep.gg.controleur;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.cegep.gg.modele.Adresse;
import org.cegep.gg.modele.Client;
import org.cegep.gg.service.AdminDBServices;
import org.cegep.gg.service.ClientDBServices;

import jakarta.annotation.Resource;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


@WebServlet("/login")
public class SeConnecterServletControleur extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ClientDBServices clientDBServices;
	private AdminDBServices adminDBServices;

    @Resource(name="jdbc/sql9641461")
	private DataSource dataSource;

    @Override
	public void init() throws ServletException {
    	super.init();

		try {
			clientDBServices = new ClientDBServices(dataSource);
			adminDBServices = new AdminDBServices(dataSource); // Ajoutez cette ligne pour initialiser adminDBServices
		}
		catch (Exception exc) {
			throw new ServletException(exc);
		}
	}

    /**
     * Traite la requête GET.
     */
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	// Vérifier si l'utilisateur est connecté
    	//TODO: POUR DÉCOMMENTER QUAND LA MÉTHODE DE CONNECTION SANS HTTPSESSION SERA FONCTIONNEL
    	//boolean clientConnecte = request.getAttribute("clientConnecte") != null && (boolean) request.getAttribute("clientConnecte");
    	try {

	    	// Obtient la session actuelle ou crée une nouvelle session si elle n'existe pas
	        HttpSession session = request.getSession(false);

	        // Vérifie si la session existe et si l'utilisateur est connecté
	        boolean clientConnecte = session != null && session.getAttribute("clientConnecte") != null
	                && (boolean) session.getAttribute("clientConnecte");

	        if (!clientConnecte) {
	            // L'utilisateur n'est pas connecté, redirigez-le vers la page de connexion
	            loginPage(request, response);
	            return; // Arrêtez le traitement de la méthode doGet ici
	        }
        }
		catch(Exception ex) {
			throw new ServletException(ex);
		}
    }


	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {

	    	String nomUtilisateur;
			if (request.getUserPrincipal() != null) {
				nomUtilisateur = request.getUserPrincipal().getName();
				System.out.println("Get User Principal Name = " + request.getUserPrincipal().getName()); // username || couriel
				System.out.println("Is User In Role = " + request.isUserInRole("client")); // role
		    }

			// Récupérer les valeurs du formulaire de connexion
			String action = request.getParameter("action");

			if (action == null) {
				action = "LOGIN";
			}

			switch (action) {
			case "LOGIN":
				seConnecter(request, response);
				break;
			}
		}
		catch(Exception ex) {
			//LOGGER.error("doGet - erreur = " + ex.getMessage());
			throw new ServletException(ex);
		}
	}

	/**
	 * Gère la tentative de connexion de l'utilisateur.
	 *
	 * @param request La requête HTTP contenant les données de connexion.
	 * @param response La réponse HTTP à renvoyer à l'utilisateur.
	 * @throws Exception Si une erreur survient pendant le processus de connexion.
	 */
	private void seConnecter(HttpServletRequest request, HttpServletResponse response)
			throws Exception {

			// Récupère les paramètres de la requête (login et password)
		 	String login = request.getParameter("login");
	        String password = request.getParameter("password");
	        // Crée une session si elle n'existe pas déjà
            HttpSession session = request.getSession(true);

            // Récupérez les rôles de l'utilisateur depuis la session
            boolean isClient = request.isUserInRole("client");

            // vérifie si l'internaute est un admin
            if (adminDBServices.estAdmin(login)) {
            	session.setAttribute("isAdmin", true);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/pageAdmin.jsp");
                dispatcher.forward(request, response);
            } else {
            	// Valide la connexion en utilisant la méthode validateLogin
    	        boolean loginValide = loginEstValide(login, password);
    	        if (loginValide) {
    	        	// Récupère le client authentifié en utilisant la méthode getClientFromDatabase
    	            //Client clientAuthentifier = getClientFromBDD(login);

    	            // Stocke les informations de l'utilisateur connecté en tant qu'attributs de requête
    	            //TODO: À VÉRIFIER AVEC LE PROF POUR DÉBOGUER
    	            //request.setAttribute("clientConnecte", true);

    	        	// After retrieving the client's data
    	            Client donneesClient = dataClient(login);

    	            // Store the client's data in the session
    	            session.setAttribute("clientDonnees", donneesClient);

    	            // Stocke les informations de l'utilisateur connecté dans la session
    	            session.setAttribute("clientConnecte", true);

    	            // Ajoute l'objet Client à l'attribut de la requête
    				//request.setAttribute("clientAuthentifier", clientAuthentifier);

    				//**données du client
    				donneesClient.setconfirmCourriel(donneesClient.getCourriel());
    				Adresse adresseP = dataAdresse(donneesClient.getIdAdressePersonnelle());

    				Adresse adresseLivraion = dataAdresse(donneesClient.getIdAdresseLivraison());

    				// Ajoute l'objet Client à l'attribut de la requête
    				session.setAttribute("clientDonnees", donneesClient);
    				// Ajoute l'objet adresse personnel à l'attribut de la requête
    				session.setAttribute("clientAdresse", adresseP);

    				// Ajoute l'objet adresse de livraison à l'attribut de la requête
    				if(donneesClient.getIdAdresseLivraison() == donneesClient.getIdAdressePersonnelle()) {
    					session.setAttribute("clientAdresseL", adresseP);
    				} else {
    					session.setAttribute("clientAdresseL", adresseLivraion);
    				}

    				// Définissez la variable isClient dans la portée de la requête
    	            request.setAttribute("isClient", isClient);
    	            // Redirige vers la servlet catalogue
    				RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
    				//RequestDispatcher dispatcher = request.getRequestDispatcher("/client/profilClient.jsp");

    				dispatcher.forward(request, response);
    				// Redirige vers la page de profil après la connexion réussie
    	            //response.sendRedirect(request.getContextPath() + "/client/profil.jsp");

    	        } else {
    	        	 // En cas d'échec de connexion, affiche un message d'erreur sur la page de connexion (login.jsp)
    	            request.setAttribute("errorMessageLogin", "Vos identifiants sont invalides");
    	            request.getRequestDispatcher("/client/seConnecter.jsp").forward(request, response);
    	        }
            }
	}


	/**
	 * Retourne les données du client
	 * @param courriel couriel du client
	 * @return lun objet client
	 * @throws SQLException
	 */
	private Client dataClient(String courriel) throws SQLException {
		Client client = clientDBServices.getClient(courriel);
		return client;
	}

	/**
	 * Retourne les données du client
	 * @param courriel couriel du client
	 * @return lun objet client
	 * @throws SQLException
	 */
	private Adresse dataAdresse(int idAdresse) throws SQLException {
		Adresse adresse = clientDBServices.getAdresseClientbyId(idAdresse);
		return adresse;
	}

	/**
	 *
	 * Gère la tentative de connection  d'un compte client
	 *
	 * @param request La requête HTTP contenant les données de connexion.
	 * @param response La réponse HTTP à renvoyer à l'utilisateur.
	 * @throws IOException
	 */
    private void loginPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	  // Crée un cookie pour stocker les informations de connexion
        Cookie userCookie = new Cookie("username", "valeur_du_cookie");

        // Définit la durée de vie du cookie à un an en secondes
        userCookie.setMaxAge(365 * 24 * 60 * 60); // Un an en secondes

        // Ajoute le cookie à la réponse
        response.addCookie(userCookie);

        // Redirige vers la page d'accueil
        request.getRequestDispatcher("/client/seConnecter.jsp").forward(request, response);
	}

	/**
	 * Récupère le client depuis la base de données en fonction du nom d'utilisateur.
	 *
	 * @param login Le nom d'utilisateur du client.
	 * @return L'objet Client correspondant ou null si non trouvé.
	 */
	private Client getClientFromBDD(String login) {
		// Utilise l'instance clientDBServices pour récupérer la liste des clients depuis la base de données
	    List<Client> clients = null;
	    try {
	        clients = clientDBServices.getClients();
	    } catch (Exception e) {
	        // Gère les éventuelles exceptions lors de la récupération des clients depuis la base de données
	        e.printStackTrace();
	        return null; // En cas d'erreur, retourne null
	    }

	    // Parcourt la liste des clients pour trouver celui correspondant au login donné
	    for (Client client : clients) {
	        if (client.getUsername().equals(login) || client.getCourriel().equals(login)) {
	            return client; // Retourne le client correspondant si trouvé
	        }
	    }

	    return null; // Retourne null si aucun client correspondant n'a été trouvé

	}

	/**
	 * Vérifie si les informations de connexion (login et password) sont valides.
	 *
	 * @param login Le nom d'utilisateur ou l'adresse e-mail du client.
	 * @param password Le mot de passe du client.
	 * @return true si les informations de connexion sont valides, false sinon.
	 * @throws SQLException
	 */
	private boolean loginEstValide(String login, String password) throws SQLException {
		// Utilise l'instance clientDBServices pour vérifier si le login et le password sont valides
	    if (clientDBServices != null) {
	    	// Appelle la méthode checkClientExists du service pour vérifier si le client existe
        	// dans la base de données avec les informations fournies.
	        return clientDBServices.checkClientExists(login, password);
	    } else {
	    	// Si clientDBServices n'est pas initialisé (par exemple, en cas de configuration incorrecte),
	        // retourne false, car la validation ne peut pas être effectuée correctement.
	        return false;
	    }
	}
}

