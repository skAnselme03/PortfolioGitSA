<?php

	function joursDeLaSemaine($dateFournis){
		// Obtenir le jour de la semaine
		$jourSemaine = date("N", strtotime($dateFournis));

		// Tableau des jours de la semaine en français
		$jours = array(
			1 => "Lundi",
			2 => "Mardi",
			3 => "Mercredi",
			4 => "Jeudi",
			5 => "Vendredi",
			6 => "Samedi",
			7 => "Dimanche"
		);
		return $jours[$jourSemaine];

	}
?>