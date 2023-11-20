package org.cegep.gg.service;


import java.sql.Connection;
//import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.cegep.gg.modele.Adresse;
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
	public boolean checkClientExists(String loginInput, String password) throws SQLException {
		Connection connection = null;
	    PreparedStatement loginStatement = null;
	    ResultSet resultSet = null;


        try{
        	connection = dataSource.getConnection();

        	String requeteLogin = "SELECT COUNT(*) FROM Client WHERE (username = ? OR courriel = ?) AND motDePasse = ?";

	        loginStatement = connection.prepareStatement(requeteLogin);

			// Lier les valeurs aux paramètres de la requête préparée
			loginStatement.setString(1, loginInput);
			loginStatement.setString(2, loginInput);
			loginStatement.setString(3, password);

			// Exécuter la requête et obtenir le résultat
			resultSet = loginStatement.executeQuery();

			if (resultSet.next()) {
				int count = resultSet.getInt(1);
				return count > 0; // Renvoyer true si au moins un client correspondant a été trouvé
			}

			return false; // Renvoyer false si aucun client correspondant n'a été trouvé
		}finally {
	        close(connection, null, loginStatement, resultSet);
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
				String dateNaissance = resultSet.getString("dateNaissance");
				String motDePasse = resultSet.getString("motDePasse");
				String courriel = resultSet.getString("courriel");
				String telephone = resultSet.getString("telephone");


				Client clientTemp = new Client(codeInternaute, nom ,prenom , username, dateNaissance, motDePasse, courriel,telephone);
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

	/**
	 * Modifie les données d'un client
	 * @param client
	 * @throws SQLException Si une erreur SQL survient lors de l'insertion du client.
	 */
	public void modifierClient(Client client) throws SQLException {
		  Connection connection = null;
		  PreparedStatement clientStatement = null;

		  try {
			    connection = dataSource.getConnection();

			    // Requête SQL pour modifier une nouvelle adresse
			String clientInsertRequete = "UPDATE Client SET codeInternaute = ?, prenom = ?, "
										+ "nom = ?, dateNaissance = ?, telephone = ?, courriel = ?, "
										+ "motDePasse = ?, idAdressePersonnelle = ?, idAdresseLivraison = ? "
										+ "WHERE idClient = ?";

			clientStatement = connection.prepareStatement(clientInsertRequete);
            // Attribution des valeurs du client aux paramètres de la requête SQL
	        clientStatement.setInt(1, client.getCodeInternaute());
	        clientStatement.setString(2, client.getPrenom());
            clientStatement.setString(3, client.getNom());
            clientStatement.setString(4, client.getUsername());
            clientStatement.setString(5,  client.getDateNaissance());
            clientStatement.setString(6, client.getTelephone());
            clientStatement.setString(7, client.getCourriel());
            clientStatement.setString(8, client.getMotDePasse());
            clientStatement.setInt(9, client.getIdAdressePersonnelle());
            clientStatement.setInt(10, client.getIdAdresseLivraison());

			// Exécution de la requête SQL d'insertion
        	clientStatement.executeUpdate();

		}finally {
			    close(connection, null, clientStatement, null);
		}

	}

	/**
	 * Ajouter un nouveau client dans la base de données.
	 *
	 * @param client Le client à insérer.
	 * @throws SQLException Si une erreur SQL survient lors de l'insertion du client.
	 * @return true si le nouveau client a été ajouter, false si l'adresse mail client existe déjà
	 */
	public boolean ajouterClient(Client client) throws SQLException {

		Connection connection = null;
	    //Connection connection = null;
		PreparedStatement clientStatement = null;

		try {
			connection = dataSource.getConnection();
			// Vérifie si l'adresse e-mail existe déjà dans la base de données
	        if (emailExiste(client.getCourriel())) {
	            return false; //un client existe déjà àvec cette email.
	        }
	        // Définition de la requête SQL pour insérer un nouveau client
		    String clientInsertRequete = "INSERT INTO Client (codeInternaute, prenom, nom, username, dateNaissance, telephone, "
		            + "courriel, motDePasse, idAdressePersonnelle, idAdresseLivraison) "
		            + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

	        clientStatement = connection.prepareStatement(clientInsertRequete);
            // Attribution des valeurs du client aux paramètres de la requête SQL
	        clientStatement.setInt(1, client.getCodeInternaute());
	        clientStatement.setString(2, client.getPrenom());
            clientStatement.setString(3, client.getNom());
            clientStatement.setString(4, client.getUsername());
            clientStatement.setString(5, client.getDateNaissance());
            clientStatement.setString(6, client.getTelephone());
            clientStatement.setString(7, client.getCourriel());
            clientStatement.setString(8, client.getMotDePasse());
            clientStatement.setInt(9, client.getIdAdressePersonnelle());
            clientStatement.setInt(10, client.getIdAdresseLivraison());
            // Exécution de la requête SQL d'insertion
        	clientStatement.execute();
		 }
		finally {
		        close(connection, null, clientStatement, null);
	    }
		return true; // l'ajout du nouveau client à bien été effectuer.
	}

	/**
	 * Obtient l'identifiant du client en fonction de son adresse email.
	 *
	 * @param courriel L'adresse email du client.
	 * @return Le client
	 * @throws SQLException Si une erreur SQL survient lors de l'accès à la base de données.
	 */
	public Client getClient(String courriel) throws SQLException {

		Client client = null;
		Connection connection = null;
	    PreparedStatement clientStatement = null;
	    ResultSet resultSet = null;

	    // Préparation de la requête SQL
	    try {
			connection = dataSource.getConnection();

		    // Requête SQL pour rechercher le client par son adresse email
		    String requeteGetIdClient = "SELECT * FROM Client WHERE courriel = ?";

	        clientStatement = connection.prepareStatement(requeteGetIdClient);

	        clientStatement.setString(1, courriel);

	        // Exécution de la requête SQL et récupération des résultats
	        resultSet = clientStatement.executeQuery();

	        if (resultSet.next()) {
	        	int idClient = resultSet.getInt("idClient");
	        	int codeInternaute = resultSet.getInt("codeInternaute");
	        	String prenom = resultSet.getString("prenom");
	        	String nom = resultSet.getString("nom");
	        	String username = resultSet.getString("username");
	        	String dateNaissance = resultSet.getString("dateNaissance");
	        	String telephone = resultSet.getString("telephone");
	        	String email = resultSet.getString("courriel");
	        	String motDePasse = resultSet.getString("motDePasse");
	        	int idAdressePersonnelle = resultSet.getInt("idAdressePersonnelle");
	        	int idAdresseLivraison = resultSet.getInt("idAdressePersonnelle");

	        	client = new Client(idClient, codeInternaute, nom,
	        						prenom, username, dateNaissance,"",
	        						email, telephone,
	        						idAdressePersonnelle, idAdresseLivraison);
	        }
	        return client;

	    } /*catch (SQLException e) {
	        e.printStackTrace();
	        throw new SQLException("**** Erreur lors de la recherche du client par adresse email ****");
	    } */finally {
	        close(null, null, clientStatement, resultSet);
	    }
	}


	/**
	 * Vérifie qu'un client existe par son id
	 * @param idClient l'identification du client
	 * @return vrai si client existe, faux sinon
	 * @throws SQLException
	 */
	public boolean clientExisteById(int idClient) throws SQLException {
		Connection connection = null;
	    PreparedStatement clientStatement = null;
	    ResultSet resultSet = null;


        try{
        	connection = dataSource.getConnection();

        	String requeteIdClientExiste = "SELECT COUNT(*) FROM Client WHERE idClient = ?";

        	clientStatement = connection.prepareStatement(requeteIdClientExiste);

			// Lier les valeurs aux paramètres de la requête préparée
        	clientStatement.setInt(1, idClient);

			// Exécuter la requête et obtenir le résultat
			resultSet = clientStatement.executeQuery();

			if (resultSet.next()) {
				int count = resultSet.getInt(1);
				return count > 0; // Renvoyer true si au moins un client correspondant a été trouvé
			}

			return false; // Renvoyer false si aucun client correspondant n'a été trouvé
		}finally {
	        close(connection, null, clientStatement, resultSet);
	    }
	}

	/**
	 * Obtient l'adresse demandé en fonction l'id d'adresse fourni
	 *
	 * @param courriel L'adresse email du client.
	 * @param idAdresse l'id de l'adresse rechercher
	 * @return Le client
	 * @throws SQLException Si une erreur SQL survient lors de l'accès à la base de données.
	 */
	public Adresse getAdresseClientbyId(int idAdresse) throws SQLException {
		Adresse getAdresse = null;
		Connection connection = null;
	    PreparedStatement clientStatement = null;
	    ResultSet resultSet = null;
    	 // Préparation de la requête SQL
	    try {
			connection = dataSource.getConnection();

		    // Requête SQL pour rechercher le client par son adresse email
		    String rqtGetAdresseClient = "SELECT * FROM Adresse WHERE idAdresse = ?";

	        clientStatement = connection.prepareStatement(rqtGetAdresseClient);

	        clientStatement.setInt(1, idAdresse);

	        // Exécution de la requête SQL et récupération des résultats
	        resultSet = clientStatement.executeQuery();

	        if (resultSet.next()) {
	        	int codedAdresse = resultSet.getInt("idAdresse");
	        	String adresse = resultSet.getString("adresse");
	        	String ville = resultSet.getString("ville");
	        	String province = resultSet.getString("province");
	        	String pays = resultSet.getString("pays");
	        	String codePostal = resultSet.getString("codePost");

	        	getAdresse = new Adresse(codedAdresse, adresse, ville, province, pays, codePostal);
	        }
	        return getAdresse;

	    }finally {
	        close(connection, null, clientStatement, resultSet);
	    }
    }

	/**
	 * Vérifie si une adresse e-mail existe déjà dans la base de données.
	 *
	 * @param email L'adresse e-mail à vérifier.
	 * @return true si l'adresse e-mail existe déjà, sinon false.
	 * @throws SQLException Si une erreur SQL survient lors de la vérification.
	 */
	public boolean emailExiste(String email) throws SQLException {
		Connection connection = null;
	    PreparedStatement emailStatementCheck= null;
	    ResultSet resultSet = null;

	    try {

	        // Obtenez une connexion à votre base de données (vous devez avoir déjà configuré votre source de données dataSource)
	        connection = dataSource.getConnection();
	        // Requête SQL pour compter le nombre d'occurrences de l'adresse e-mail dans la table Client
	        String checkEmailRequete = "SELECT COUNT(*) FROM Client WHERE courriel = ?";
	        emailStatementCheck = connection.prepareStatement(checkEmailRequete);
	        // Remplace le paramètre dans la requête SQL par l'adresse e-mail à vérifier
	        emailStatementCheck.setString(1, email);

	        // Exécutez la requête SQL et obtenez le résultat
	        resultSet = emailStatementCheck.executeQuery();	 // Si le résultat contient au moins une ligne, l'eamil existe dans la base de données

	        if (resultSet.next()) {
                // Récupère le nombre d'occurrences
                int count = resultSet.getInt(1);
                // Si le nombre d'occurrences est supérieur à zéro, l'adresse e-mail existe déjà
                return count > 0;
	        	}
	        }/* catch (SQLException e) {
		        e.printStackTrace();
		        throw new SQLException("**** La vérification de l'existance de cet email à échoué ****");
		    }*/
			finally {
				close(connection, null, emailStatementCheck, resultSet);
			}
	        return false;
	    }



	/**
	 * Vérifie si le username existe déjà dans la base de données.
	 *
	 * @param username Le username à vérifier.
	 * @return true si le username existe, sinon false.
	 * @throws SQLException Si une erreur SQL survient lors de l'accès à la base de données.
	 */
	public boolean usernameExistsInDatabase(String username) throws SQLException {
	    Connection connection = null;
	    PreparedStatement preparedStatement = null;
	    ResultSet resultSet = null;
	    boolean usernameExists = false;

	    try {
	        // Obtenez une connexion à votre base de données (vous devez avoir déjà configuré votre source de données dataSource)
	        connection = dataSource.getConnection();

	        // Préparez une requête SQL pour rechercher le username dans la table appropriée (remplacez "votre_table" par le nom réel de votre table)
	        String sql = "SELECT COUNT(*) FROM Client WHERE username = ?";
	        preparedStatement = connection.prepareStatement(sql);
	        preparedStatement.setString(1, username);

	        // Exécutez la requête SQL et obtenez le résultat
	        resultSet = preparedStatement.executeQuery();

	        // Si le résultat contient au moins une ligne, le username existe dans la base de données
	        if (resultSet.next()) {
	            int count = resultSet.getInt(1);
	            usernameExists = (count > 0);
	        	}
	        } /*catch (SQLException e) {
		        e.printStackTrace();
		        throw new SQLException("**** La vérification de l'existance de ce username à échoué ****");
		    }*/
			finally {
				close(connection, null, preparedStatement, resultSet);
			}
	        return usernameExists;
	    }


	/**
	 * Obtient l'identifiant de l'adresse en fonction de ses valeurs.
	 *
	 * @param adresse L'objet ClientAdresse contenant les valeurs de l'adresse.
	 * @return L'identifiant de l'adresse s'il existe, sinon -1.
	 * @throws Exception SQLException En cas d'erreur lors de l'accès à la base de données.
	 */
	public int getIdAdresseByValues(Adresse adresse) throws Exception {

	    Connection connection = null;
	    PreparedStatement idAdresseStatement = null; // Modification : Utilisation de la bonne instance de PreparedStatement
	    ResultSet resultSet = null;

	    // Préparation de la requête SQL
	    try {
	        connection = dataSource.getConnection();
		    // Requête SQL pour rechercher l'adresse par ses valeurs
		    String requeteGetIdAdresse = "SELECT * FROM Adresse "
	                                + "WHERE adresse = ? AND ville = ? AND province = ? AND pays = ? "
	                                + "AND codepost = ?";

	        idAdresseStatement = connection.prepareStatement(requeteGetIdAdresse);
	        // Attribution des valeurs de l'adresse aux paramètres de la requête SQL
	        idAdresseStatement.setString(1, adresse.getAdresse());
	        idAdresseStatement.setString(2, adresse.getVille());
	        idAdresseStatement.setString(3, adresse.getProvince());
	        idAdresseStatement.setString(4, adresse.getPays());
	        idAdresseStatement.setString(5, adresse.getCodePostal());

	        // Exécution de la requête SQL et récupération des résultats
	        resultSet = idAdresseStatement.executeQuery();

	        if (resultSet.next()) {
	            return resultSet.getInt("idAdresse");
	        }
	    } /*catch (SQLException e) {
	        e.printStackTrace();
	        throw new Exception("L'adresse n'existe pas dans la BDD");
	    } */finally {
	        close(connection, null, idAdresseStatement, resultSet);
	    }
        return -1;
	}


	/**
	 * Insère une nouvelle adresse dans la base de données.
	 *
	 * @param adresse L'objet Adresse contenant les valeurs de l'adresse.
	 * @throws SQLException Si une erreur SQL survient lors de l'insertion de l'adresse.
	 */
	public void insererAdresse(Adresse adresse) throws SQLException {
	    // Identifiant de l'adresse, initialisé à -1 (valeur par défaut en cas d'échec)
	    //int idAdresse = -1;

	    Connection connection = null;
	    PreparedStatement clientAdresseStatement = null;

	    try {
	        connection = dataSource.getConnection();

	        // Requête SQL pour insérer une nouvelle adresse
	        String clientAdresseRequete = "INSERT INTO Adresse (adresse, ville, province, pays, codepost) VALUES (?, ?, ?, ?, ?)";

	        clientAdresseStatement = connection.prepareStatement(clientAdresseRequete);

	        // Attribution des valeurs de l'adresse aux paramètres de la requête SQL
	        clientAdresseStatement.setString(1, adresse.getAdresse());
	        clientAdresseStatement.setString(2, adresse.getVille());
	        clientAdresseStatement.setString(3, adresse.getProvince());
	        clientAdresseStatement.setString(4, adresse.getPays());
	        clientAdresseStatement.setString(5, adresse.getCodePostal());

	        // Exécution de la requête SQL d'insertion
	        clientAdresseStatement.executeUpdate();

	    }finally {
	        close(connection, null, clientAdresseStatement, null);
	    }
	}


	/**
	 * Modifier l'adresse dans la bdd.
	 *
	 * @param adresse L'objet Adresse contenant les valeurs de l'adresse à mettre à jout
	 * @throws SQLException Si une erreur SQL survient lors de l'insertion de l'adresse.
	 */
	public void modifierAdresse(Adresse adresse) throws SQLException {

	    Connection connection = null;
	    PreparedStatement adressePreparedStatement = null;

	    try {
	        connection = dataSource.getConnection();

	        // Requête SQL pour modifier une nouvelle adresse
	        String sql = "UPDATE Adresse SET adresse = ?, "
	        		+ "ville = ?, province = ?, pays = ?, "
	        		+ "codepost = ? WHERE idAdresse = ?";
            adressePreparedStatement = connection.prepareStatement(sql);
            adressePreparedStatement.setString(1, adresse.getAdresse());
            adressePreparedStatement.setString(2, adresse.getVille());
            adressePreparedStatement.setString(3, adresse.getProvince());
            adressePreparedStatement.setString(4, adresse.getPays());
            adressePreparedStatement.setString(5, adresse.getCodePostal());
            adressePreparedStatement.setInt(6, adresse.getIdAdresse());

	        // Exécution de la requête SQL d'insertion
            adressePreparedStatement.executeUpdate();

	    }finally {
	        close(connection, null, adressePreparedStatement, null);
	    }

	}


}
