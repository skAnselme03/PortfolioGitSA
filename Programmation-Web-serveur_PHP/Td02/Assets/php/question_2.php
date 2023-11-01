<!DOCTYPE html>
<html lang="fr">
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<meta http-equiv="X-UA-Compatible" content="ie=edge">
		<meta name="Description" content="Manipulation syntaxe"/>
		<link rel="stylesheet" href="../css/main.css">
		<title>Travail dirigé 01/question 2</title>
	</head>
	<body>
		<header>
			<h1>TD 1: Question 2</h1>
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
				<section class="wrap_phrase">
					<label for="phrase">Saisir phrase : </label>
					<textarea type="text" name=phrase aria-label="Saisir une phrase quelconque" id="phrase" class="phrase"></textarea>
				</section>
				<section>
					<button type="submit">Compter mots</button>
				</section>
			</form>
			<section id="compterMots">
				<?php
					require "./module/pourQuestion_2.php";
					echo "<p>Le nombre de mots dans la phrase \"$phrase\" est : $nombreMots </p>&nbsp;";
				?>

			</section>
		</section>
	</body>
</html>