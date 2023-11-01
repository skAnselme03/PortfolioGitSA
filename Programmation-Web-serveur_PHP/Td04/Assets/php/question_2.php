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
				<a href="../../index.php">Accueil</a>
				<a href="./question_1.php">Question 1</a>
				<a href="./question_2.php">Question 2</a>
				<a href="./question_3.php">Question 3</a>
				<a href="./question_4.php">Question 4</a>
				<a href="./question_5.php">Question 5</a>
				<a href="./question_6.php">Question 6</a>
			</nav>
		</header>
		<?php require("./module/pourQuestion_2.php"); ?>
		<section>			
			<h2>Calcul l'aire d'un rectangle</h2>
			<!-- Pour garder les informations dans le formulaire -->
			<form action='<?php echo $_SERVER["PHP_SELF"]; ?>' method="get">
					<fieldset>
						<legend>Factorielle</legend>
						<label for="nombre">Saisir un nombre : </label>
						<input type="text" name=nombre aria-label="valeur de nombre quelconque" id="nombre" class="longueur" type="numbre" min="0"  value="<?php echo $nombre;?>">
						<label for="resultat">Affichage de résultat : </label>
						<input type="text" name=resultat aria-label="valeur de resultat quelconque" id="resultat" class="resultat" value="<?php 
							if($nombre >= 0){
								echo $resultat;
							}
						?>"readonly>
						<button type="submit">Calculer la factorielle</button>
					</fieldset>
			</form>
		</section>
		<section>
		
			<br>
		</section>
	</body>
</html>