<?php
	class Ville{
		/*---------------------------------------- */
		/*	Définitions des propriétés (Attributs) */
		/*---------------------------------------- */
		private $nom;
		private $departement;
		/*---------------------------------------- */
		/*				Constructeur			   */
		/*---------------------------------------- */
		public function __construct($nom, $departement ){
			$this->nom = $nom;
			$this->departement = $departement;
		}
		/*---------------------------------------- */
		/*			Définitions des méthodes	   */
		/*---------------------------------------- */
		public function afficherMessage(){
			echo "La ville $this->nom  est dans le département $this->departement ";
		}
	}
?>