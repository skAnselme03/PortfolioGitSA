package org.cegep.gg.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.cegep.gg.modele.Client;



public class ClientDBServices{

	private DataSource dataSource;
	
	public ClientDBServices(DataSource theDataSource) {
		dataSource = theDataSource;
	}
	
	/**
	 * Vérifie si un client existe dans la base de données en 
	 * fonction des informations de connexion fournies.
	 * 
	 * @param loginInput Le nom d'utilisateur ou l'adresse e-mail du client.
	 * @param password Le mot de passe du client.
	 * @return vrai si le client existe, faux sinon.
	 */
	public boolean checkClientExists(String loginInput, String password) {
		String query = "SELECT COUNT(*) FROM Client WHERE (username = ? OR courriel = ?) AND motDePasse = ?";
		

        try (Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)) {

			// Lier les valeurs aux paramètres de la requête préparée
			preparedStatement.setString(1, loginInput);
			preparedStatement.setString(2, loginInput);
			preparedStatement.setString(3, password);
			
			// Exécuter la requête et obtenir le résultat
			ResultSet resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				int count = resultSet.getInt(1);
				return count > 0; // Renvoyer true si au moins un client correspondant a été trouvé
			}

			return false; // Renvoyer false si aucun client correspondant n'a été trouvé
		} catch (SQLException e) {
			e.printStackTrace();
			return false; // En cas d'erreur, considérer que le client n'existe pas
		}
	}

	/**
	 * Récupère la liste des clients depuis la base de données.
	 * IMPORTANT: Cette fonction doit-être appeler seulement pour la page person d'un client.
	 * 
	 * @return Une liste de clients.
	 * @throws Exception Si une erreur survient lors de la récupération des données.
	 */
	public List<Client> getClients() throws Exception {
		
		List<Client> client = new ArrayList<>();
		
		Connection connection = null;
    	PreparedStatement preparedStatement = null;//utiliser pour contrer les attaques par injections
		ResultSet resultSet = null;
		
		try {
			// Établir une connexion à la base de données
	        connection = dataSource.getConnection();
	        
	        // Requête SQL pour récupérer tous les clients triés par codeInternaute
	        String sql = "SELECT * FROM Client ORDER BY codeInternaute";
	        
	        // Préparer la déclaration pour exécuter la requête
	        preparedStatement = connection.prepareStatement(sql);
	        
	        // Exécuter la requête et obtenir le résultat
	        resultSet = preparedStatement.executeQuery();
	        
	        // Parcourir les résultats et créer des objets Client
			
			while (resultSet.next()) {				
				int codeInternaute = resultSet.getInt("codeInternaute");
				String nom = resultSet.getString("nom");
				String prenom = resultSet.getString("prenom");
				String username = resultSet.getString("username");
				String motDePasse = resultSet.getString("motDePasse");
				String courriel = resultSet.getString("courriel");
				String telephone = resultSet.getString("telephone");
				
				
				Client clientTemp = new Client(codeInternaute, nom ,prenom ,
											   username, motDePasse, courriel,telephone);
				client.add(clientTemp);
			}
			return client;	// Renvoie la liste de clients récupérée	
		}
		finally {
			// Fermeture sécurisée des ressources de base de données	        
			close(connection, null,  preparedStatement, resultSet);
		}		
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

}

