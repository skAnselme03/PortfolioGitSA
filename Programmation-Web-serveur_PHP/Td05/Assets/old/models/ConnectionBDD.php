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
		public function __construct($hostname, $username, $password, $donneesbaseName, $port, $pageRedirect) {
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
		 * @return array Tableau associatif contenant les données
		 * @throws Exception En cas d'erreur lors de l'exécution de la requête
		 */
		public function selectData($requete) {
			$result = $this->connection->query($requete);

			if (!$result) {
				header("Location: " . $this->pageRedirect); // Redirection vers une page données
			}

			$donnees = [];
		/* 	while ($row = $result->fetch_assoc()) {
				$donnees[] = $row;
			} */
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
				header("Location: " . $this->pageRedirect); // Redirection vers une page données
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
		 *
		 * @param string $requete Requête DELETE à exécuter
		 * @return int Nombre de lignes affectées
		 * @throws Exception En cas d'erreur lors de l'exécution de la requête
		 */
		public function deleteData($requete) {
			$result = $this->connection->query($requete);
	
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
		 * @return array Tableau associatif contenant les données
		 * @throws Exception En cas d'erreur lors de l'exécution de la requête
		 */
		public function get($table, $condition = "") {
			$requete = "SELECT * FROM $table";
	
			if ($condition) {
				$requete .= " WHERE $condition";
			}
	
			return $this->selectData($requete);
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
	
			if ($condition) {
				$requete .= " WHERE $condition";
			}
	
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