<?php
	/* include ("../../data/param.php"); */
	include ("../../data/connection.php");
	include("../models/ConnectionBDD.php");
	include("../models/Moovie.php");
	

	$username = "";
	//tableau de film
	$films = array();
	session_start();
	echo $_SESSION['username'];

	//Récupérer la variable de session
	if(isset($_SESSION['username'])){
		$username = $_SESSION['username'];
		echo $username;
		//
		$result = getMoovies();
	/* 	if ($result != null) {
			while ($ligne = $result->fetch_array(MYSQLI_ASSOC)) {
				$film = new Moovie($ligne['id'], $ligne['title'], $ligne['genre'], $ligne['releaseDate'], $ligne['price']);
				array_push($films, $film);
			}
			$result->free(); // Libérer le résultat de la requête
		} */
		$ligne = getMoovies();
		while($ligne){
			echo $ligne['id'];
			/* $film = new Moovie($ligne['id'], $ligne['title'], $ligne['genre'], $ligne['releaseDate'], $ligne['price']);
			array_push($films, $film); */
		}
	}

	//get moovie
	function getMoovies(){
		$connection = connect();// connection à la BDD
		if($connection != null){
			//requête préparer
			$requete = "SELECT * FROM Movie";
			$result = $connection->query($requete);//statement
			if (!$result) {
				$connection->close(); // fermer la connexion
				// Retourner null si la requête ne renvoie aucun résultat
				return null;
			} else {
				if($result->num_rows > 0){
					//return $result->fetch_array(MYSQLI_ASSOC); 
					$moovies = $result->fetch_array(MYSQLI_ASSOC);
            		$result->free();
					$connection->close(); // fermer la connexion
					return $moovies;
					//return $result->fetch_array(MYSQLI_ASSOC); 
				}else{
					$connection->close(); // fermer la connexion
					return null;// Retourner null si aucun membre trouvé
				}
			}
			/* if (!$result) {
				$connection->close();
				return null;
			} else {
				if ($result->num_rows > 0) {
					return $result;
				} else {
					$connection->close();
					return null;
				}
			} */
		/* 	if (!$result) {
				$connection->close(); // fermer la connexion
				// Retourner null si la requête ne renvoie aucun résultat
				return null;
			} else {
				if($result->num_rows > 0){
					//return $result->fetch_array(MYSQLI_ASSOC); 
					$movies = $result->fetch_array(MYSQLI_NUM);
					$result->free();
					$connection->close(); // fermer la connexion
					return $movies;
				}else{
					$connection->close(); // fermer la connexion
					return null;// Retourner null si aucun membre trouvé
				}
			} */
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