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
			<h1>TD 1: Question 6</h1>
			<nav>
				<a href="../../index.php">Accueil</a>
				<a href="./question_1.php">Question 1</a>
				<a href="./question_2.php">Question 2</a>
				<a href="./question_3.php">Question 3</a>
				<a href="./question_4.php">Question 4</a>
				<a href="./question_5.php">Question 5</a>
				<a href="./question_6.php">Question 6</a>
			</nav>
		</header>
	</body>
	<?php ?>
	<section>
		<form action="<?php echo $_SERVER['PHP_SELF'] ?>" method="get">
			<fieldset>
				<legend>Calcul d'impôts</legend>
				<label for="nom">Saisir votre nom</label>
				<input type="text" name="nom">
				<label for="revenu">Saisir votre revenu annuel</label>
				<input type="text" name="revenu">
				<button type="submit" name="btnCalculer">Calculer</button>
			</fieldset>
		</form>
	</section>
	<section>
		<?php
			require("./module/pourQuestion_6.php");
		?>
		<br>
		<br>
	</section>
</html>