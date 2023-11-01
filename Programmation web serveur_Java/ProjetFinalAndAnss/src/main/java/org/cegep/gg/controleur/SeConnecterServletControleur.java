package org.cegep.gg.controleur;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import jakarta.annotation.Resource;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.cegep.gg.modele.Client;
import org.cegep.gg.service.ClientDBServices;

public class SeConnecterServletControleur extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private ClientDBServices clientDBServices;
	

    @Resource(name="jdbc/sql9641461")
	private DataSource dataSource;
	
    @Override
	public void init() throws ServletException {
    	super.init();
		
		try {
			clientDBServices = new ClientDBServices(dataSource);
			
			//TODO: LOGGER.info("init() - OK");
			
		}
		catch (Exception exc) {
			//TODO: LOGGER.error("init() - erreur = " + exc.getMessage());
			throw new ServletException(exc);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			
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

	        // Valide la connexion en utilisant la méthode validateLogin
	        boolean loginValide = loginEstValide(login, password);
	        
	        if (loginValide) {
	        	// Récupère le client authentifié en utilisant la méthode getClientFromDatabase
	            Client clientAuthentifier = getClientFromBDD(login);
	                   				
	            // Ajoute l'objet Client à l'attribut de la requête	     
				request.setAttribute("clientAuthentifier", clientAuthentifier);
	            // Redirige vers la servlet catalogue   
				RequestDispatcher dispatcher = request.getRequestDispatcher("/catalogue.jsp");
				dispatcher.forward(request, response);
	        } else {
	        	 // En cas d'échec de connexion, affiche un message d'erreur sur la page de connexion (login.jsp)	            
	            request.setAttribute("errorMessage", "Invalid username or password");
	            request.getRequestDispatcher("/seConnecter.jsp").forward(request, response);
	        }		
		
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
	        if (client.getUsername().equals(login)) {
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
	 */
	private boolean loginEstValide(String login, String password) {
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
