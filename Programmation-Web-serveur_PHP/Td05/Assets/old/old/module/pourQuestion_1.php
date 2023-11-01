<?php
	require("data/connection.php");
	echo "<tbody>";
	foreach ($listeContinents as $continent) {
		echo "<tr>";
		echo"<td>" . $continent['Name'] . "</td>";
		echo"<td><a href=''>" . $continent['Name'] . "</td>";
		echo "</tr>";
	}
	echo "</tbody>";
?>