<?php
	//Définition de la classe abstraite Personne
	abstract class Personne
	{

		/*---------------------------------------- */
		/*	Définitions des propriétés (Attributs) */
		/*---------------------------------------- */
		protected $nom;
		protected $prenom;

		/*---------------------------------------- */
		/*				Constructeur			   */
		/*---------------------------------------- */
		public function __construct($nom, $prenom)
		{
			$this->nom = $nom;
			$this->prenom = $prenom;
		}
		/*---------------------------------------- */
		/*			Définitions des méthodes	   */
		/*---------------------------------------- */
	
		public function __toString()
		{
			return "Personne [$this->prenom $this->nom]";
		}
	}
	

?>