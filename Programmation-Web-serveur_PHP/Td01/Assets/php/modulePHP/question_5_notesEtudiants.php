<!DOCTYPE html>
<html lang="fr">
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<meta http-equiv="X-UA-Compatible" content="ie=edge">
		<meta name="Description" content="Manipulation syntaxe"/>
		<link rel="stylesheet" href="../../css//main.css">
		<title>Travail dirigé 01/question 5</title>
	</head>
	<body>
		<header>
			<h1>TD 1: Question 5 - Notes Étudiants</h1>
			<nav>
				<a href="../../../index.php">Accueil</a>
				<a href="../question_5.php">Question 5</a>
			</nav>
		</header>
		<section>
			<header>
				<nav>
					<a href="../question_5.php">Note max/min/moyenne</a>
					<a href="./question_5_notesEtudiants.php">Note Étudiants</a>
					<a href="./question_5_Merite.php">Classement</a>
				</nav>
			</header>
			<section>
				<?php
					require("./pourQuestion_5.php");
					
				?>
				<table>
					<thead>
						<tr>
							<th>
								Étudiants
							</th>
							<th>
								Notes
							</th>
						</tr>
					</thead>
					<tbody>
				
							<?php
								foreach ($notesEtudiants as $nom => $note) {
									echo"<tr>
											<td>$nom</td>
											<td>$note</td>
										 </tr>";
								}
							?>
					</tbody>
				</table>
			</section>
		</section>
	</body>
</html>