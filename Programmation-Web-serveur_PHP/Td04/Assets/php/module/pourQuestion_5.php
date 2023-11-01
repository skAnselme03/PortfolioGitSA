<?php
	include("classes/Personne.php");
	include("classes/Client.php");
	include("classes/Electeur.php");

	$titi = new Client("Le-Canary","Titi","5555 Rue Xxxxx, Ville Xxxxx, X4X2X2, Povince XX, Pays Xxxxx");
	$grosMinet = new Client("Le-Chat","Gros-Minet","5555 Rue Xxxxx, Ville Xxxxx, X4X2X2, Povince XX, Pays Xxxxx");
	echo "<br>";
	$titi->setCoord();
	echo "<br>";
	echo "<br>";
	$grosMinet->setCoord();
	echo "<br><hr>";
	echo "<br>";

    $e1 = new electeur('Lavictoire', 'William', 'Ecole St-rené');
    $e2 = new electeur('Gagner', 'Luc', 'Centre Rockland');

    echo $e1;
    $e2->voter(true);
    echo '<br>';
    echo $e2;

?>