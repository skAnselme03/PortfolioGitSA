<!DOCTYPE html>
<html lang="fr">
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<meta http-equiv="X-UA-Compatible" content="ie=edge">
		<meta name="Description" content="Manipulation syntaxe"/>
		<link rel="stylesheet" href="../../css/main.css">
		<title>Travail dirigé 05/question 2</title>
	</head>
	<body>
		<header>
			<h1>TD 5: Question 2</h1>
			<nav>
				<a href="../../../index.php">Accueil</a>
				<a href="./continents.php">Liste des continents</a>
				<a href="./pays.php">Liste des pays</a>
				<a href="./villes.php">Liste des villes</a>
				<a href="./modifier_ville.php">Modifier villes</a>
				<a href="./supprimer_ville.php">Supprimer villes</a>
			</nav>
		</header>
		<?php
			require("../controllers/pourContinent.php");
		?>
		<section>
			<table>
				<thead>
					<tr>
						<th>Continent</th>
						<th>Population</th>
						<th>Superficie (Km<sup>2</sup>)</th>
						<th>Pays</th>
					</tr>
				</thead>
				<tbody>
					<?php
						$continents = getContinents();
						foreach($continents as $key => $value)
						{
							$idConctinent = $value['ID'];
							$nomContinent = $value['Name'];
							echo "<tr>";
							echo "<td>" . $value["Name"] . "</td>";
							echo "<td>" . $value["Population"] . "</td>";
							echo "<td>" . $value["Area"] . "</td>";
							echo "<td><a class=lien href='pays.php?id=". $idConctinent ."&nomContinent=".$nomContinent ."'>Voir pays</a></td>"; 
							echo "</tr>";
						}
					?>
				</tbody>
			</table>
		</section>
	</body>
</html>