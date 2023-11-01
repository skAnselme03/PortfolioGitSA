<?php
require ("../data/param.php");
include("../models/ConnectionBDD.php");
$nomContinent = "";
$paysList = array();

/**
 * Retourne la liste des pays d'un continent spécifié par son identifiant
 * @param int $continentId: l'id du continent
  * @return array resutlt: resultat de la requête - la liste des pays du continent selectionner
 */
function getPaysByContinent($continentId)
{
		$page404 = "../../pages/page404.html";
		$pageContinent = "../views/continents.php";

		// Instancier un objet de connection
		$connectionBDD = new ConnectBDD(HOSTNAME, USERNAME, PASSWORD, DATABASENAME, PORT);

		$connexion = $connectionBDD->getConnection();
		/* $condition = "ContinentId = $continentId"; */
		$condition = "ContinentId = ?";
		if ($connexion != null) {
			//resultat de la requête
			/* $result = $connectionBDD->get("Country", $condition, [$continentId]); */
			$requete = "SELECT * FROM Country WHERE $condition" ;
			$result = $connectionBDD->selectData($requete, [$continentId]);
			if (!$result) {
				header("Location: " . $pageContinent);
				echo "Attention ! Problème de requête SELECT <br>";
				$connectionBDD->closeConnection();//fermer la connection
				return null;
			} else {
				$connectionBDD->closeConnection();//fermer la connection
				return $result; //resultat de la requête
			}
		}
		$connectionBDD->closeConnection();//fermer la connection
		return null;
}

	// Vérifie si l'identifiant du continent est présent dans l'URL
	if (isset($_POST['idContinent']) && isset($_POST['nomContinent'])) {
		$continentId = $_POST['idContinent'];
		$continentName = $_POST['nomContinent'];

		if($continentId != null){
			//récupérer la liste des pays selon le continent
			$paysList = getPaysByContinent($continentId);
			if ($paysList != null) {
				$nomContinent = $continentName;
			} else {
				echo "<p>Aucun pays trouvé pour le continent $continentName.</p>";
			}
		}
	} else {
		echo "L'identifiant du continent n'est pas spécifié dans l'URL.";
	}
?>
