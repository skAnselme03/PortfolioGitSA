package org.cegep.gg.controleur;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
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

@WebServlet(urlPatterns = "/catalogue")
public class CatalogueServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Resource(name="jdbc/sql9641461")
    private DataSource dataSource;

    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement();

            // Récupérer les produits selon la catégorie choisie ou non
            String categoryParam = request.getParameter("category");
            String query = "SELECT * FROM Produits";
            List<Object> queryParams = new ArrayList<>();

            if (categoryParam != null && !categoryParam.isEmpty()) {
                int categoryId = Integer.parseInt(categoryParam);
                query = "SELECT * FROM Produits WHERE categorie = ?";
                queryParams.add(categoryId);
            }

            // Fonction de la barre de recherche
            String searchParam = request.getParameter("search");
            if (searchParam != null && !searchParam.isEmpty()) {
                if (!queryParams.isEmpty()) {
                    query += " AND";
                } else {
                    query += " WHERE";
                }
                query += " nom LIKE ?";
                queryParams.add("%" + searchParam + "%");
            }

            // Create prepared statement
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
                produit.setDescription(resultSet.getString("description"));
                produit.setCategorie(resultSet.getInt("categorie"));
                produit.setPrix(resultSet.getFloat("prix"));
                produit.setImage_url(resultSet.getString("image_url"));
                produit.setResponsable(resultSet.getInt("responsable"));
                produits.add(produit);
            }
            request.setAttribute("produits", produits);
            resultSet.close();

            // Récupérer les catégories pour le menu de gauche
            resultSet = statement.executeQuery("SELECT * FROM Categorie");
            List<Categorie> categories = new ArrayList<>();
            while (resultSet.next()) {
                Categorie categorie = new Categorie();
                categorie.setId(resultSet.getInt("id"));
                categorie.setNom(resultSet.getString("nom"));
                categorie.setResponsable(resultSet.getInt("responsable"));
                categories.add(categorie);
            }
            request.setAttribute("categories", categories);

            preparedStatement.close();
            statement.close();
            connection.close();

            // Tout envoyer à catalogue.jsp
            request.getRequestDispatcher("catalogue.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}