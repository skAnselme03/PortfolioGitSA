<?php
	$auteurs = ['Victor Hugo', 'Charles Baudelaire', 'Arthur Rimbaud', 'Paul Verlaine', 'Victor Hugo', 'Gary Victor', 'Emile Roumer', 'Victor Hugo'];
	//suppresion de doublon
	$auteurs = array_unique($auteurs);
	//tri du tableau
	sort($auteurs);

		// Lorsque le bouton est cliqué, le formulaire est soumis et 
		//la condition isset($_POST['masquer_doublon']) est vérifiée pour 
		//déterminer si les doublons doivent être masqués ou non.
	if (isset($_POST['masquer_doublon'])) {
		// Afficher le tableau sans doublons
		foreach ($auteurs as $nom) {
			echo "<tr><td>$nom</td></tr>";
		}
	} else {
		// Afficher le tableau avec doublons
		for ($i = 0; $i < count($auteurs); $i++) {
			echo "<tr><td>$auteurs[$i]</td></tr>";
		}
	}
?>

