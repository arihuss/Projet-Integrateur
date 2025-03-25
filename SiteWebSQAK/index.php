<!DOCTYPE html>
<html lang="fr">

<head>
    <meta charset="UTF-8">
    <title>SQAK - Bienvenue</title>
    <link rel="stylesheet" type="text/css" href=".\css\styles.css">
    <link rel="stylesheet" type="text/css" href=".\css\index.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    <link href='https://fonts.googleapis.com/css?family=Inter' rel='stylesheet'>
</head>

<body>
    <header><?php include("components/header.php") ?></header>
<!--Steps pour continuer:
    Regarder si on a besoin de js et l'implementer
    Creer du code pour recuperer controleur dans index
    Definir la classe controleur dans le abstract
    Remplacer les href par des actions
    Faire les fonctions DAO et les tester
    Figure out comment controler le fait que seul les organisateurs peuvent acceder aux autres pages
    Enlever le menu dans la page index (elements de la nav)
    Remplir les fonctions executerAction() dans tous les controleurs
    Faire des fichiers JSON
    Mettre la bd sur phpMyAdmin
    Tester les fonctions sur la platerforme utiliser dans le cours de Program Web
    -->
    <div class="container">
        <div id="accueil-card">
            <img id="logo-accueil" src="img/logo.svg">
            <h1>Bienvenue!</h1>
            <h3>Travaillons ensemble pour l'avenir...</h3>
            <div id="btn-container">
                <a href="log-in.php" class="btn-jaune">Se connecter</a>
                <a href="sign-up.php" class="btn-jaune">S'Inscrire</a>
            </div>
        </div>
        <h2>Fonctionnalités</h2>
        <div id="fonction">
            <p class="content"><i class="fa-solid fa-pencil"></i><br>Creation et gestion d'evenements</p>
            <p class="content"><i class="fa-solid fa-bell"></i><br>Notifications et rappels</p>
            <p class="content"><i class="fa-solid fa-calendar-days"></i><br>Suivi des inscriptions</p>
            <p class="content"><i class="fa-solid fa-comments"></i><br>Interactions avec la communaute</p>
        </div>
        <h2>Revues</h2>
        <div id="reviews">
            <div class="revue">
                <img class="img-revue" src="img/cat-face-silhouettes-cat-face-svg-black-and-white-cat-vector.jpg">
                <h3>Cat & Co</h3>
                <p>2024-03-18</p>
                <img src="img/rating.svg">
                <p>Nos chatons adorent recevoir des visites de bénévoles attentionnés!
                    Nous sommes reconnaissant pour les gentilles personnes chez SQAK pour
                    cette magnifique platerforme qui rend la recherche de bénévoles un véritable catwalk!
                </p>
            </div>

            <div class="revue">
                <img class="img-revue" src="img/HemaQuebecLogo.jpg">
                <h3>Hema-Quebec</h3>
                <p>2025-05-02</p>
                <img src="img/rating.svg">
                <p>Ici à Héma-Québec, on adore utiliser SQAK pour retroouver des bénévoles à nos nombreuses collectes de sang!
                    Merci aux créateurs de SQAK pour cette ingénieuse création!!!
                </p>
            </div>

            <div class="revue">
                <img class="img-revue" src="img/dog.png">
                <h3>Buddies Inc.</h3>
                <p>2025-07-12</p>
                <img src="img/rating.svg">
                <p>Nous avons pu trouver de gentils visiteurs et bénévoles partant pour tricotter de magnifiques tuques pour nos chiens sans fourrure!
                    La platerforme SQAK a offert une manière efficace et facile pour organiser ce genre d'événement!
                </p>
            </div>
        </div>
    </div>



    <footer><?php include("components/footer.php"); ?></footer>
    <script src="js/general.js"></script>
</body>

</html>