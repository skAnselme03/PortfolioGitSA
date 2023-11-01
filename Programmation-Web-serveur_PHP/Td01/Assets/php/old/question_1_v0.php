<!DOCTYPE html>
<html lang="fr">
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<meta http-equiv="X-UA-Compatible" content="ie=edge">
		<meta name="Description" content="Manipulation syntaxe"/>
		<link rel="stylesheet" href="../css/main.css">
		<title>Travail dirigé 01/question 1</title>
	</head>
	<body>
		<header>
			<h1>TD 1: Question 1</h1>
			<nav>
				<a href="../../index.php">Acceuil</a>
				<a href="./question_1.php">Question 1</a>
				<a href="./question_2.php">Question 2</a>
				<a href="./question_3.php">Question 3</a>
				<a href="./question_4.php">Question 4</a>
				<a href="./question_5.php">Question 5</a>
			</nav>
		</header>
		<section>
			<?php
			include("./modulePHP/pourQuestion_1.php");
			/* 	const NOM_SITE = 'monSite.com';
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
				} */
			?>
		</section>
	</body>
</html>