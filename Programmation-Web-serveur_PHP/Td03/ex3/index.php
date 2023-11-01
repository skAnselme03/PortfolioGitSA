<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Ex 3</title>
</head>

<body>
    <?php
    // on ne traite le formulaire qu'après la soumission
    if (!isset($_POST["submit"])) {
        $prix = 0;
        $mensualite = 0;
        $tauxAnnuel = 0;
        $duree = 0;
    } else {
        $prix = 0;
        $tauxAnnuel = 0;
        $duree = 0;
        $assurance = 0;
        //recupération des données du formulaire
        if (isset($_POST["prix"]))
            $prix = floatval($_POST["prix"]);
        if (isset($_POST["taux"]))
            $tauxAnnuel = floatval($_POST["taux"]); //taux annuel
        if (isset($_POST["duree"]))
            $duree = intval($_POST["duree"]); //durée en annees
        if (isset($_POST["assurance"]))
            $assurance = floatval($_POST["assurance"]);
        //validation côté serveur
        if (empty($prix) || empty($tauxAnnuel) || empty($duree)) {
            echo "<script>alert('Attention champ manquant');</script>";
            $mensualite = 0;
        } else {
            $tauxMensuel  = $tauxAnnuel / 100 / 12;
            $dureeEnMois = $duree * 12; //en nombre des mois
            $mensualite = ($prix * $tauxMensuel) / (1 - pow((1 + $tauxMensuel), $dureeEnMois * -1));
        }
    }
    ?>

    <form action="<?php echo $_SERVER['PHP_SELF'] ?>" method="post">
        <h1>Calcul Prêt</h1>
        <label for="prix">Prix: </label>
        <input type="text" name="prix" id="" value="<?php echo $prix; ?>">$ <br>
        <label for="taux">Taux: </label>
        <input type="text" name="taux" id="" value="<?php echo $tauxAnnuel; ?>">% <br>
        <label for="duree">Durée (en années): </label>
        <input type="text" name="duree" id="" value="<?php echo $duree; ?>"> <br>
        <label for="assurance">Assurance: </label><br>
        <input type="radio" name="assurance" value="0.035"> OUI<br>
        <input type="radio" name="assurance" value="0" checked> NON<br>
        <br>
        <input type="submit" value="calculer" name="submit">
    </form>
    <br>

    <h2>Vous avez à payer une mensualité de <strong style="color:red"><?php echo round($mensualite, 2); ?> $</strong> hors assurance.</h2>

    <table border="1">
        <tr>
            <th>Mois</th>
            <th>Capital restant</th>
            <th>Intérêt</th>
        </tr>
        <?php
            $prixEncours = $prix;
            for ($i=0; $i < $duree*12; $i++) { 
               $interet = $prixEncours * $tauxAnnuel / 100 / 12;
               echo "<tr><td> " . ($i + 1) . "</td>";
               echo "<td>". round($prixEncours, 2) . "</td>";
               echo "<td>". round($interet, 2) . "</td>";
               echo "</tr>";
               $prixEncours = $prixEncours - ($mensualite - $interet);
            }
        ?>
    </table>


</body>

</html>