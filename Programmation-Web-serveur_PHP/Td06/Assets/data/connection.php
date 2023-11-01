<?php
	require ("param.php");

/* 	try {
		// Connexion au serveur MySQL
		$con = new mysqli(HOSTNAME, USERNAME, PASSWORD, DATABASENAME, PORT);

		// Vérification de la connexion
		if ($con->connect_errno) {
			throw new Exception("Erreur de connexion à la base de données : " . $con->connect_error);
		}

		// Code supplémentaire pour interagir avec la base de données...

		// Fermeture de la connexion
		$con->close();
	} catch (Exception $e) {
		echo "Erreur : " . $e->getMessage();
	}*/

	/**
	 * Retourner la connection à un BDD
	 */
	function connect()
	{
		$connexion = new mysqli(HOSTNAME, USERNAME, PASSWORD, DATABASENAME,  PORT);
		if (!$connexion) {
			echo "Attention ! Problème de connexion avec la base <br>";
			return null;
		}else
			return $connexion;
	}
?> 
