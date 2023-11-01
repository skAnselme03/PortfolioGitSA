<?php
	$jourDeLaSemaine = "";
	$uneDate = "";
	$afficheJours = "";
	//si requete serveur est post
	if ($_SERVER["REQUEST_METHOD"] === "POST") {
		// Vérifier si le champ de phrase a été soumis
		if (isset($_POST["uneDate"])) {
			// Récupérer la valeur de l'input
			$uneDate = $_POST["uneDate"];
			//jour de la semaine associé à la date
			$jourDeLaSemaine = joursDeLaSemaine($uneDate);
			$afficheJours = "<p>Le jour de la semaine du $uneDate est un : $jourDeLaSemaine</p>";
		}
	}

?>