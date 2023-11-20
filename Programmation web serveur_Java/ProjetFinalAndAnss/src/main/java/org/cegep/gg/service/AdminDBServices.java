package org.cegep.gg.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

public class AdminDBServices {
	private DataSource dataSource;

	public AdminDBServices(DataSource theDataSource) {
		dataSource = theDataSource;
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
	 * Méthode pour vérifier si un utilisateur est un admin - À CORRIGER!!!
	 * @param login
	 * @return
	 */
	public boolean estAdmin(String login) throws SQLException {
	    Connection connection = null;
	    PreparedStatement preparedStatement = null;
	    ResultSet resultSet = null;

	    try {
	        // Establish a database connection
	        connection = dataSource.getConnection();

	        // SQL query to check if the login exists in the 'Administrateur' table
	        String sql = "SELECT COUNT(*) FROM Administrateur WHERE codeEmploye = ?";
	        preparedStatement = connection.prepareStatement(sql);
	        preparedStatement.setString(1, login);

	        resultSet = preparedStatement.executeQuery();

	        // If there is at least one row with the given login, it's an admin
	        if (resultSet.next()) {
	            int count = resultSet.getInt(1);
	            return count > 0;
	        }

	        return false;
	    } finally {
	        close(connection, null, preparedStatement, resultSet);
	    }
	}

}
