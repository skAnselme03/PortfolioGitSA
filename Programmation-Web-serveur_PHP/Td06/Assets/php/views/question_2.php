<!DOCTYPE html>
<html lang="fr">
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<meta http-equiv="X-UA-Compatible" content="ie=edge">
		<meta name="Description" content="Manipulation syntaxe"/>
		<link rel="stylesheet" href="../../css/main.css">

		<title>Travail dirigé 01/question 2</title>
	</head>
	<body>
		<header>
			<h1>TD 1: Question 2</h1>
			<nav>
				<a href="../../../index.php">Accueil</a>
				<a href="./question_1.php">Question 1</a>
				<a href="./question_2.php">Question 2</a>
				<a href="./question_3.php">Question 3</a>
				<a href="./question_4.php">Question 4</a>
				<a href="./question_5.php">Question 5</a>
			</nav>
		</header>
		<?php require("../controllers/pourQuestion_2.php")?>
		<section>
			<?php 
				if (!empty($username)) {
					echo "<h2>Bonjour, ". $username . "!</h2>";
				}
			?>
			<table>
				<thead>
					<tr>
						<th>Titre</th>
						<th>Genre</th>
						<th>Date de release</th>
						<th>Prix</th>
					</tr>
				</thead>
				<tbody>
					<?php
						$films = getMoovies();

						if($films != null){
							foreach ($films as $film) {
								echo "<tr>";
								echo "<td>" . $film->title . "</td>";
								echo "<td>" . $film->genre . "</td>";
								echo "<td>". $film->releaseDate . "</td>";
								echo "<td>" . $film->getPrice() . "</td>";
								echo "</tr>";
							}
						}
					?>
				</tbody>
			</table>
		</section>
	</body>
</html>