<?php
	require ("../../data/param.php");
	include("../models/ConnectionBDD.php");
	include("../models/Membre.php");
	$message = "";
	/* $id=0;
	$nom="";
	$email="";
	$pwd=""; */

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
			//une méthode pour la redirection vers la page films.php
			header('Location:'.'../views/question_2.php');
		}else{
			echo "<p class='estErreur'>Utilisateur non existant</p>";
		}

		/* foreach ($membre as $key => $value) {
			echo $value . "<br>";
		} */

	/* 		if($membre != null){
			//aller à la page de films
			//header('Location:'.'../views/question_2.php');
			//créer une session et placer l'user dans une variable de session
			session_start();
			$_SESSION['username'] = $membre;
			/* foreach ($membre as $key => $value) {
				echo $value . "<br>";
			}


		}else{
			$message = 'Attention, vérifier vos données';   
		} */
	/* 	$membre = null;
		if ($ligne = $infosMembre) {
			$membre = new Membre($ligne[0], $ligne[1], $ligne[2], $ligne[3]);
		}
		$id = $membre->getIdMembre()."\n";
		$nom=$membre->getFullName()."\n";
		$email = $membre->getEmail()."\n";
		$pwd=$membre->getPassword()."\n"; 
		$message = "ID: $id\tNom: $nom\temail: $email\pwd: $pwd";*/
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
				// Retourner null si la requête ne renvoie aucun résultat
				return null;
			} else {
				if($result->num_rows > 0){
					/* //todo : test
					$membres = array();

					while ($row = $result->fetch_assoc()) {
						$membres[] = $row; // Ajouter chaque membre trouvé au tableau de membres
					}
					//trest pour afficher
					foreach ($membres as $key => $value) {
						echo $value['id'].": ".$value['full_name'].": ".$value['email'];
					}
					//Todo: fin */
				// Retourner le premier membre trouvé sous forme de tableau associatif
					return $result->fetch_array(MYSQLI_ASSOC); 
				}else{
					return null;// Retourner null si aucun membre trouvé
				}
			}
		}
		return null;
	}

	/**
	 * Retourner la connection à un BDD
	 */
	function connect()
	{
		$connexion = new mysqli(HOSTNAME, USERNAME, PASSWORD, DATABASENAME,  PORT);
		if (!$connexion) {
			echo "Attention ! Problème de connexion avec la base <br>";
			return null;
		}else
			return $connexion;
	}


?>