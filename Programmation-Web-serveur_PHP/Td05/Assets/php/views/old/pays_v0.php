<!DOCTYPE html>
<html lang="fr">
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<meta http-equiv="X-UA-Compatible" content="ie=edge">
		<meta name="Description" content="Manipulation syntaxe"/>
		<link rel="stylesheet" href="../../css/main.css">
		<title>Travail dirigé 05/question 1</title>
	</head>
	<body>
		<header>
			<h1>TD 5: Question 3</h1>
			<nav>
				<a href="../../../index.php">Accueil</a>
				<a href="./continents.php">Liste des continents</a>
				<a href="./pays.php">Liste des pays</a>
				<a href="./villes.php">Liste des villes</a>
				<a href="./modifier_ville.php">Modifier villes</a>
				<a href="./supprimer_ville.php">Supprimer villes</a>
			</nav>
		</header>
		<?php require("../controllers/pourPays.php");?>
		<section>
			<h2>Liste des pays du continent <?php $continent?></h2>
			<table>
				<thead>
					<tr>
						<th>Pays</th>
						<th>Villes</th>
					</tr>
				</thead>
				<tbody>
					<?php
					/* 	foreach ($paysList as $pays) {
							echo "<tr>";
							echo "<td>" . $pays['Nom'] . "</td>";
						} */
						flush(); // Vide le tampon de sortie pour libérer la mémoire
					?>
				</tbody>
			</table>
			

		</section>
	</body>
</html>