package org.cegep.gg.controleur;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;


public class SeDeconnecter extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	  /**
     * Traite la requête GET.
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Traiter la demande de déconnexion pour la méthode GET
        deconnection(request, response);
    }

    /**
     * Traite la requête POST.
     */
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
        // Effacer les attributs de session ou de requête associés à l'authentification
        request.removeAttribute("clientAuthentifier");

        // Rediriger vers la page d'accueil
        response.sendRedirect("index.jsp");
    }

}
