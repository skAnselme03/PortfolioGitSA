<?php
	require ("../data/param.php");
	include("../models/ConnectionBDD.php");

/**
 * retourne la liste des continents stoker dans la 
 * table Continent dans un tableau (array) en mémoire.
 * @return array resutlt: resultat de la requête - la liste des continents
 */
function getContinents()
{// Instancier un objet de connection
    $connectionBDD = new ConnectBDD(HOSTNAME, USERNAME, PASSWORD, DATABASENAME, PORT);
    $page404 = "";

    $connexion = $connectionBDD->getConnection();

    if ($connexion != null) {
        $result = $connectionBDD->get("Continent");
        if (!$result) {
            header("Location: " . $page404);
            echo "Attention ! Problème de requête SELECT <br>";
            $connectionBDD->closeConnection();
            return null;
        } else {
            $connectionBDD->closeConnection();
            return $result;
        }
    }
    $connectionBDD->closeConnection();
    return null;
}
?>
