<?php
	class ConnectBDD
	{
		/*
		 **********************************
		 *				ATTRIBUTS		  *
		 **********************************
		*/
		private $connection;
		private $pageRedirect;

		/*
		 **********************************
		 *			CONSTRUCTEUR		  *
		 **********************************
		*/
		//
		/**
		 * Constructeur de la classe ConnectBDD.
		 * Initialise la connexion à la base de données.
		 * Lance une exception en cas d'erreur de connexion.
		 *
		 * @param string $hostname     Nom d'hôte du serveur de la base de données
		 * @param string $username     Nom d'utilisateur de la base de données
		 * @param string $password     Mot de passe de la base de données
		 * @param string $donneesbaseName Nom de la base de données
		 * @param int    $port         Numéro du port de la base de données
		 * @param string    $pageRedirect   Lien de la page de redirection en cas de problème de connection
		 */
		public function __construct($hostname, $username, $password, $donneesbaseName, $port, $pageRedirect="") {
			$this->connection = new mysqli($hostname, $username, $password, $donneesbaseName, $port);
	
			 // Vérification de la connexion
			 if ($this->connection->connect_errno) {
				header("Location: " . $pageRedirect); // Redirection vers une page données
				exit(); // Arrête l'exécution du script
			}
		}

		/*
		 **********************************
		 *			ACCESSEURS			  *
		 **********************************
		*/
		//
		/**
		 * Obtient l'objet de connexion à la base de données.
		 *
		 * @return mysqli Objet de connexion à la base de données
		 */
		public function getConnection() {
			return $this->connection;
		}
		/**
		 * Obtient l'objet de pageRedirect à la base de données.
		 *
		 * @return mysqli Objet de pageRedirect à la base de données
		 */
		public function getpageRedirect() {
			return $this->pageRedirect;
		}

		/*
		 **********************************
		 *			MUTATEURS			  *
		 **********************************
		*/
		//
		/**
		 * Modifie l'objet de connexion à la base de données.
		 *
		 * @param mysqli $newConnection Nouvel objet de connexion
		 */
		public function setConnection($newConnection) {
			$this->connection = $newConnection;
		}
	
		/**
		 * Modifie l'objet de connexion à la base de données.
		 *
		 * @param mysqli $newpageRedirect Nouvel objet de connexion
		 */
		public function setpageRedirect($newpageRedirect) {
			$this->pageRedirect = $newpageRedirect;
		}

		/*
		 **********************************
		 *			MÉTHODES			  *
		 **********************************
		*/
		//

		/**
		 * Exécute une requête SELECT et retourne les données résultantes.
		 *
		 * @param string $requete Requête SELECT à exécuter
		 * @param array $parametres parametres de selection
		 * @return array Tableau associatif contenant les données
		 * @throws Exception En cas d'erreur lors de l'exécution de la requête
		 */
		public function selectData($requete, $parametres = array()) {
			 // Prépare la requête SQL
			 $statement = $this->connection->prepare($requete);

			 if (!$statement) {
				 /* header("Location: " . $this->pageRedirect); // Redirection vers une page données */
				 return null;
			 }
		 
			 if (!empty($parametres)) {
				 $bindParams = array();
				 $types = "";
		 
			/* 	 // Crée un tableau des paramètres avec les valeurs
				 foreach ($parametres as $param) {
					 $bindParams[] = $param;
					echo $param . " ";
				 } */
				// Crée un tableau des paramètres avec les références
				foreach ($parametres as $key => $value) {
					// Obtient le premier caractère du type (par exemple, "s" pour une chaîne de caractères, "i" pour un entier)
					$types .= gettype($value)[0]; 
					$bindParams[$key] = &$parametres[$key];
				}
				
				// Ajoute la chaîne de définition de type en tant que premier élément du tableau
				array_unshift($bindParams, $types); 

				// Lie les paramètres à la requête en utilisant call_user_func_array()
				call_user_func_array(array($statement, 'bind_param'), $bindParams);
    
			 }
		 
			 $statement->execute(); // Exécute la requête
			 
			 // Récupère le résultat de la requête
			 $result = $statement->get_result();
			 $donnees = [];
		 
			 // Parcourt chaque ligne du résultat et les ajoute au tableau $donnees
			 while ($ligne = $result->fetch_array(MYSQLI_ASSOC)) {
				 array_push($donnees, $ligne);
			 }
		 
			 $result->close();
		 
			 return $donnees;
		}

		/**
		 * Exécute une requête INSERT et retourne l'ID de la nouvelle ligne insérée.
		 *
		 * @param string $requete Requête INSERT à exécuter
		 * @return int ID de la nouvelle ligne insérée
		 * @throws Exception En cas d'erreur lors de l'exécution de la requête
		 */
		public function insertData($requete) {
			$result = $this->connection->query($requete);
	
			if (!$result) {
				/* header("Location: " . $this->pageRedirect); // Redirection vers une page données*/
				return null;
			}
	
			return $this->connection->insert_id;
		}
	
		/**
		 * Exécute une requête UPDATE et retourne le nombre de lignes affectées.
		 *
		 * @param string $requete Requête UPDATE à exécuter
		 * @return int Nombre de lignes affectées
		 * @throws Exception En cas d'erreur lors de l'exécution de la requête
		 */
		public function updateData($requete) {
			$result = $this->connection->query($requete);
	
			if (!$result) {
				header("Location: " . $this->pageRedirect); // Redirection vers une page données
			}
	
			return $this->connection->affected_rows;
		}
	
		/**
		 * Exécute une requête DELETE et retourne le nombre de lignes affectées.
		 * (ATTENTION: SET LA PAGE DE REDIRECTION AVANT D'UTILISER CETTE FONCTION)
		 * @param string $requete Requête DELETE à exécuter
		 * @param string $verification Requête SELECT pour vérifier l'existence des données
		 * @return int Nombre de lignes affectées
		 * @throws Exception En cas d'erreur lors de l'exécution de la requête
		 */
		public function deleteData($requete, $verification) {
			
			 // Vérifier l'existence des données
			 $resultVerification = $this->connection->query($verification);
			// Rediriger si les données n'existe pas dans la bdd ou
			// qu'il y'a une Erreur lors de l'exécution de la requête de vérification 
			 if (!$resultVerification or ($resultVerification->num_rows === 0)) {
				header("Location: " . $this->pageRedirect); // Redirection vers une page données
				exit();
			}

			 // Exécuter la requête DELETE
			$result = $this->connection->query($requete);
	
			//Rediriger s'il y'a une Erreur lors de l'exécution de la requête de vérification 
			if (!$result) {
				header("Location: " . $this->pageRedirect); // Redirection vers une page données
			}
	
			return $this->connection->affected_rows;
		}
	
		/**
		 * Exécute une requête POST (INSERT) pour insérer des données dans une table.
		 *
		 * @param string $table Nom de la table
		 * @param array  $donnees  Tableau associatif des données à insérer
		 * @return int ID de la nouvelle ligne insérée
		 * @throws Exception En cas d'erreur lors de l'exécution de la requête
		 */
		public function post($table, $donnees) {
			$columns = implode(", ", array_keys($donnees));
			$values = implode("', '", array_values($donnees));
			$requete = "INSERT INTO $table ($columns) VALUES ('$values')";
	
			return $this->insertData($requete);
		}
	
		/**
		 * Exécute une requête GET (SELECT) pour récupérer les données d'une table.
		 *
		 * @param string $table     Nom de la table
		 * @param string $condition Condition de la requête (facultatif)
 		* @param array $params     Paramètres de la requête (facultatif)
		 * @return array Tableau associatif contenant les données
		 * @throws Exception En cas d'erreur lors de l'exécution de la requête
		 */
		public function get($table, $condition = "", $params = array()) {
			$requete = "SELECT * FROM $table";
			// Tableau pour stocker les paramètres
			$parametres = array();
			if ($condition) {
				//ajouter la condition à la requête
				/* $requete .= " WHERE $condition"; */
				$requete .= " WHERE $condition";

				// Ajouter les valeurs de condition aux paramètres
				$conditionParts = explode(" ", $condition);
				foreach ($conditionParts as $part) {
					$parametres[] = $part;
				}
			}
			echo "<br>";
			 // Ajouter les paramètres spécifiés pour la requête préparer
			 foreach ($params as $param) {
				$parametres[] = $param;
			}
		// Appelle la fonction selectData() avec la requête et les paramètres
			return $this->selectData($requete, $parametres);
		}
	
		/**
		 * Exécute une requête PUT (UPDATE) pour modifier des données dans une table.
		 *
		 * @param string $table Nom de la table
		 * @param array  $donnees  Tableau associatif des données à mettre à jour
		 * @param string $condition Condition de la requête (facultatif)
		 * @return int Nombre de lignes affectées
		 * @throws Exception En cas d'erreur lors de l'exécution de la requête
		 */
		public function put($table, $donnees, $condition = "") {
			$updates = [];
			foreach ($donnees as $column => $value) {
				$updates[] = "$column = '$value'";
			}
	
			$updatesStr = implode(", ", $updates);
			$requete = "UPDATE $table SET $updatesStr";
			echo($requete) . "<br>";
			if ($condition) {
				$requete .= " WHERE $condition";
			}
			echo($requete);
	
			return $this->updateData($requete);
		}
		/**
		 * Ferme la connexion à la base de données.
		 */
		public function closeConnection() {
			$this->connection->close();
		}

	}
	

?>