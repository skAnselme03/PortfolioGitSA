package org.cegep.gg.controleur;
import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.cegep.gg.modele.Produit;
import org.cegep.gg.modele.Categorie;

@WebServlet(urlPatterns = "/catalogue")
public class CatalogueServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    @Resource(name="jdbc/sql9641461")
	private DataSource dataSource;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            
            // Récupérer les produits selon la catégorie choisie ou non
            String categoryParam = request.getParameter("category");
            String query = "SELECT * FROM Produits";
            if (categoryParam != null && !categoryParam.isEmpty()) {
                int categoryId = Integer.parseInt(categoryParam);
                query = "SELECT * FROM Produits WHERE categorie = " + categoryId;
            }
            
            ResultSet resultSet = statement.executeQuery(query);

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
            
            statement.close();
            connection.close();
            
            // Tout envoyer à catalogue.jsp
            request.getRequestDispatcher("catalogue.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}