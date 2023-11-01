<!DOCTYPE html>
<html lang="fr">
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<meta http-equiv="X-UA-Compatible" content="ie=edge">
		<meta name="Description" content="Manipulation syntaxe"/>
		<link rel="stylesheet" href="../css/main.css">
		<title>Travail dirigé 01/question 3</title>
	</head>
	<body>
		<header>
			<h1>TD 1: Question 3</h1>
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
			<form method="post">
				<section>
					<button type="submit">Générer valeurs aléatoires</button>
				</section>
			</form>
			<?php
				require "./module/genererNbrAleatoire.php";
				require "./module/pourQuestion_3.php";
			?>
			<table>
				<thead>
					<tr>
						<th>
							10 Nombres aléatoire comprise entre [0, 9999]
						</th>
					</tr>
				</thead>
				<tbody>
					<?php				
						foreach ($numbers as $nombre) {
							echo "<tr><td>$nombre</td></tr>";
						}		
					?>	
				</tbody>
			</table>
		</section>
	</body>
</html>