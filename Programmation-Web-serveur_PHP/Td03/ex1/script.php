<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Result Ex 1</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css" integrity="sha384-gH2yIJqKdNHPEq0n4Mqa/HGKIhSkIHeL5AyhkYV8i59U5AR6csBvApHHNl/vI1Bx" crossorigin="anonymous">
</head>

<body>
    <?php
    //recupérer les données envoyées par le client
    $nom = '';
    $prenom = '';
    $ville = '';
    $sports = array();
    if (isset($_POST['nom']))
        $nom = $_POST['nom'];
    if (isset($_POST['prenom']))
        $prenom = $_POST['prenom'];
    if (isset($_POST['ville']))
        $ville = $_POST['ville'];
    if (isset($_POST['sports']))
        $sports = $_POST['sports'];
    //validation côté serveur
    $is_valid = true;
    if (empty($nom)) {
        echo "<script>alert('Attention ! Nom obligatoire')</script>";
        $is_valid = false;
    }
    if (empty($prenom)) {
        echo "<script>alert('Attention ! Prénom obligatoire')</script>";
        $is_valid = false;
    }
    if (empty($ville)) {
        echo "<script>alert('Attention ! Ville obligatoire')</script>";
        $is_valid = false;
    }

    if ($is_valid) { //les données obligatoires (nom, prenom et villes) sont bien saisies
        //formatter sous la forme d'un tableau
        echo "<table class='table table-hover'>";
        echo "<tr><td>Nom saisi</td><td>$nom</td><tr>";
        echo "<tr><td>Prénom saisi</td><td>$prenom</td><tr>";
        echo "<tr><td>Ville saisie</td><td>$ville</td><tr>";
        if(count($sports) > 0){
            echo "<tr><td>Sports choisis</td><td>";
            foreach ($sports as $value) {
                echo "$value, ";
            }
            echo "</td><tr>";
        }
        echo "</table>";
    }
    ?>
</body>

</html>