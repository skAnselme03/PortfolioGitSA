<!DOCTYPE html>
<html lang="fr">
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<meta http-equiv="X-UA-Compatible" content="ie=edge">
		<meta name="Description" content="Manipulation syntaxe"/>
		<link rel="stylesheet" href="../css/main.css">
		<title>Travail dirigé 01/question 5</title>
	</head>
	<body>
		<header>
			<h1>TD 1: Question 5</h1>
			<nav>
				<a href="../../index.php">Accueil</a>
				<a href="./question_1.php">Question 1</a>
				<a href="./question_2.php">Question 2</a>
				<a href="./question_3.php">Question 3</a>
				<a href="./question_4.php">Question 4</a>
				<a href="./question_5.php">Question 5</a>
			</nav>
		</header>
		<section>
			<header>
				<nav>
					<a href="./question_5.php">Note max/min/moyenne</a>
					<a href="./modulePHP/question_5_notesEtudiants.php">Note Étudiants</a>
					<a href="./modulePHP/question_5_Merite.php">Classement</a>

				</nav>
			</header>
			<section class="note_etudiant">
				<?php
					require("./modulePHP/pourQuestion_5.php");
					echo "<p>La note maximale du groupe est : $noteMax</p>";
					echo "<p>La note minimale du groupe est : $noteMin</p>";
					echo "<p>La moyenne du groupe est : $moyenne</p>";
				?>
		</section>
	</body>
</html>