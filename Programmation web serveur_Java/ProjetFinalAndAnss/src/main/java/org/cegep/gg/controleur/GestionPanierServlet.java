package org.cegep.gg.controleur;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.cegep.gg.modele.Adresse;
import org.cegep.gg.modele.Client;
import org.cegep.gg.modele.DateUtil;
import org.cegep.gg.modele.EnvoieEmail;
import org.cegep.gg.modele.PanierCookie;
import org.cegep.gg.modele.PanierItem;
import org.cegep.gg.service.PanierDBServices;

import jakarta.annotation.Resource;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


@WebServlet("/panier")
public class GestionPanierServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static final String CHEMIN_PAGE_PANIER = "/panier.jsp";
    private static final String CHEMIN_PAGE_CONNEXION = "/client/seConnecter.jsp"; // Page de connexion
    private static final String CHEMIN_PAGE_ACCEUIL = "/index.jsp"; // Page d'acceuil du client
    private static final String CHEMIN_PAGE_ERREUR = "client/erreur.jsp"; // Page d'acceuil du client
    private static final String CHEMIN_PAGE_COMMANDE = "/client/commande.jsp"; // Page de commande

    @Resource(name="jdbc/sql9641461")
	private DataSource dataSource;
	private PanierDBServices panierDBServices;

	 @Override
    public void init() throws ServletException {
        super.init();
        try {
	        // Initialisez panierDBServices avec la source de données (dataSource)
	        panierDBServices = new PanierDBServices(dataSource);
			//LOGGER.info("init() - OK");

		}
		catch (Exception exc) {
			//LOGGER.error("init() - erreur = " + exc.getMessage());
			throw new ServletException(exc);
		}
    }
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 try {
			 	// Vérifier si une session existe
		        HttpSession session = request.getSession(false);

		        // Récupérez le contenu du panier à partir du cookie
		        Cookie panierCookie = getPanierCookie(request);

		        List<PanierItem> panierItems = new ArrayList<>();

		        if (panierCookie != null) {
		            // Le panier existe déjà dans les cookies, récupérez les données
		            panierItems = PanierCookie.getPanierDeCookie(panierCookie);
		        }

		        // Transmettez les éléments du panier à la JSP
		        if(panierItems == null) {
	            	 // Définissez un message d'erreur
	                request.setAttribute("msg_PanierVide", "Le panier est vide!");
	                request.setAttribute("estPanier", false); // Le panier est vide, donc estPanier est défini à false
	            }else {
		            // Définissez le panier comme attribut de la requête pour l'afficher dans panier.jsp
		            request.setAttribute("LISTE_PANIER", panierItems);
		            request.setAttribute("estPanier", true);
	            }

		        //récupérer les données de session du client
		        if (session != null && session.getAttribute("clientConnecte") != null &&
                		(boolean)session.getAttribute("clientConnecte")) {

	        		// Calculer le prix total des produits dans le panier
	    	    	double prixTotal = 0.0;
	        	 	if (panierCookie != null) {
		      	       panierItems = PanierCookie.getPanierDeCookie(panierCookie);
			      	   for (PanierItem item : panierItems) {
			      	       	prixTotal += item.getPrixTotal();
			      	   }
		      	    }
		    	    //récupérer les données du client
		    	    Client clientDonnees = (Client) request.getAttribute("clientDonnees");
		            Adresse adresseClient = (Adresse) request.getAttribute("clientAdresse");
		            Adresse adresseLivraion = (Adresse) request.getAttribute("clientAdresseL");

		    		// set un attribut prix total à envoyer vers la page commande
		            // Stocke les informations de l'utilisateur connecté dans la session
		            request.setAttribute("clientConnecte", true);

		            // dispatcher ces données vers la page de commande

		            request.setAttribute("PRIX_TOTAL", prixTotal);
		            request.setAttribute("clientDonnees", clientDonnees);
		            request.setAttribute("clientAdresse", adresseClient);
		            request.setAttribute("clientAdresseL", adresseLivraion);
		        }
		        // Redirigez vers la page du panier
		        RequestDispatcher dispatcher = request.getRequestDispatcher(CHEMIN_PAGE_PANIER);
		        dispatcher.forward(request, response);



       } catch (Exception ex) {
		    throw new ServletException(ex);
		}

	}


	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {

			// Récupérer les valeurs du formulaire de connexion
			String action = request.getParameter("action");

			if (action == null) {
				action = "PANIER";
			}

			switch (action) {

			case "PANIER":
				doGet(request, response);
				break;
			case "AJOUT":
				gererAjoutAuPanier(request, response);
				break;
			case "MODIFIER":
				modifierItemPanier(request, response);
				break;
			case "SUPPRIMER":
				supprimerItemPanier(request, response);
				break;
			case "ACHETER":
				// Vérifier si une session est active avant d'acheter
                HttpSession session = request.getSession(false);
                if (session != null && session.getAttribute("clientConnecte") != null &&
                		(boolean)session.getAttribute("clientConnecte")) {
                    // Session active, procéder à l'achat
                    acheterProduit(request, response);
                } else {
                    // Aucune session active, rediriger vers la page de connexion
                    response.sendRedirect(request.getContextPath() + CHEMIN_PAGE_CONNEXION);
                }
				break;
			case "COMMANDER":
				commanderProduit(request, response);
				break;
			default:
				doGet(request, response);
			}

		}catch(Exception ex) {
			//LOGGER.error("doGet - erreur = " + ex.getMessage());
			throw new ServletException(ex);
		}

	}


	/**
	 * Gère la commande passer par le client
	 *
	 * @param request  La requête HTTP contenant les informations du produit à ajouter.
	 * @param response La réponse HTTP pour mettre à jour le cookie du panier.
	 * @throws Exception si une erreur survient pendant le traitement.
	 */
	private void commanderProduit(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		//récupérer le panier cookie,
		//récupérer l'adresse de messagerie du client
		//envoyer un mail de confirmation d'achat avec le panier cookie lister dans le message
		// dans l'email de confirmation, ajouter les 4 dernier chiffre de la carte dans le message comme suit:
		// "paiment effectué par cette carte [numéro de la carte] (format : xxxx xxxx 5555).
		//supprimer le panier de cookie
		//redirection vers la page principale avec message
	    // Récupérer le panier depuis le cookie
	    Cookie panierCookie = getPanierCookie(request);
	    List<PanierItem> panierItems = new ArrayList<>();

	    if (panierCookie != null) {
	        panierItems = PanierCookie.getPanierDeCookie(panierCookie);
	    }

	    // Récupérer l'adresse e-mail du client depuis la session
	    HttpSession session = request.getSession(false);

	    String emailClient = "";
		String carteAchat = request.getParameter("carteCredit");
		 Client clientDonnees = null;
	    if (session != null && session.getAttribute("clientConnecte") != null
	            && (boolean) session.getAttribute("clientConnecte")) {
	        clientDonnees = (Client) session.getAttribute("clientDonnees");
	        emailClient = clientDonnees.getCourriel();
	    }


	    // Créer un objet EnvoieEmail
        EnvoieEmail emailConfirmation = new EnvoieEmail(emailClient, panierItems, "Confirmation de commande");
        boolean estEnvoyer = emailConfirmation.envoyerEmailConfirmation(panierItems, carteAchat);

	    //date du jour
	    String dateDuJour = DateUtil.getDateDuJour("yyyy-MM-dd");
	    //enregistrer mon panier dans la bdd
        panierDBServices.ajouterPanier(panierItems, clientDonnees.getidClient(), dateDuJour );


        if(estEnvoyer) {
        	//supprimer le panier sauvegarder dans la cookie
        	Cookie panierCookies = new Cookie(PanierCookie.NOM_PANIER_COOKIE, "");
            panierCookies.setMaxAge(0);
            panierCookies.setPath("/"); // Assurez-vous que le chemin est le même que celui utilisé pour créer le cookie

            // Ajoutez le cookie supprimé à la réponse pour qu'il soit supprimé du navigateur du client
            response.addCookie(panierCookies);

            request.setAttribute("CONFIRMATION_COMMANDE", true);
            // Redirigez l'utilisateur vers la page de commande
            request.getRequestDispatcher(CHEMIN_PAGE_ACCEUIL).forward(request, response);
        }else {

            request.setAttribute("CONFIRMATION_COMMANDE", false);
            // Vous pouvez rediriger vers une page d'erreur si nécessaire
            request.getRequestDispatcher(CHEMIN_PAGE_ERREUR).forward(request, response);
        }

	}


	/**
	 * Gère l'achat des produits dans le panier.
	 *
	 * @param request  La requête HTTP contenant les informations du produit à ajouter.
	 * @param response La réponse HTTP pour mettre à jour le cookie du panier.
	 * @throws Exception si une erreur survient pendant le traitement.
	 */
	private void acheterProduit(HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();

        // récupérer le panier cookie
		 // Récupérer le panier cookie
	    Cookie panierCookie = getPanierCookie(request);

	    // Calculer le prix total des produits dans le panier
	    double prixTotal = 0.0;
	    List<PanierItem> panierItems = new ArrayList<>();

	    if (panierCookie != null) {
	        panierItems = PanierCookie.getPanierDeCookie(panierCookie);
	        for (PanierItem item : panierItems) {
	            prixTotal += item.getPrixTotal();
	        }
	    }
	    //récupérer les données du client
	    Client clientDonnees = (Client) session.getAttribute("clientDonnees");
        Adresse adresseClient = (Adresse) session.getAttribute("clientAdresse");
        Adresse adresseLivraion = (Adresse) session.getAttribute("clientAdresseL");

		// set un attribut prix total à envoyer vers la page commande
        // Stocke les informations de l'utilisateur connecté dans la session
        request.setAttribute("clientConnecte", true);

        // dispatcher ces données vers la page de commande

        request.setAttribute("PRIX_TOTAL", prixTotal);
        request.setAttribute("clientDonnees", clientDonnees);
        request.setAttribute("clientAdresse", adresseClient);
        request.setAttribute("clientAdresseL", adresseLivraion);

		//récupère les données du client reçus par la page de connexion et le set pour la page de commande
		//rediriger vers la page de commande

        // Redirigez l'utilisateur vers la page de commande
        request.getRequestDispatcher(CHEMIN_PAGE_COMMANDE).forward(request, response);

	}


	/**
	 * Gère l'ajout d'un produit dans le panier.
	 *
	 * @param request  La requête HTTP contenant les informations du produit à ajouter.
	 * @param response La réponse HTTP pour mettre à jour le cookie du panier.
	 * @throws Exception si une erreur survient pendant le traitement.
	 */
	private void gererAjoutAuPanier(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// Récupérez les informations du produit depuis les paramètres de la requête
        int produitId = Integer.parseInt(request.getParameter("produitId"));
        String produitNom = request.getParameter("produitNom");
        double produitPrix = Double.parseDouble(request.getParameter("produitPrix"));

        // Vérifier si le panier existe déjà dans les cookies
        Cookie panierCookie = getPanierCookie(request);

        List<PanierItem> panierItems;

        if (panierCookie != null) {
            // Le panier existe déjà dans les cookies, récupérez les données
            panierItems = PanierCookie.getPanierDeCookie(panierCookie);
        } else {
            // Créez un nouveau panier
            panierItems = new ArrayList<>();
        }

        boolean produitExiste = false;

        // Parcourez le panier pour vérifier si le produit existe déjà
        for (PanierItem item : panierItems) {
            if (item.getIdProduit() == produitId) {
                // Le produit existe déjà dans le panier, augmentez simplement sa quantité
                item.setQuantite(item.getQuantite() + 1);
                produitExiste = true;
                break;
            }
        }

        // Le produit n'existe pas dans le panier, ajoutez-le
        if (!produitExiste) {
            PanierItem item = new PanierItem(produitId, produitNom, 1, produitPrix);
            panierItems.add(item);
        }

        // Enregistrer le panier dans les cookies
        Cookie newPanierCookie = PanierCookie.creerCookiePanier(panierItems);
        // Mettre à jour le cookie panier
        response.addCookie(newPanierCookie);

        // Rediriger vers la page du panier
        //response.sendRedirect(request.getContextPath() + CHEMIN_PAGE_PANIER);

        request.setAttribute("LISTE_PANIER", panierItems);
        request.setAttribute("estPanier", true);
        // Redirige vers la servlet catalogue
		RequestDispatcher dispatcher = request.getRequestDispatcher(CHEMIN_PAGE_PANIER);

		dispatcher.forward(request, response);

	}

	/**
	 * Gèrer la quantité d'un produit dans le panier.
	 *
	 * @param request  La requête HTTP contenant les informations du produit à ajouter.
	 * @param response La réponse HTTP pour mettre à jour le cookie du panier.
	 * @throws Exception si une erreur survient pendant le traitement.
	 */
	private void modifierItemPanier(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		 // Récupérez les valeurs du formulaire
        int produitId = Integer.parseInt(request.getParameter("produitId"));
        int nouvelleQuantite = Integer.parseInt(request.getParameter("produitQuantite"));

        // Récupérez le panier à partir du cookie
        Cookie panierCookie = getPanierCookie(request);
        List<PanierItem> panierItems = new ArrayList<>();

        if (panierCookie != null) {
            panierItems = PanierCookie.getPanierDeCookie(panierCookie);
        }

        // Parcourez le panier pour trouver le produit à modifier
        for (PanierItem item : panierItems) {
            if (item.getIdProduit() == produitId) {
                item.setQuantite(nouvelleQuantite);
                break;
            }
        }

        // Mettez à jour le cookie panier avec les nouvelles données
        // Enregistrer le panier dans les cookies
        Cookie newPanierCookie = PanierCookie.creerCookiePanier(panierItems);
        // Mettre à jour le cookie panier
        response.addCookie(newPanierCookie);

        request.setAttribute("LISTE_PANIER", panierItems);
        request.setAttribute("estPanier", true);
        // Redirige vers la servlet catalogue
		RequestDispatcher dispatcher = request.getRequestDispatcher(CHEMIN_PAGE_PANIER);

		dispatcher.forward(request, response);
	}

	/**
	 * Gèrer la supression d'un produit dans le panier.
	 *
	 * @param request  La requête HTTP contenant les informations du produit à ajouter.
	 * @param response La réponse HTTP pour mettre à jour le cookie du panier.
	 * @throws Exception si une erreur survient pendant le traitement.
	 */
	private void supprimerItemPanier(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// Récupérez l'identifiant du produit à supprimer
        int produitId = Integer.parseInt(request.getParameter("produitId"));

        // Récupérez le panier à partir du cookie
        Cookie panierCookie = getPanierCookie(request);
        List<PanierItem> panierItems = new ArrayList<>();

        if (panierCookie != null) {
            panierItems = PanierCookie.getPanierDeCookie(panierCookie);
        }

        // Parcourez le panier pour trouver le produit à supprimer
        PanierItem itemToRemove = null;
        for (PanierItem item : panierItems) {
            if (item.getIdProduit() == produitId) {
                itemToRemove = item;
                break;
            }
        }

        // Supprimez le produit du panier
        if (itemToRemove != null) {
            panierItems.remove(itemToRemove);
        }

        //TODO: VIDER LE COOKIE AVANT DE RECRÉ UN NOUVEAU PANIER DE COOKIE

        // Mettez à jour le cookie panier avec les nouvelles données
        // Enregistrer le panier dans les cookies
        Cookie newPanierCookie = PanierCookie.creerCookiePanier(panierItems);
        // Mettre à jour le cookie panier
        response.addCookie(newPanierCookie);

        request.setAttribute("LISTE_PANIER", panierItems);
        request.setAttribute("estPanier", true);
        // Redirige vers la servlet catalogue
		RequestDispatcher dispatcher = request.getRequestDispatcher(CHEMIN_PAGE_PANIER);

		dispatcher.forward(request, response);
	}


	/**
	 * Récupérer le panier enregistrer dans le cookie
	 * @param request La requête HTTP contenant les données de connexion.
	 * @return panierCookie
	 * @throws ServletException
	 * @throws IOException
	 */
	private Cookie getPanierCookie(HttpServletRequest request) throws ServletException, IOException {
		Cookie[] cookies = request.getCookies();
        Cookie panierCookie = null;

        if (cookies != null) {
            for (Cookie cookie : cookies) {
            	 if (PanierCookie.NOM_PANIER_COOKIE.equals(cookie.getName())) {
                       panierCookie = cookie;
                    break;
                }
            }
        }
        return panierCookie;
	}

	//TODO: METTRE UNE MÉTHODE DE SUPPRESSION DU COOKIE "NOM_PANIER_COOKIE"
}
