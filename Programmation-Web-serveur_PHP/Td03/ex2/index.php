<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Exercice 2</title>
</head>

<body>
    <?php
    $prix = 0;
    $tps = 0;
    $tvq = 0;
    $taxes = 0;
    $total = 0;
    if (isset($_GET['prix']))
        $prix = floatval($_GET['prix']);
    if (isset($_GET['tps']))
        $tps = floatval($_GET['tps']) / 100;
    if (isset($_GET['tvq']))
        $tvq = floatval($_GET['tvq']) / 100;
    if(!empty($prix)){
        $taxes = round($prix * ($tps + $tvq),2);
        $total = round($prix  + $taxes, 2);
        //echo 'TAXES: ' . $taxes. '$ <br>';
        //echo 'TOTAL: ' . $total. '$ <br>';
    }
    ?>

    <form action="index.php">
        <table>
            <tr>
                <td><label for="prix">Prix Hors taxes</label></td>
                <td><input type="text" name="prix" value="<?php echo $prix; ?>"> $</td>
            </tr>
            <tr>
                <td><label for="tqs">TPS</label></td>
                <td><input type="text" name="tps" value="5.00"> %</td>
            </tr>
            <tr>
                <td><label for="tvq">TVQ</label></td>
                <td><input type="text" name="tvq" value="9.975"> %</td>
            </tr>
            <tr>
                <td><label for="taxes">Taxes</label></td>
                <td><input type="text" name="taxes" readonly value="<?php echo $taxes;?>" style="background-color: gray; color:red;"> $</td>
            </tr>
            <tr>
                <td><label for="total">Montant total</label></td>
                <td><input type="text" name="total" readonly value="<?php echo $total;?>" style="background-color: gray; color:red; font-size:22px"> $</td>
            </tr>
        </table>
        <br>
        <br>
        <input type="submit" value="Calculer">
    </form>

</body>

</html>