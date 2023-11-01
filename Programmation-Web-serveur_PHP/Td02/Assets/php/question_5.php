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
					<label for="mot">Mot de la phrase a enlever: </label>
					<input type="text" name=mot aria-label="Saisir une mot quelconque" id="mot" class="mot"></input>
				</section>
				<section>
					<button type="submit">Analayse phrase</button>
				</section>
			</form>
			<section style="margin-bottom: 1vw;">
				<?php
					require "./module/pourQuestion_5.php";
					echo"<h2>Enlever le <em>$mot</em> dans la phrase saisie</h2>";
					echo"<p>$phraseChange</p>";
				?>
			</section>
		</section>
	</body>
</html>