<?php
	/**
	 * Calculer l'aire d'un rectangle
	 * @param mixed $longueur du rectangle
	 * @param mixed $largeur du rectangle
	 * @return {double} l'aire du rectangle
	 */
	function calcul_aireRectangle($longueur, $largeur){
		return $longueur * $largeur;
	}

	/**
	 * Calculer le factorielle d'un nombre entier une approche récursive
	 * @param mixed $entier
	 * @return mixed entier
	 */
	function factorielle($entier){
		// Si le nombre est égal à 0 ou 1, retourner 1 (cas de base).
		// Condition de sortie 
		if ($entier === 0 || $entier === 1) {
			return 1;
		} else {
			/**
			 * Sinon, multiplier le nombre par la factorielle du nombre
			 * précédent (récursion).
			 * 
			 * Elle utilise la récursion pour calculer 
			 * la factorielle du nombre en multipliant le nombre 
			 * lui-même par la factorielle du nombre précédent 
			 * (c'est-à-dire $n * factorielle($n - 1)).
			 */
			return $entier * factorielle($entier - 1);
		}
	}
?>