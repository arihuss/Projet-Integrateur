<!DOCTYPE html>
<html lang="fr">

<head>
    <meta charset="UTF-8">
    <title>SQAK - Paramètres</title>
    <link rel="stylesheet" type="text/css" href="./css/styles.css">
    <link rel="stylesheet" type="text/css" href="./css/settings.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    <link href='https://fonts.googleapis.com/css?family=Inter' rel='stylesheet'>
</head>

<body>
    <header><?php include("components/header.php") ?></header>
    <div class=container>
        <h1>À Propos</h1>
        <p class="content">SQAK est une organisation dédiée à la mise en valeur des organismes à but non lucratif en facilitant la
            connexion
            entre bénévoles, visiteurs et événements. Grâce à notre application intuitive et interactive, nous
            permettons
            aux utilisateurs de découvrir et de participer facilement à des initiatives engagées. De plus, notre
            interface
            web offre aux organisateurs un outil efficace pour planifier, publier et gérer leurs événements, optimisant
            ainsi leur visibilité et leur impact communautaire.</p>
        <h2>Paramètres</h2>
        <div id="parametres">
            <button class="btn-param" onclick="toggleDarkMode()"><p>Changer le theme</p><p id="mode">Light Mode</p></button>
            <a href="index.php" class="btn-param">Se deconnecter</a>
            <a href="index.php" class="btn-param" onclick="confirmSupprimer(event)">Supprimer mon compte</a>
        </div>
    </div>
    <footer><?php include("components/footer.php"); ?></footer>
    <script src="js/general.js"></script>
</body>

</html>