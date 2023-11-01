<?php
/* 	include ("../../data/param.php"); */
	include ("../../data/connection.php");
	include("../models/ConnectionBDD.php");
	include("../models/Membre.php");

	$message = "";

	// Vérifie si l'identifiant du continent est présent dans l'URL
	if (!empty($_POST)) {
		$courriel = $_POST['courriel'];
		$motDePasse = $_POST['motDePasse'];
		$member = getMembre($courriel, $motDePasse);

		if($member != null){
			$membre = new Membre($member['id'], $member['full_name'], $member['email'], $member['password']);
			
			//echo "<p class='estOk'>Utilisateur existant</p>";
			session_start(); //on crée la variable de session
			$_SESSION['username'] = $membre->getFullName();
			/* echo $_SESSION['username'];  */
			/* echo $membre->getFullName();*/

			//une méthode pour la redirection vers la page films.php
			header('Location:'.'../views/question_2.php');
		}else{
			echo "<p class='estErreur'>Utilisateur non existant</p>";
		}
	}

	// get membre
	function getMembre($email, $motDePasse){
		$connection = connect();// connection à la BDD
		if($connection != null){
			//requête préparer
			$requete = "SELECT * FROM Member WHERE email =? AND password = ?";
			$stmt = $connection->prepare($requete);//statement
			//filtrer les données envoyées par l'user
			$courriel_filtre = mysqli_real_escape_string($connection, $email);
			$motDePasse_filtre = mysqli_real_escape_string($connection, $motDePasse);
	
			// Lier les paramètres à la requête préparée
			$stmt->bind_param("ss", $courriel_filtre, $motDePasse_filtre);
			// Exécuter la requête préparée
			$stmt->execute();
			// Récupérer le résultat de la requête
			$result = $stmt->get_result();

			if (!$result) {
				$connection->close(); // fermer la connexion
				// Retourner null si la requête ne renvoie aucun résultat
				return null;
			} else {
				if($result->num_rows > 0){
					//return $result->fetch_array(MYSQLI_ASSOC); 
					$member = $result->fetch_array(MYSQLI_ASSOC);
            		$result->free();
					$connection->close(); // fermer la connexion
					return $member;
					//return $result->fetch_array(MYSQLI_ASSOC); 
				}else{
					$connection->close(); // fermer la connexion
					return null;// Retourner null si aucun membre trouvé
				}
			}
		}
		return null;
	}

	/**
	 * Retourner la connection à un BDD
	 */
/* 	function connect()
	{
		$connexion = new mysqli(HOSTNAME, USERNAME, PASSWORD, DATABASENAME,  PORT);
		if (!$connexion) {
			echo "Attention ! Problème de connexion avec la base <br>";
			return null;
		}else
			return $connexion;
	} */


?>