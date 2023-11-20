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

import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(urlPatterns = "/gestionCategorie")
public class GererCategorieServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Resource(name = "jdbc/sql9641461")
    private DataSource dataSource;

    public GererCategorieServlet() {
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
    	    	List<Categorie> categories = recupererToutesCategoriesDB();
    	        request.setAttribute("categories", categories);
    	        request.getRequestDispatcher("/admin/gestionCategorie.jsp").forward(request, response);
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
            int responsable = Integer.parseInt(request.getParameter("responsable"));
            creerNouvelleCategorie(nom, responsable);
        } else if ("modify".equals(action)) {
            int id = Integer.parseInt(request.getParameter("categorieId"));
            String nom = request.getParameter("nom");
            int responsable = Integer.parseInt(request.getParameter("responsable"));
            modifierCategorie(id, nom, responsable);
        } else if ("delete".equals(action)) {
            int categoryId = Integer.parseInt(request.getParameter("categorieId"));
            supprimerCategorie(categoryId);
        }

        response.sendRedirect(request.getContextPath() + "/gestionCategorie");
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

    private void creerNouvelleCategorie(String nom, int responsable) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement("INSERT INTO Categorie (nom, responsable) VALUES (?, ?)")) {

            statement.setString(1, nom);
            statement.setInt(2, responsable);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void modifierCategorie(int id, String nom, int responsable) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement("UPDATE Categorie SET nom=?, responsable=? WHERE id=?")) {

            statement.setString(1, nom);
            statement.setInt(2, responsable);
            statement.setInt(3, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void supprimerCategorie(int id) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement("DELETE FROM Categorie WHERE id=?")) {

            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}