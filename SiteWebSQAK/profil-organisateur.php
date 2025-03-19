<!DOCTYPE html>
<html lang="fr">

<head>
    <meta charset="UTF-8">
    <title>SQAK - Mon profil</title>
    <link rel="stylesheet" type="text/css" href="./css/styles.css">
    <link rel="stylesheet" type="text/css" href="./css/profil-organisateur.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    <link href='https://fonts.googleapis.com/css?family=Inter' rel='stylesheet'>
</head>

<body>
    <header>
        <?php
        include("components/header.php")
            ?>
    </header>
    <div class="container">
        <div id="entete-profil">
            <h1>Mon Profil</h1>
            <a href="modifier-profil.php" class="btn-rose">Modifier</a>
        </div>
        <div id="profil-content">
            <img src="img\HemaQuebecLogo.jpg">
            <div>
                <h2>Hema-Quebec</h2>
                <p>Héma-Québec est un organisme sans but lucratif dont la mission est de répondre avec efficience aux
                    besoins de la population québécoise en sang et autres produits biologiques d'origine humaine de
                    qualité.
                </p>
            </div>
        </div>
    </div>

    <footer>

        <?php
        include("components/footer.php");
        ?>
    </footer>
</body>

</html>