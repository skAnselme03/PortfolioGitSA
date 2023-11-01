<?php
	//tableau associatif qui contient les notes des étudiants
	$notesEtudiants = array(
		"Jean" => 15,
		"Marie" => 18,
		"Pierre" => 12,
		"Sophie" => 16,
		"Luc" => 14,
		"Titi" => 20,
		"Gros-Minet" => 11,
		"Tom" => 12,
		"Jerry" => 19,
		"Bugs Bunny" => 20
	);
	$noteMax = max($notesEtudiants);
	$noteMin = min($notesEtudiants);

	// Calculer la somme des notes
	$somme = array_sum($notesEtudiants);

	// Calculer le nombre total d'étudiants
	$nombreEtudiants = count($notesEtudiants);

	// Calculer la moyenne
	$moyenne = $somme / $nombreEtudiants;

	/* echo'<section class="note_etudiant">';
	echo "<p>La note maximale du groupe est : $noteMax</p>";
	echo "<p>La note minimale du groupe est : $noteMin</p>";
	echo "<p>La moyenne du groupe est : $moyenne</p>";
	echo'</section>'; */

	// tableau trié
	ksort($notesEtudiants);


?>