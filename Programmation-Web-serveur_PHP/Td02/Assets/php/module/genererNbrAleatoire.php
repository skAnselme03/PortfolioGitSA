<?php
		/**
	 *  Fonction permettant de générer un nombre
	 * aléatoire entre un minimum et un maximum
	 * 
	 * @param {int } $min : valeur minimum 
	 * @param {int }  $max : valeur maximum
	 *@param {bool} $estEntiere : retourne une valeur entière 
	 *							  si vrai, décimal si faux 
	 * 
	 * @return {int} nombre aléatoire
	 */
	function genererValAleatoire($min, $max, $estReel){
		$nbrFloatAleatoire = mt_rand($min, $max);
		$nbrIntAleatoire = rand($min, $max);

		if (!$estReel) {
			return $nbrFloatAleatoire;
		}

		return $nbrIntAleatoire;
	}

?>