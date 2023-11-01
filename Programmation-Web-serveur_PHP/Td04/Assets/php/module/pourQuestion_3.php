<?php
	require("manipChaine.php");
	$tableau = ["titi", "toto", "tata","bugs-bunny","tom","jerry"];
	//parcourt chaque élément du tableau. L'opérateur & devant $element 
	//indique que la variable $element est une référence 
	//au contenu du tableau, 
	foreach ($tableau as &$element) {
		echo "<li>";
		echo majFirstLetter($element);
		echo "</li>";
	}

?>