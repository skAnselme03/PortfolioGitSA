<!DOCTYPE html>
<html lang="fr">
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<meta http-equiv="X-UA-Compatible" content="ie=edge">
		<meta name="Description" content="Manipulation syntaxe"/>
		<link rel="stylesheet" href="../../css/main.css">
		<title>Travail dirigé 05/question 4</title>
	</head>
	<body>
		<header>
			<h1>TD 5: Question 4</h1>
			<nav>
				<a href="../../../index.php">Accueil</a>
				<a href="./continents.php">Liste des continents</a>
				<!-- <a href="./pays.php">Liste des pays</a>
				<a href="./villes.php">Liste des villes</a> -->
			<!-- 	<a href="./modifier_ville.php">Modifier villes</a>
				<a href="./supprimer_ville.php">Supprimer villes</a> -->
			</nav>
		</header>
		<?php require("../controllers/pourVilles.php");?>
		<section>
			<h2>Liste des villes :  <?php echo $nomPays?></h2>
			<table>
				<thead>
					<tr>
						<th>Villes</th>
						<th>Est Capital</th>
						<th>Traitements</th>
					</tr>
				</thead>
				<tbody>
					<?php
						foreach($villesList as $key => $value)
						{
							$idVille = $value['ID'];
							// Récupère le nom du pays de la valeur actuelle dans la boucle
							$nomVille = $value['Name'];
							/* echo($nomVille); */
							$estCapital = $value['IsCapital'];
							$countryName = $value['CountryName'];
							/* echo($estCapital); */
							echo "<tr>";
							echo "<td>" . $nomVille . "</td>"; //nom de la ville
							if ($estCapital == 1) {
							echo "<td>Oui</td>"; //la ville est la capital du pays
							}else{
								echo "<td>Non</td>"; //la ville n'est pas la capital du pays
							}
							echo "<td class='method-postOrGet'>";
							// Définit un formulaire avec la méthode POST pour envoyer les informations d'une villes à traiter
							echo "
								<form method='POST' action='modifier_ville.php'>
									<input type='hidden' name='idVille' value='$idVille'>
									<input type='hidden' name='nomVille' value='$nomVille'>
									<input type='hidden' name='estCapital' value='$estCapital'>
									<input type='hidden' name='countryName' value='$countryName'>
									<input type='hidden' name='action' value='modifier'>
									<button type='submit' class='lien lien_voir'>Modifier</button>
								</form>
							";
							echo "
								<form method='POST' action='supprimer_ville.php'>
									<input type='hidden' name='idVille' value='$idVille'>
									<input type='hidden' name='nomVille' value='$nomVille'>
									<input type='hidden' name='estCapital' value='$estCapital'>
									<input type='hidden' name='countryName' value='$countryName'>
									<input type='hidden' name='action' value='supprimer'>
									<button type='submit' class='lien lien_voir'>Supprimer</button>
								</form>
							";
							echo "</td>"; // Ferme la cellule du tableau
							echo "</tr>";
						}
						flush(); // Vide le tampon de sortie pour libérer la mémoire
					?>
				</tbody>
			</table>
		</section>
	</body>
</html>