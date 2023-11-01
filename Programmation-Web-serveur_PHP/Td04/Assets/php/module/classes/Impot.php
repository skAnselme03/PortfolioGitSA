<?php
	class Impot{
		/*---------------------------------------- */
		/*	Définitions des propriétés (Attributs) */
		/*---------------------------------------- */
		const TAUX_15 = 0.15;
		const TAUX_25 = 0.25;
		private $nom;
		private $revenu;
		private $montant_impot = 0;

		/*---------------------------------------- */
		/*				Constructeur			   */
		/*---------------------------------------- */
		public function __construct($nom, $revenu) {			
			$this->nom = $nom;
			$this->revenu = $revenu;
		}

		/*---------------------------------------- */
		/*			Définitions des méthodes	   */
		/*---------------------------------------- */
		public function afficher_impot(){
			echo "$this->nom, votre impôt cette année est de $this->montant_impot";
		}

		public function calcul_impot(){
			if($this->revenu<=30000){
				$this->montant_impot = $this->revenu * self::TAUX_15;
			}else{
				$this->montant_impot = $this->revenu * self::TAUX_25;
			}
		}
	}
?>