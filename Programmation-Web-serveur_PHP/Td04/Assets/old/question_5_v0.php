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
	</body>
	<?php
		require("./module/pourQuestion_5.php");
	?>
	<section>
			<h2>Bureau de vote</h2>
			<!-- Pour garder les informations dans le formulaire -->
			<form action='<?php echo $_SERVER["PHP_SELF"]; ?>' method="get">
					<fieldset>
						<label for="nom">Nom</label>
						<input type="text" name=nom aria-label="valeur de nom quelconque" id="nom" class="longueur" type="numbre" min="0"  value="<?php echo isset($nom) ? $nom : ''; ?>"placeholder="Votre nom">
						<label for="prenom">Prenom</label>
						<input type="text" name=prenom aria-label="valeur de prenom quelconque" id="prenom" class="prenom" value="<?php echo isset($prenom) ? $prenom : ''; ?>" placeholder="votre prénom">
						<label for="adresse">Adresse</label>
						<input type="text" name=adresse aria-label="valeur de adresse quelconque" id="adresse" class="adresse" value="<?php echo isset($adresse) ? $adresse : ''; ?>" placeholder="votre adresse">
						<hr>
						<label for="bureauDeVote">Bureau de vote</label>
						<select name="bureauDeVote" id="bureauDeVote" class="bureauDeVote">
							<option value="">-- Choisir un bureau de vote --</option>
							<option value="Jean-Talon" <?php echo $bureauDeVote === 'Jean-Talon' ? 'selected' : ''; ?>>Jean-Talon</option>
							<option value="Saint-Laurent" <?php echo $bureauDeVote === 'Saint-Laurent' ? 'selected' : ''; ?>>Saint-Laurent</option>
							<option value="Rosemont" <?php echo $bureauDeVote === 'Rosemont' ? 'selected' : ''; ?>>Rosemont</option>
							<option value="Longueuil" <?php echo $bureauDeVote === 'Longueuil' ? 'selected' : ''; ?>>Longueuil</option>
							<option value="Brossard" <?php echo $bureauDeVote === 'Brossard' ? 'selected' : ''; ?>>Brossard</option>
							<option value="Laval" <?php echo $bureauDeVote === 'Laval' ? 'selected' : ''; ?>>Laval</option>
						</select>

						<section>
						<label for="voter">Bureau de vote</label>
						<input type="checkbox" name="voter" id="voter" class="voter" <?php echo isset($aVoter) && $aVoter ? 'checked' : ''; ?>>
						
						</section>
						<button type="submit">Soumettre</button>
					</fieldset>
			</form>
	</section>
	<section>
		<?php 
			echo "Nom : " . $nom . "<br>";
			echo "Prénom : " . $prenom . "<br>";
			echo "Adresse : " . $adresse . "<br>";
			echo "Bureau de vote : " . $bureauDeVote . "<br>";
			echo "A voter : " . ($aVoter ? 'Oui' : 'Non') . "<br>";
		?>
	</section>
</html>