package org.cegep.gg.controleur;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.cegep.gg.modele.Categorie;
import org.cegep.gg.modele.Produit;

import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(urlPatterns = "/gestionProduit")
public class GererProduitServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Resource(name = "jdbc/sql9641461")
    private DataSource dataSource;

    public GererProduitServlet() {
        super();
    }

    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	HttpSession session = request.getSession(false); // On met faux pour éviter de créer une nouvelle session si une existe déjà

    	if (session != null) {
    	    // Récupère la valeur de l'attribut isAdmin
    	    Boolean isAdmin = (Boolean) session.getAttribute("isAdmin");
    	    if (isAdmin != null && isAdmin) {
    	        // L'usager est un admin
    	    	List<Produit> produits = recupererTousProduitsDB();
    	        List<Categorie> categories = recupererToutesCategoriesDB();
    	        request.setAttribute("produits", produits);
    	        request.setAttribute("categories", categories);
    	        request.getRequestDispatcher("/admin/gestionProduit.jsp").forward(request, response);
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
        String action = request.getParameter("action");

        if ("create".equals(action)) {
        	String nom = request.getParameter("nom");
            int categorie = Integer.parseInt(request.getParameter("categorie"));
            String description = request.getParameter("description");
            float prix = Float.parseFloat(request.getParameter("prix"));
            String image = request.getParameter("image");
            int responsable = Integer.parseInt(request.getParameter("responsable"));
            creerNouveauProduit(nom, categorie, description, prix, image, responsable);
            // Fetch the list of products and categories again
            List<Produit> produits = recupererTousProduitsDB();
            List<Categorie> categories = recupererToutesCategoriesDB();
            // Set the attributes
            request.setAttribute("produits", produits);
            request.setAttribute("categories", categories);
            // Forward the request to the JSP page
            request.getRequestDispatcher("/admin/gestionProduit.jsp").forward(request, response);
        } else if ("modify".equals(action)) {
            int id = Integer.parseInt(request.getParameter("produitId"));
            String nom = request.getParameter("nom");
            int categorie = Integer.parseInt(request.getParameter("categorie"));
            String description = request.getParameter("description");
            float prix = Float.parseFloat(request.getParameter("prix"));
            String image = request.getParameter("image");
            int responsable = Integer.parseInt(request.getParameter("responsable"));
            modifierProduit(id, nom, categorie, description, prix, image, responsable);
        } else if ("delete".equals(action)) {
            int produitId = Integer.parseInt(request.getParameter("produitId"));
            supprimerProduit(produitId);
        }

        response.sendRedirect(request.getContextPath() + "/gestionProduit");
    }

    private List<Produit> recupererTousProduitsDB() {
        List<Produit> produits = new ArrayList<>();

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT p.id, p.nom, p.categorie, c.nom AS categorie_nom, p.description, p.prix, p.image_url, p.responsable FROM Produits p INNER JOIN Categorie c ON p.categorie = c.id");
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Produit produit = new Produit();
                produit.setId(resultSet.getInt("id"));
                produit.setNom(resultSet.getString("nom"));
                produit.setCategorie(resultSet.getInt("categorie"));
                produit.setCategorieNom(resultSet.getString("categorie_nom")); // Add a setter for category name
                produit.setDescription(resultSet.getString("description"));
                produit.setPrix(resultSet.getFloat("prix"));
                produit.setImage_url(resultSet.getString("image_url"));
                produit.setResponsable(resultSet.getInt("responsable"));
                produits.add(produit);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return produits;
    }

    private List<Categorie> recupererToutesCategoriesDB() {
        List<Categorie> categories = new ArrayList<>();

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM Categorie");
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Categorie categorie = new Categorie();
                categorie.setId(resultSet.getInt("id"));
                categorie.setNom(resultSet.getString("nom"));
                categorie.setResponsable(resultSet.getInt("responsable"));
                categories.add(categorie);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return categories;
    }

    private void creerNouveauProduit(String nom, int categorie, String description, float prix, String image, int responsable) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "INSERT INTO Produits (nom, categorie, description, prix, image_url, responsable) VALUES (?, ?, ?, ?, ?, ?)")) {

            statement.setString(1, nom);
            statement.setInt(2, categorie);
            statement.setString(3, description);
            statement.setFloat(4, prix);
            statement.setString(5, image);
            statement.setInt(6, responsable);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void modifierProduit(int id, String nom, int categorie, String description, float prix, String image, int responsable) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "UPDATE Produits SET nom=?, categorie=?, description=?, prix=?, image_url=?, responsable=? WHERE id=?")) {

            statement.setString(1, nom);
            statement.setInt(2, categorie);
            statement.setString(3, description);
            statement.setFloat(4, prix);
            statement.setString(5, image);
            statement.setInt(6, responsable);
            statement.setInt(7, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void supprimerProduit(int id) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement("DELETE FROM Produits WHERE id=?")) {

            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}