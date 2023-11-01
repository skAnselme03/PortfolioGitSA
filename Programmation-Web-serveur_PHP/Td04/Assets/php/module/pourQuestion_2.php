<?php
	require("calcul.php");
	$nombre = 0;
	$resultat = 0;
	//si requete serveur est GET
	if ($_SERVER["REQUEST_METHOD"] === "GET") {
		// Vérifie l'existence des données
		if (isset($_GET["nombre"])) {
			
			// Récupère les valeurs des champs du formulaire
			$nombre =(int) $_GET["nombre"];
			$resultat = factorielle($nombre);
		}
	}
	
?>