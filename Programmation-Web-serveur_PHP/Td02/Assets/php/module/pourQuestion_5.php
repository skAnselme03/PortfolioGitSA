<?php
	$phraseChange = "";
	$mot = "";

	//si requete serveur est post
	if ($_SERVER["REQUEST_METHOD"] === "POST") {
		// Vérifier si les champs de phrase et de mot 	ont été soumis
		if (isset($_POST["phrase"]) && isset($_POST["mot"])) {
			// Récupérer la valeur de l'input
			$phrase = $_POST["phrase"];
			$mot = $_POST["mot"];
			//enlever le mot dans la phrase
			$phraseChange = str_ireplace($mot, "", $phrase);
		}
	}


?>