<?php
require ("../data/param.php");
include("../models/ConnectionBDD.php");

// retourne la liste des pays d'un continent spécifié par son identifiant

function getPaysByContinent($continentId)
{
		$page404 = "../../pages/page404.html";
		// Instancier un objet de connection
		$connectionBDD = new ConnectBDD(HOSTNAME, USERNAME, PASSWORD, DATABASENAME, PORT);

		$connexion = $connectionBDD->getConnection();

		if ($connexion != null) {
			$result = $connectionBDD->get("Pays", $continentId);
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
		$connectionBDD->closeConnection();//fermer la connection
		return null;
	}

	// Vérifie si l'identifiant du continent est présent dans l'URL
	if (isset($_GET['id']) && isset($_GET['nom'])) {
		$continentId = $_GET['id'];
		$continentName = $_GET['nom'];

		$paysList = getPaysByContinent($continentId);

		if ($paysList != null) {
			echo "<h2>Liste des pays du continent $continentName :</h2>";
			echo "<ul>";
			foreach ($paysList as $pays) {
				echo "<li>" . $pays->name . "</li>";
			}
			echo "</ul>";
		} else {
			echo "Aucun pays trouvé pour le continent $continentName.";
		}
	} else {
		echo "L'identifiant du continent n'est pas spécifié dans l'URL.";
	}
?>
