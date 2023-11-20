package org.cegep.gg.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.sql.DataSource;

import org.cegep.gg.modele.PanierItem;

public class PanierDBServices {
	private DataSource dataSource;

	private ClientDBServices clientDBServices;

	public PanierDBServices(DataSource theDataSource) {
		dataSource = theDataSource;
		clientDBServices = new ClientDBServices(dataSource);
	}

	/**
	 * Ferme les ressources de base de données (connexion, déclaration,
	 * préparation de la déclaration et résultat) de manière sécurisée.
	 *
	 * @param connection La connexion à la base de données.
	 * @param statement La déclaration standard.
	 * @param preparedStatement La préparation de déclaration.
	 * @param resultSet Le résultat de la requête.
	 */
	private void close(Connection connection, Statement statement,PreparedStatement preparedStatement, ResultSet resultSet) {

		try {
			 // Fermeture du résultat de la requête s'il est ouvert
	        if (resultSet != null) {
	            resultSet.close();
	        }

	        // Fermeture de la déclaration standard s'il est ouvert
	        if (statement != null) {
	            statement.close();
	        }

	        // Fermeture de la connexion à la base de données si elle est ouverte
	        if (connection != null) {
	            connection.close();
	        }
	    } catch (Exception exc) {
	        // Gestion des exceptions lors de la fermeture des ressources
	        exc.printStackTrace();
	    }
	}

	public boolean ajouterPanier(List<PanierItem> panierItems, int idClient, String dateAchat)
			throws SQLException {
		Connection connection = null;
	    PreparedStatement panierStatement = null;
	    ResultSet resultSet = null;
	    try {
			//valider que l'id du client existe
			boolean idClientExiste = clientDBServices.clientExisteById(idClient);
			if (!idClientExiste) {
                // Gérer le cas où l'id du client n'existe pas
                return false;
            }
			// valide que l'id du produit existe dans la bdd
			for (PanierItem panierItem : panierItems) {
                int idProduit = panierItem.getIdProduit();
                boolean idProduitExiste = produitExisteById(idProduit);

                if (!idProduitExiste) {
                    // Gérer le cas où l'id du produit n'existe pas
                    return false;
                }
			}
			//ajouter les items du panier dans la bdd
			//panierItems contient, l'id du produit,la quantité et le prix unitaire du produit
			//la date d'achat sera fournis en paramètre

			connection = dataSource.getConnection();
			 // Définir la requête d'insertion pour la table Panier
	        String requeteInsertionPanier = "INSERT INTO Panier (idClient, idProduit, "
	        								+ "quantite, prixUnitaire, dateAchat) VALUES (?, ?, ?, ?, ?)";
	        panierStatement = connection.prepareStatement(requeteInsertionPanier);

	        // Parcourir la liste des éléments du panier et les insérer dans la table Panier
	        for (PanierItem panierItem : panierItems) {
	            int idProduit = panierItem.getIdProduit();
	            int quantite = panierItem.getQuantite();
	            float prixUnitaire = (float) panierItem.getPrixUnitaire();

	            // Lier les valeurs aux paramètres de la requête préparée
	            panierStatement.setInt(1, idClient);
	            panierStatement.setInt(2, idProduit);
	            panierStatement.setInt(3, quantite);
	            panierStatement.setFloat(4, prixUnitaire);
	            panierStatement.setString(5, dateAchat);

	            // Exécution de la requête SQL d'insertion
	            //panierStatement.execute();  // Exécuter la requête d'insertion
	            int rowsAffected = panierStatement.executeUpdate();

	            if (rowsAffected != 1) {
	                // Gérer le cas où l'insertion a échoué pour un produit
	                return false;
	            }
	        }

	        // Si toutes les insertions réussissent, retournez true
	        return true;

		}finally {
	        close(connection, null, panierStatement, resultSet);
			}
	    }



	/**
	 * Vérifie qu'un produit existe par son id
	 * @param idProduit l'identification du produit
	 * @return vrai si produit existe, faux sinon
	 * @throws SQLException
	 */
	public boolean produitExisteById(int idProduit) throws SQLException {
		Connection connection = null;
	    PreparedStatement produitPrepStatement = null;
	    ResultSet resultSet = null;


        try{
        	connection = dataSource.getConnection();

        	String requeteIdProduitExiste = "SELECT COUNT(*) FROM Produits WHERE id = ?";

        	produitPrepStatement = connection.prepareStatement(requeteIdProduitExiste);

			// Lier les valeurs aux paramètres de la requête préparée
        	produitPrepStatement.setInt(1, idProduit);

			// Exécuter la requête et obtenir le résultat
			resultSet = produitPrepStatement.executeQuery();

			if (resultSet.next()) {
				int count = resultSet.getInt(1);
				return count > 0; // Renvoyer true si au moins un client correspondant a été trouvé
			}

			return false; // Renvoyer false si aucun client correspondant n'a été trouvé
		}finally {
	        close(connection, null, produitPrepStatement, resultSet);
	    }
	}



}
