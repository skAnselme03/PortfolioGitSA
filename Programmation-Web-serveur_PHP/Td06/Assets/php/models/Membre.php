<?php
	class Membre
	{
		/*
		 **********************************
		 *			ATTRIBUTS			  *
		 **********************************
		*/
		private $idMembre;
		private $nom;
		private $prenom;
		private $fullName;
		private $email;
		private $password;

		/*
		 **********************************
		 *			CONSTRUCTEUR		  *
		 **********************************
		*/
		//

		/**
		 * Constructeur de la classe Membre.
		 * Initialise la classe membre.
		 * @param int $idMembre     l'identifiant du membre
		 * @param string $fullName  nom complet du membre
		 * @param string $email     adresse mail du membre
		 * @param string $password  mot de passe du membre
		 */
		public function __construct($idMembre,$fullName,$email, $password ){
			$this->idMembre = $idMembre;
			$this->fullName = $fullName;
			//vérifier l'Adresse mail
			$this->email = $this->validerAdresseEmail($email) ? $email : "";
			$this->password = $password;
			//
			$splitNom = explode(" ", $fullName);
			$this->prenom = $splitNom[0];
			$this->nom = $splitNom[1];
		}
		/**
		 * surchage Constructeur de la classe Membre.
		 * Initialise la classe membre.
		 * @param int $idMembre 	L'identifiant du membre
		 * @param string $nom		nom du membre
		 * @param string $prenom  	prenom du membre
		 * @param string $email     adresse mail du membre
		 * @param string $password  mot de passe du membre
		 */
		//
		public function __construct2($idMembre, $nom, $prenom, $email, $password)
		{
			$this->idMembre = $idMembre;
			$this->nom = $nom;
			$this->prenom = $prenom;
			$this->fullName = $nom . " " . $prenom;
			$this->email = $this->validerAdresseEmail($email) ? $email : "";
			$this->password = $password;
		}


		/*
		 **********************************
		 *			ACCESSEURS			  *
		 **********************************
		*/
		//
		public function getIdMembre()
		{
			return $this->idMembre;
		}
		public function getNom()
		{
			return $this->nom;
		}
		public function getPrenom()
		{
			return $this->prenom;
		}
		public function getFullName()
		{
			return $this->fullName;
		}
		public function getEmail()
		{
			return $this->email;
		}
		public function getPassword()
		{
			return $this->password;
		}
		/*
		 **********************************
		 *			MUTATEURS			  *
		 **********************************
		*/
		//	
		public function setNom($nom)
		{
			$this->nom = $nom;
		}	
		public function setPrenom($prenom)
		{
			$this->prenom = $prenom;
		}	
		public function setEmail($email)
		{
			$this->email = $this->validerAdresseEmail($email) ? $email : "";
		}	
		public function setPassword($password)
		{
			$this->password = $password;
		}
		/*
		 **********************************
		 *			MÉTHODES			  *
		 **********************************
		*/
		//
		/**
		 * Valider un adresse mail
		 * @param string $email email du membre
		 */
		private function validerAdresseEmail($email) {
			// Expression régulière pour valider l'adresse e-mail
			$pattern = '/^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/';
		
			// Vérifier si l'adresse e-mail correspond au modèle
			if (preg_match($pattern, $email)) {
				return true; // Adresse e-mail valide
			} else {
				return false; // Adresse e-mail invalide
			}
		}
		public function toString()
    {
        return "Membre : \n\tidMembre = " . $this->idMembre . "\n\tnom = " . $this->nom . 
						  "\n\t prenom = " . $this->prenom . "\n\t email = " . $this->email;
    }
		
	}
	

?>