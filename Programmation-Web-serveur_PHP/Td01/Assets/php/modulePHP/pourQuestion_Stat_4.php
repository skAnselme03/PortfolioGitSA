<?php
	if ($_SERVER["REQUEST_METHOD"] == "POST") {
		$serverNames = [];

		foreach ($_POST as $key => $value) {
			$email = $value;
			$serverName = explode("@", $email)[1];
			$serverNames[] = $serverName;
		}
	}
	// Afficher le nom des serveur
	foreach ($serverNames as $key => $value) {
		echo "<tr><td>$value</td></tr>"; // Affichage de l'adresse e-mail dans une ligne du tableau
	}
?>
