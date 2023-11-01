<?php
	$auteur = ['Victor Hugo', 'Charles Baudelaire', 'Arthur Rimbaud', 'Paul Verlaine'];
	for ($i=0; $i < count($auteur); $i++) { 
		echo "<tr>
				<td>$auteur[$i]</td>
			  </tr>";
	}
?>