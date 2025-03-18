<!DOCTYPE html>
<html lang="fr">

<head>
    <meta charset="UTF-8">
    <title>SQAK - Mon profil</title>
    <link rel="stylesheet" type="text/css" href="./css/styles.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
</head>

<body>
<header>
        <?php
        include("components/header.php")
        ?>
    </header>
    <div class="container">
    <h1>Mon Profil</h1>
    <a href="modifier-profil.html">Modifier</a>
    <div>
        <img src="img\HemaQuebecLogo.jpg">
        <p>Héma-Québec est un organisme sans but lucratif dont la mission est de répondre avec efficience aux besoins de la population québécoise en sang et autres produits biologiques d'origine humaine de qualité.</p>
    </div></div>

    <footer>

<?php
include("components/footer.php");
?>
</footer>
</body>

</html>