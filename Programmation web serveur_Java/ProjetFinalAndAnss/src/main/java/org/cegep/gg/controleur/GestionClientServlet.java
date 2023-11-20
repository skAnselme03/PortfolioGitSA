package org.cegep.gg.controleur;

import java.io.IOException;

import javax.sql.DataSource;

import org.cegep.gg.modele.Adresse;
import org.cegep.gg.modele.Client;
import org.cegep.gg.service.ClientDBServices;

import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/profilClient")
public class GestionClientServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ClientDBServices clientDBServices;
	private static final String CHEMIN_PAGE_PROFIL_CLIENT = "/client/profilClient.jsp";
    private static final String CHEMIN_PAGE_CONNEXION = "/client/seConnecter.jsp"; // Page de connexion
    private static final String CHEMIN_PAGE_ERREUR404 = "/erreur404.jsp"; // Page d'acceuil du client

    /**
     * @see HttpServlet#HttpServlet()
     */
    public GestionClientServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

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
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			//TODO: TEST
        	/*String nomUtilisateur;
        	Principal test = request.getUserPrincipal();
    		if (request.getUserPrincipal() != null) {
				nomUtilisateur = request.getUserPrincipal().getName();
				System.out.println("Get User Principal Name = " + request.getUserPrincipal().getName()); // username
				System.out.println("Is User In Role = " + request.isUserInRole("client")); // role
		    }*/

			//TODO: FIN TEST

			HttpSession session = request.getSession(false); // On met faux pour éviter de créer une nouvelle session si une existe déjà
            // Récupérez les rôles de l'utilisateur depuis la session
            //boolean isClient = request.isUserInRole("client");

	        // Vérifiez si l'utilisateur est connecté en vérifiant la session
	        //boolean clientConnecte = request.getAttribute("clientConnecte") != null && (boolean) request.getAttribute("clientConnecte");
			Boolean clientConnecte = (Boolean) session.getAttribute("clientConnecte");
	        Client clientDonnees = (Client) request.getAttribute("clientDonnees");
			//String action = request.getParameter("action");
			 //if (action != null) {

				//switch (action) {

				//	case "CHARGER":

				       // boolean clientConnecte = request.getSession().getAttribute("clientConnecte") != null
				             //   && (boolean) request.getSession().getAttribute("clientConnecte");

		        if(clientConnecte) {
		        	profilClient(request, response);
		        }else {
		        	 // L'utilisateur n'est pas connecté,
    	        	 // En cas d'échec de connexion,redirigez-le vers la page de connexion avec un message d'erreur
    	            request.setAttribute("errorMessageLogin", "Vous devez vous connecter pour accéder à cette page");
    	            request.getRequestDispatcher(CHEMIN_PAGE_CONNEXION).forward(request, response);
		        }
				//	break;
				//}
			 //}
		}
		catch (Exception exc) {
			//LOGGER.error("init() - erreur = " + exc.getMessage());
			//throw new ServletException(exc);
			exc.printStackTrace();
            response.sendRedirect(CHEMIN_PAGE_ERREUR404);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);

	}


	/**
	 * Afficher les données du profil client
	 * @param request   L'objet HttpServletRequest pour récupérer les données de la requête.
	 * @param response  L'objet HttpServletResponse pour gérer la réponse.
	 * @throws ServletException
	 * @throws IOException
	 */
	private void profilClient(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		 //Récupérez les données de connexion, par exemple, le nom d'utilisateur ou l'adresse e-mail.
		//Récupérez ces données depuis la requête
        Client clientDonnees = (Client) request.getAttribute("clientDonnees");
        Adresse adresseClient = (Adresse) request.getAttribute("clientAdresse");
        Adresse adresseLivraion = (Adresse) request.getAttribute("clientAdresseL");

        // Stocke les informations de l'utilisateur connecté dans la session
        request.setAttribute("clientConnecte", true);

        //dispatcher ces données vers la page de profil
        //request.setAttribute("login", login);

        request.setAttribute("clientDonnees", clientDonnees);
        request.setAttribute("clientAdresse", adresseClient);
        request.setAttribute("clientAdresseL", adresseLivraion);

        // Redirigez l'utilisateur vers la page de profil
        request.getRequestDispatcher(CHEMIN_PAGE_PROFIL_CLIENT).forward(request, response);
	}

}
