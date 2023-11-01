<!DOCTYPE html>
<html lang="fr">
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<meta http-equiv="X-UA-Compatible" content="ie=edge">
		<meta name="Description" content="Manipulation syntaxe"/>
		<link rel="stylesheet" href="../css/main.css">
		<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
		<script src="../js/script.js"></script>
		<title>Travail dirigé 01/question 4</title>
	</head>
	<body>
		<header>
			<h1>TD 1: Question 4</h1>
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
			<button id="afficheStatistique" onclick="document.getElementById('statistique').submit()">Statistique</button>
			<table id="adresseMail" action="./modulePHP/pourQuestion_Stat_4.php">
				<thead>
					<tr>
						<th>
							Adresse mail
						</th>
					</tr>
				</thead>
				<tbody>
					<!--action="pourQuestion_Stat_4.php": fichier PHP qui traitera les données 
						lorsque le formulaire est soumis 
					-->
					<!-- <form id="statistique" method="POST" action="./modulePHP/pourQuestion_Stat_4.php"> -->
					<form id="statistique" method="POST">
						<?php 
							/* include("./genererMailAleatoire.php"); */

							include("./modulePHP/pourQuestion_4.php");
						 ?>
					</form>
				</tbody>
			</table>
			<!-- TABLE NOM SERVEUR -->
			<table id="nomServeur">
				<thead>
					<tr>
						<th>Nom des serveurs</th>
					</tr>
				</thead>
				<tbody>
					<form id="afficheStatistique" method="GET">
						<?php include("./modulePHP/pourQuestion_Stat_4.php"); ?>
					</form>
				</tbody>
			</table>
		</section>
	
	</body>
</html>