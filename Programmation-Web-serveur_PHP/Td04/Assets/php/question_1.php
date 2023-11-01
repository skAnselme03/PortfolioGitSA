<!DOCTYPE html>
<html lang="fr">
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<meta http-equiv="X-UA-Compatible" content="ie=edge">
		<meta name="Description" content="Manipulation syntaxe"/>
		<link rel="stylesheet" href="../css/main.css">
		<title>Travail dirigé 01/question 1</title>
	</head>
	<body>
		<?php
			require("./module/nombreAleatoires.php");
		?>
		<header>
			<h1>TD 1: Question 1</h1>
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
		<section>
			<h2>Calcul l'aire d'un rectangle</h2>
			<form action='<?php echo $_SERVER["PHP_SELF"]; ?>' method="get">
				<section>
					<label for="longueur">Longueur : </label>
					<input type="text" name=longueur aria-label="valeur de longueur quelconque" id="longueur" class="longueur" value="<?php
						echo genererValAleatoire(0, 500, true);
					?>" readonly>
					<span>cm</span>
				</section>
				<section>
					<label for="largeur">Largeur : </label>
					<input type="text" name=largeur aria-label="valeur de largeur quelconque" id="largeur" class="largeur" value="<?php
						echo genererValAleatoire(0, 500, true);
					?>" readonly>
					<span>cm</span>
				</section>
				<section>
					<button type="submit">Calculer</button>
				</section>
			</form>
		</section>
		<section>
			<?php
				require("./module/pourQuestion_1.php");
				if($aire_rectangle != 0){
					echo "<p>L'aire du rectangle $longueur x  $largeur est : $aire_rectangle cm&sup2</p>";
				}
			?>
			<br>
		</section>
	</body>
</html>