<?php

/**
 * Mettre en majuscule le premier caractère d'un mot
 * @param mixed $mot : mot à transformer
 * @return mixed mot avec première lettre en majuscule
 */
	function majFirstLetter($mot){
		return  ucfirst(strtolower($mot));
	}

?>