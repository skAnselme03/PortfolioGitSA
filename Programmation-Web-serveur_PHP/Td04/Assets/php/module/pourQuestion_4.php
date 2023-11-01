<?php

	//classe Ville inclus
	include("classes/Ville.php");

	//déclarer une liste de ville
	$villes = array(
		"Les Gonaïves" => "Artibonite",
		"Hinche" => "Centre",
		"Jérémie" => "Grand'Anse",
		"Miragoâne" => "Nippes",
		"Cap-Haïtien" => "Nord",
		"Fort-Liberté" => "Nord-Est",
		"Port-de-Paix" => "Nord-Ouest",
		"Port-au-Prince" => "Ouest",
		"Les Cayes" => "Sud"
	);

	$departements = array();// tableau de départements
	//Instancier des villes
	foreach ($villes as $nom => $departement) {
		$departements[] = new Ville($nom, $departement);
	/* 	echo "$nom : $departement <br>"; */
	}
	/* foreach ($departements as $ville) {
		echo $ville->afficherMessage() . "<br>";
	} */
?>