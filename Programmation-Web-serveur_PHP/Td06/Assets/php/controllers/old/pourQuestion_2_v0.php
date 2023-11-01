<?php
	session_start();


	//get moovie
	function getMoovies(){
		$connection = connect();// connection à la BDD
		if($connection != null){
			//requête préparer
			$requete = "SELECT * FROM Movie";
			$result = $connection->query($requete);//statement

			if (!$result) {
				// Retourner null si la requête ne renvoie aucun résultat
				return null;
			} else {
				if($result->num_rows > 0){
					return $result->fetch_array(MYSQLI_ASSOC); 
				}else{
					return null;// Retourner null si aucun membre trouvé
				}
			}
		}
		return null;
	}

?>