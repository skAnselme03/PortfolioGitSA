<?php
	//classe hérité de la classe personne
	class Client extends Personne{

		/*---------------------------------------- */
		/*	Définitions des propriétés (Attributs) */
		/*---------------------------------------- */
		public $adresse;
		/*---------------------------------------- */
		/*				Constructeur			   */
		/*---------------------------------------- */

		public function __construct($nom, $prenom, $adresse) {
			parent::__construct($nom, $prenom);
			$this->adresse = $adresse;
		}
		/*---------------------------------------- */
		/*			Définitions des méthodes	   */
		/*---------------------------------------- */
		public function setCoord(){
			echo parent::__toString()." habite à l'adresse suivant : $this->adresse";
		}
	}
?>