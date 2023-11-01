<?php
	require ("param.php");
	include("class/ConnectionBDD.php");
	$tableCountry = array();// tableau associatif pour stocqué les données country

	try {
		$page404 = "../../pages/page404.html";
		// Instancier un objet de connection
		$conncetionBDD = new ConnectBDD(HOSTNAME, USERNAME, PASSWORD, DATABASENAME, PORT, $page404);

		//Test de la classe
		$requete = "SELECT * FROM Country";

		$tableCountry = $conncetionBDD->selectData($requete);
		// Fermeture de la connexion
		$conncetionBDD->closeConnection();

	} catch (Exception $e) {
		echo "Erreur : " . $e->getMessage();
	}
?>
