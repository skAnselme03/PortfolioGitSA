<!DOCTYPE html>
<html lang="fr">
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<meta http-equiv="X-UA-Compatible" content="ie=edge">
		<meta name="Description" content="Manipulation syntaxe"/>
		<link rel="stylesheet" href="../css/main.css">
		<title>Travail dirigé 01/question 4</title>
	</head>
	<body>
		<header>
			<h1>TD 1: Question 4</h1>
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
				require "./module/jourDeLaSemaine.php";
				require "./module/pourQuestion_4.php";
				echo  "<p>Le jour de la semaine du 3 mars 1993 était un : " . 
					   strtolower(joursDeLaSemaine("03/03/1993")). 
					   "</p>";
			?>
			<section>
				<h2>Entrer une date</h2>
				<p><small>Format date :<br> 00 mois 0000 | 00 00 0000 | 00-00-0000 | 00/00/0000</small></p>
				<section>
					<form method="post">
						<section class="wrap_phrase">
							<label for="uneDate">Saisir uneDate : </label>
							<input type="text" name=uneDate aria-label="Saisir une uneDate quelconque" id="uneDate" class="uneDate"></input>
						</section>
						<section>
							<button type="submit">Jour de la semaine</button>
						</section>
					</form>
					<section style="padding:1.5vw;">
						<?php
							echo  $afficheJours;
						?>
					</section>
				</section>
			</section>
		</section>
	</body>
</html>