package org.cegep.gg.controleur;


import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.cegep.gg.modele.Adresse;
import org.cegep.gg.modele.Client;
import org.cegep.gg.modele.Produit;

import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

//mapping du filtre de connextion
//@WebServlet("/conClient")
public class AcceuilServletControleur extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Resource(name="jdbc/sql9641461")
    private DataSource dataSource;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Traiter la demande de déconnexion pour la méthode GET
		//versAcceuil(request, response);
		try {
            Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement();

            // Récupérer les produits selon la catégorie choisie ou non
            String categoryParam = request.getParameter("category");
            String query = "SELECT * FROM Produits";
            List<Object> queryParams = new ArrayList<>(); // To store query parameters

            if (categoryParam != null && !categoryParam.isEmpty()) {
                int categoryId = Integer.parseInt(categoryParam);
                query = "SELECT * FROM Produits WHERE categorie = ?";
                queryParams.add(categoryId);
            }

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            for (int i = 0; i < queryParams.size(); i++) {
                preparedStatement.setObject(i + 1, queryParams.get(i));
            }

            ResultSet resultSet = preparedStatement.executeQuery();

            List<Produit> produits = new ArrayList<>();
            while (resultSet.next()) {
                Produit produit = new Produit();
                produit.setId(resultSet.getInt("id"));
                produit.setNom(resultSet.getString("nom"));
                produit.setImage_url(resultSet.getString("image_url"));
                produits.add(produit);
            }
            request.setAttribute("produits", produits);
            resultSet.close();
            preparedStatement.close();
            statement.close();
            connection.close();

            // Tout envoyer à index.jsp
            //request.getRequestDispatcher("index.jsp").forward(request, response);
            versAcceuil(request, response);
		} catch (Exception e) {
            e.printStackTrace();
        }
    }


	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Traiter la demande de déconnexion pour la méthode POST
		versAcceuil(request, response);
	}


	/**
	 * Faire le processus de la page d'acceuil.
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void versAcceuil(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		// Efface les cookies en les remplaçant par des cookies expirés
	    Cookie[] cookies = request.getCookies();
	    if (cookies != null) {
	        for (Cookie cookie : cookies) {
	            cookie.setMaxAge(365 * 24 * 60 * 60); // Un an en secondes
	            response.addCookie(cookie);
	        }
	    }
	    // Vérifiez si l'utilisateur est connecté en vérifiant la session
        boolean clientConnecte = request.getAttribute("clientConnecte") != null &&
        		(boolean) request.getAttribute("clientConnecte");

	    Client clientDonnees = (Client) request.getAttribute("clientDonnees");
        Adresse adresseClient = (Adresse) request.getAttribute("clientAdresse");
        Adresse adresseLivraion = (Adresse) request.getAttribute("clientAdresseL");

        request.setAttribute("clientDonnees", clientDonnees);
        request.setAttribute("clientAdresse", adresseClient);
        request.setAttribute("clientAdresseL", adresseLivraion);
	    // Redirige vers la page d'accueil: TODO: À modifier pour la page de catalogue plus tard
	    request.getRequestDispatcher("/index.jsp").forward(request, response);

	}


}
