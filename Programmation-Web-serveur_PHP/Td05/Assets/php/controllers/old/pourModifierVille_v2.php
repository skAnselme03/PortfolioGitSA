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

	
	// Vérifie si l'identifiant de la villes est présent dans l'URL
	if($_SERVER["REQUEST_METHOD"] == "POST"){
		// Récupérer les données du formulaire
		$idVille = isset($_POST["idVille"]) ? $_POST["idVille"] : '';
		$nomVille = $_POST["nomVille"];
		$estCapital = $_POST["estCapital"];
		$countryName = $_POST["countryName"];
		$confirmUpdate = false;

		if($_POST["update"] == "modifier"){
			$donnees = array(
				"ID"=>$idVille,
				"Name" => $nomVille,
				"IsCapital" => $estCapital,
				"CountryName" => $countryName,
			);

			echo "<br>ID = '". $donnees['ID'] . "<br>";
			
			//modifier la table City
			$estModifier = modifierVille($donnees, $confirmUpdate);
			echo $estModifier;
			//valider que la modification a été réalise
			if($estModifier){
				
				// Construire l'URL avec les paramètres de données
				$urlRedirection = $pageVilles."?nomPays=".$nomPays;
				// Effectuer la redirection
				header("Location: " . $urlRedirection);
				/* exit(); */
			}else {
				echo "<p class=\"text-danger\">Attention! une erreur est survenue.</p>";
			}/* else if(isset($_POST['cancel'])){
				// la méthode header de PHP permet une rediection vers la page cities.php
				header('Location: cities.php?name='.$_POST["nomPays"]);
			} */
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