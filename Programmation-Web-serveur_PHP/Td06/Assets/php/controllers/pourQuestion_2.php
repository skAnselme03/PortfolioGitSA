<?php
	/* include ("../../data/param.php"); */
	include ("../../data/connection.php");
	include("../models/ConnectionBDD.php");
	include("../models/Moovie.php");
	

	$username = "";
	//tableau de film
/* 	$films = null; */
	session_start();
	/* echo $_SESSION['username']; */

	//Récupérer la variable de session
	if(isset($_SESSION['username'])){
		$username = $_SESSION['username'];
		/* echo $username; */
		//
		/* $films = getMoovies(); */
		
	}

	//get moovie
	function getMoovies(){
		$connection = connect();// connection à la BDD
		if($connection != null){
			//requête préparer
			$requete = "SELECT * FROM Movie";
			$result = $connection->query($requete);//statement
			if (!$result) {
				echo "Attention ! Problème de requête SELECT <br>";
				return null;
			} else {
				$movies = array();
				while ($ligne = $result->fetch_array(MYSQLI_NUM)) {
					$movie = new Moovie($ligne[0], $ligne[1], $ligne[2], $ligne[3], $ligne[4]);
					array_push($movies, $movie);
				}
				$result->free();
				$connection->close();
				return $movies;
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