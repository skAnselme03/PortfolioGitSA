<?php 
	const NOM_SITE = 'monSite.com';
	$nom = 'Titi';
	//affichés variables
	echo '<p>NOM_SITE : ', NOM_SITE, '</p> <br>';
	echo "<p>Nom : $nom </p> <br>";
	//Compter le nombre de lettres du nom
	$nombre_lettre = strlen($nom);
	echo "<p>Le nombre de lettre dans le nom de \"$nom\" est : $nombre_lettre</p> <br>";
	
	//Indiquer si le nom commence par une voyelle ou une consonne
	//extraire le premier caractère du nom et le mettre en minuscule
	$premiereLettre = strtolower(substr($nom, 0, 1));
	$tableVoyelle = ['a', 'e', 'i', 'o', 'u'];
	//vérifier si la première lettre existe dans la table des voyelles
	if(in_array($premiereLettre, $tableVoyelle)){
		echo "Le nom \"$nom\" commence par une voyelle.";
	} else {
		echo "Le nom \"$nom\" commence par une consonne.";
	}
?>