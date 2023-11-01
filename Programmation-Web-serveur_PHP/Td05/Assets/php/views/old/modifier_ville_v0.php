<!DOCTYPE html>
<html lang="fr">
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<meta http-equiv="X-UA-Compatible" content="ie=edge">
		<meta name="Description" content="Manipulation syntaxe"/>
		<link rel="stylesheet" href="../../css/main.css">

		<title>Travail dirigé 05/question 5</title>
	</head>
	<body>
		<header>
			<h1>TD 5: Question 5</h1>
			<nav>
				<a href="../../../index.php">Accueil</a>
				<a href="./continents.php">Liste des continents</a>
				<!-- <a href="./pays.php">Liste des pays</a>
				<a href="./villes.php">Liste des villes</a> -->
				<a href="./modifier_ville.php">Modifier villes</a>
				<a href="./supprimer_ville.php">Supprimer villes</a>
			</nav>
		</header>
		<?php require("../controllers/pourModifierVille.php");?>
		<section>
			<h2>Mise à jour ville : <?php echo $nomVille?></h2>			
			<form action="<?php echo $_SERVER['PHP_SELF']; ?>" method="POST">
				<fieldset>
					<input type="hidden" name="idVille" value="<?php echo isset($_POST['idVille']) ? $_POST['idVille'] : ''; ?>">
					
					<label for="nomVille">Ville : </label>
					<input name="nomVille" value="<?php echo isset($_POST['nomVille']) ? $_POST['nomVille'] : ''; ?>" placeholder="Nom de la ville">

					<label for="estCapital">Est capital (0 non, 1 oui) : </label>
					<input type="number" min="0" max="1" name="estCapital" value="<?php echo isset($_POST['estCapital']) ? $_POST['estCapital'] : ''; ?>">
					<label for="countryName">Nom du pays : </label>
					<input name="countryName" value="<?php echo isset($_POST['countryName']) ? $_POST['countryName'] : ''; ?>" placeholder="Nom du pays">
					<section>
						<!-- <input type="hidden" name="update" value="modifier"> -->
						<button type="submit" name="update" class="lien lien_voir">Modifier</button>
						<!-- <input type="hidden" name="action" value="annuler"> -->
						<button type="submit" name="cancel" class="lien lien_voir">Annuler</button>
					</section>
				</fieldset>
			</form>
		</section>
	</body>
</html>