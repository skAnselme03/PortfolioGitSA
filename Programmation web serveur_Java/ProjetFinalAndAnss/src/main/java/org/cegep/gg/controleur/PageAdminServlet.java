package org.cegep.gg.controleur;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(urlPatterns = "/pageAdmin")
public class PageAdminServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	HttpSession session = request.getSession(false); // On met faux pour éviter de créer une nouvelle session si une existe déjà

    	if (session != null) {
    	    // Récupère la valeur de l'attribut isAdmin
    	    Boolean isAdmin = (Boolean) session.getAttribute("isAdmin");
    	    if (isAdmin != null && isAdmin) {
    	        // L'usager est un admin
    	    	request.getRequestDispatcher("/admin/pageAdmin.jsp").forward(request, response);
    	    } else {
    	        // L'usager n'est pas un admin ou il n'y a pas d'attribut isAdmin
    	    	request.getRequestDispatcher("/erreur404.jsp").forward(request, response);	// Page d'erreur
    	    }
    	} else {
    	    // La session n'est pas valide ou a expiré
    		request.getRequestDispatcher("/erreur404.jsp").forward(request, response);	// Page d'erreur
    	}
    }

    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String buttonClicked = request.getParameter("category");

        if ("Gestion de catégorie".equals(buttonClicked)) {
            // Vérifie que l'utilisateur a le rôle "administrateur"
            if (request.isUserInRole("administrateur")) {
                request.getRequestDispatcher(request.getContextPath() + "/admin/gestionCategorie.jsp").forward(request, response);
            } else {
                response.getWriter().println("Accès refusé. Vous n'avez pas le rôle autorisé.");
            }
        } else if ("Gestion de produit".equals(buttonClicked)) {
            // Vérifie que l'utilisateur a le rôle "administrateur"
            if (request.isUserInRole("administrateur")) {
                request.getRequestDispatcher(request.getContextPath() + "/admin/gestionProduit.jsp").forward(request, response);
            } else {
                response.getWriter().println("Accès refusé. Vous n'êtes pas administrateur.");
            }
        } else {
            response.getWriter().println("Je ne sais pas comment vous avez fait pour obtenir cette erreur. Bravo!");
        }
    }
}
