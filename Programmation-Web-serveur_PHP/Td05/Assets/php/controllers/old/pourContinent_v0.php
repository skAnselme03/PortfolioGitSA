<?php
	require ("../data/param.php");
	include("../models/ConnectionBDD.php");
	include("../models/Continent.php");

	$listeContinents = array(); 

	try {
		$page404 = "../../pages/page404.html";
		// Instancier un objet de connection
		$connectionBDD = new ConnectBDD(HOSTNAME, USERNAME, PASSWORD, DATABASENAME, PORT, $page404);

        // si la connection n'Est pas null
        if($connectionBDD->getConnection() != null){
          //Exécute une requête GET (SELECT) pour récupérer les données de la table continent.
          /* $listeContinents = $connectionBDD->get("Continent"); */
          /* foreach ($connectionBDD->get("Continent") as $continent) {
            $objContinents = new Continent($continent[0], $continent[1],$continent[2], $continent[3]);
            $listeContinents[] =  $objContinents;
          } */ 
        /*   foreach ($connectionBDD->get("Continent") as $continent) {
            $continent = new Continent($continent[0], $continent[1],$continent[2], $continent[3]);
            array_push($listeContinents, $continent);
          } */
          //$continents = $connectionBDD->get("Continent");
         /*  while ($ligne) {
            $continent = new continent($ligne[0], $ligne[1], $ligne[2], $ligne[3]);
            array_push($listeContinents, $continent);
            } */
            foreach ($connectionBDD->get("Continent") as $ligne) {
                $continent = new Continent($ligne[0], $ligne[1], $ligne[2], $ligne[3]);
                array_push($listeContinents, $continent);
            }
        }

		// Fermeture de la connexion
		$connectionBDD->closeConnection();

	} catch (Exception $e) {
		echo "Erreur : " . $e->getMessage();
	}
?>
