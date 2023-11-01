<?php
	// Remplir le tableau avec 10 nombres aléatoires
	$numbers = [];
	if ($_SERVER["REQUEST_METHOD"] === "POST") {
		$min = 0;
		$max = 9999;
		$estEntiere = true;

		for ($i = $min; $i < 10; $i++) {
			$nombreAleatoire = genererValAleatoire($min, $max, $estEntiere);
			$numbers[] = $nombreAleatoire ;
		}
		// Trier les valeurs du tableau
		sort($numbers);
	}


?>