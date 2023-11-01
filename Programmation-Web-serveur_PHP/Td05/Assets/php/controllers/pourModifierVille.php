<?php
	require ("../data/param.php");
	include("../models/ConnectionBDD.php");
	$pageVilles = "../views/villes.php";
	$pagesModif = "../views/modifier_ville.php";
	$nomVille = "";
	$estCapital = "";
	$countryName = "";
	$nomPays = $countryName;
	$idVille = "";
	$CountryName = "";
	
	// Vérifie si l'identifiant de la villes est présent dans l'URL
	if($_SERVER["REQUEST_METHOD"] == "POST"){
		// Récupérer les données du formulaire
		$idVille = isset($_POST["idVille"]) ? $_POST["idVille"] : '';
		$nomVille = $_POST["nomVille"];
		$estCapital = $_POST["estCapital"];
		$confirmUpdate = false;

		if(isset($_POST["update"])){
			$donnees = array(
				"ID"=>$idVille,
				"Name" => $nomVille,
				"IsCapital" => $estCapital,
				"CountryName" => $countryName,
			);
		
			//modifier la table City
			$estModifier = modifierVille($donnees, $confirmUpdate);
			echo $estModifier;

			//valider que la modification a été réalise
			if($estModifier){
				
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
	 * @param bool $estModifier : valider que la modification a bien été éffectuer
	 * @return bool: si oui ou non la modification a été effectué correctement
	 */
	function modifierVille($donnees, &$estModifier){
		// Instancier un objet de connection
		$connectionBDD = new ConnectBDD(HOSTNAME, USERNAME, PASSWORD, DATABASENAME, PORT);

		$connexion = $connectionBDD->getConnection();
		$condition = "ID = '". $donnees['ID'] . "' AND Name = '" . $donnees['Name'] . "'". " AND CountryName = '" . $donnees['CountryName'] . "'";
		if ($connexion != null) {
			//modifier le tableau
			$modification = $connectionBDD->put("City", $donnees,$condition);

			//valider que la modification a été réalise
			if($modification){
				$estModifier = true;
			}
		}

		$connectionBDD->closeConnection();//fermer la connection
		return $estModifier;
	}

?>