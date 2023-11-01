<?php
require ("../data/param.php");
include("../models/ConnectionBDD.php");
$nomPays = "";
$villesList = array();

/**
 * Retourne la liste des pays d'un continent spécifié par son identifiant
 * @param int $paysName: l'id du continent
  * @return array resutlt: resultat de la requête - la liste des pays du continent selectionner
 */
function getVillesByPays($paysName)
{
		$page404 = "../../pages/page404.html";
		$pagePays = "../views/pays.php";

		// Instancier un objet de connection
		$connectionBDD = new ConnectBDD(HOSTNAME, USERNAME, PASSWORD, DATABASENAME, PORT);

		$connexion = $connectionBDD->getConnection();
		/* $condition = "paysName = $paysName"; */
		$condition = "CountryName = ?";
		if ($connexion != null) {
			//resultat de la requête
			/* $result = $connectionBDD->get("City", $condition, [$paysName]); */
			$requete = "SELECT * FROM City WHERE $condition" ;
			$result = $connectionBDD->selectData($requete, [$paysName]);
			if (!$result) {
				header("Location: " . $pagePays);
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
	if (isset($_POST['nomPays']) OR isset($_POST['countryName'])) {
		if (isset($_POST['nomPays'])) {
			$paysName = $_POST['nomPays'];
		}else{
			$paysName = $_POST['countryName'];
		}
		echo "<br>$paysName<br>";
		echo isset($_POST['countryName']);

		if($paysName != null){
			//récupérer la liste des pays selon le continent
			$villesList = getVillesByPays($paysName);
			if ($villesList != null) {
				$nomPays = $paysName;
			} else {
				echo "<p>Aucun pays trouvé pour le continent $paysName.</p>";
			}
		}
	} else {
		echo "L'identifiant du continent n'est pas spécifié dans l'URL.";
	}
?>
