<?php
	require ("../data/param.php");
	include("../models/ConnectionBDD.php");
	include("../models/Continent.php");

	$listeContinents = []; 

	try {
		$page404 = "../../pages/page404.html";
		// Instancier un objet de connection
		$connectionBDD = new ConnectBDD(HOSTNAME, USERNAME, PASSWORD, DATABASENAME, PORT, $page404);

        // si la connection n'Est pas null
     /*    $result = $connectionBDD->get("Continent"); */
        while ($continent = $connectionBDD->get("Continent")) {
           /*  $continent = new Continent($ligne[0], $ligne[1], $ligne[2], $ligne[3]); */
            array_push($listeContinents, $continent);
        }

		// Fermeture de la connexion
		$connectionBDD->closeConnection();

	} catch (Exception $e) {
		echo "Erreur : " . $e->getMessage();
	}
?>
