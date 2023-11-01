<?php	
	require("classes/Impot.php");

	$nom  = "";
	$revenu = 0;
	$impot_calculer = 0;

	//si requete serveur est GET
	if ($_SERVER["REQUEST_METHOD"] === "GET") {
		// Vérifie l'existence des données
		if (isset($_GET["nom"]) &&
			isset($_GET["revenu"])) {
			// Récupère les valeurs des champs du formulaire
			$nom = $_GET["nom"];
			$revenu =(int) $_GET["revenu"]; 
			if ($nom !== "" && $revenu !== 0) {
				$impot_calculer = new Impot($nom, $revenu);
				$impot_calculer->calcul_impot();
				$impot_calculer->afficher_impot();
			}
		}
	}
?>