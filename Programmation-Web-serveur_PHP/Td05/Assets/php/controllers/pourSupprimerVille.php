<?php
	require ("../data/param.php");
	include("../models/ConnectionBDD.php");
	$pageVilles = "../views/villes.php";
	$pagesModif = "../views/supprimer_ville.php";
	$nomVille = "";
	$estCapital = "";
	$countryName = "";
	$nomPays = $countryName;
	$idVille = "";
	
	// Vérifie si l'identifiant de la villes est présent dans l'URL
	if($_SERVER["REQUEST_METHOD"] == "POST"){
		// Récupérer les données du formulaire
		$idVille = isset($_POST["idVille"]) ? $_POST["idVille"] : '';
		$nomVille = $_POST["nomVille"];
		$estCapital = $_POST["estCapital"];
		$countryName = $_POST["countryName"];
		$confimDelete = false;

		if(isset($_POST["update"])){
			$donnees = array(
				"ID"=>$idVille,
				"Name" => $nomVille,
				"IsCapital" => $estCapital,
				"CountryName" => $countryName,
			);

		
			//modifier la table City
			$estEffacer = supprimerVille($donnees, $confimDelete, $pageRedirect);

			//valider que la modification a été réalise
			if($estEffacer){
				
				// Construire l'URL avec les paramètres de données
				$urlRedirection = $pageVilles."?Name=".$countryName;
				// Effectuer la redirection
				header("Location: " . $urlRedirection);
				/* exit(); */
			}
		}else if(isset($_POST["cancel"])){
				// La méthode header de PHP permet une redirection vers la page villes.php
				$urlRedirection = $pageVilles."?Name=". $countryName;
				// la méthode header de PHP permet une rediection vers la page villes.php
				header("Location: ". $urlRedirection);
			}
		}else {
		header("Location: " . $pageVilles);
	}
	

	/**
	 * Modifier les données d'une ville
	 * @param array $donnees : données à modifier dans la bdd
	 * @param bool $estEffacer : valider que l"efface a bien été éffectuer
	 * @return bool: si oui ou non la modification a été effectué correctement
	 */
	function supprimerVille($donnees, &$estEffacer, $pageRedirect){
		// Instancier un objet de connection
		$connectionBDD = new ConnectBDD(HOSTNAME, USERNAME, PASSWORD, DATABASENAME, PORT);

		$connectionBDD->setpageRedirect($pageRedirect);
		$connexion = $connectionBDD->getConnection();
		
		// Requête SELECT pour vérifier l'existence des données
		$verifExistdonnees = "SELECT * FROM City WHERE id = ". $donnees['ID'];
		echo $verifExistdonnees;
		echo"<br>";
		// Requête DELETE à exécuter
		$requete = "DELETE FROM City WHERE id = ". $donnees['ID'];
		echo $requete;
		echo"<br>";
		
		if ($connexion != null) {
			//modifier le tableau
			$modification = $connectionBDD->deleteData($requete, $verifExistdonnees);
			echo $modification;
			echo"<br>";
		//valider que la modification a été réalise
			if($modification){
				$estEffacer = true;
			}
		}

		$connectionBDD->closeConnection();//fermer la connection
		return $estEffacer;
	}

?>