<?php
	$nombreMots  = 0;
	$phrase = "";
	//si requete serveur est post
	if ($_SERVER["REQUEST_METHOD"] === "POST") {
		// Vérifier si le champ de phrase a été soumis
		if (isset($_POST["phrase"])) {
			// Récupérer la valeur de l'input textarea
			$phrase = $_POST["phrase"];
			// Utiliser explode() pour diviser la phrase en un tableau de mots
			$mots = explode(" ", $phrase);

			// Compter le nombre de mots dans le tableau
			$nombreMots = count($mots);
		}
	}


?>