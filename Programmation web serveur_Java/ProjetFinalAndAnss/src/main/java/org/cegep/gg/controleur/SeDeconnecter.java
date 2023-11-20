package org.cegep.gg.controleur;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


public class SeDeconnecter extends HttpServlet {
	private static final long serialVersionUID = 1L;

	  /**
     * Traite la requête GET.
     */
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Traiter la demande de déconnexion pour la méthode GET
        deconnection(request, response);
    }

    /**
     * Traite la requête POST.
     */
    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Traiter la demande de déconnexion pour la méthode POST
        deconnection(request, response);
    }

	 /**
     * Faire le processus de deconnection.
     *
     * @param request La requête HTTP.
     * @param response La réponse HTTP.
     * @throws ServletException En cas d'erreur spécifique à la servlet.
     * @throws IOException En cas d'erreur d'entrée/sortie.
     */
    private void deconnection(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	// Récupère la session si elle existe
        HttpSession session = request.getSession(false);

        // Vérifie si la session existe
        if (session != null) {
            // Invalide la session (déconnexion)
            session.invalidate();
        }
        // Effacer les attributs de session ou de requête associés à l'authentification
        //request.removeAttribute("clientAuthentifier");

        // Rediriger vers la page d'accueil
        response.sendRedirect("index.jsp");
    }

}
