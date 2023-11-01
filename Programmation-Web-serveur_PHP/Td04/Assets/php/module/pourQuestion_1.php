<?php
	require("calcul.php");
	$aire_rectangle = 0;
	$longueur = 0;
	$largeur = 0;
	//si requete serveur est post
	if ($_SERVER["REQUEST_METHOD"] === "GET") {
		// Vérifie l'existence des données
		if (isset($_GET["longueur"]) && 
			isset($_GET["largeur"])) {
			
			// Récupère les valeurs des champs du formulaire
			$longueur =(float) $_GET["longueur"];
			$largeur = (float)$_GET["largeur"];
			//calcul de l'aire du rectangle
			$aire_rectangle = calcul_aireRectangle($longueur, $largeur);
		}
	}
	
?>