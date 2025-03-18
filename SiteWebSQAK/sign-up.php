<!DOCTYPE html>
<html lang="fr">

<head>
    <meta charset="UTF-8">
    <title>SQAK - Inscription</title>
    <link rel="stylesheet" type="text/css" href="./css/styles.css">
</head>

<body>
<header>
        <?php
        include("components/header.php")
        ?>
    </header>
    <form action="" method="POST">
        <h2> Inscription </h2>
    <div>
        <p>Personne</p>
        <label for="prenom-ins">Prénom:</label>
        <input id="prenom-ins" type="text"> 
        <label for="nom-ins">Nom:</label>
        <input id="nom-ins" type="text"> 
    </div>

    <div id="ins-bar"> <p>ou </p>
    </div>

    <div>
        <p>Organisation</p>
        <label for="organisation-ins">Nom de l'organisation:</label>
        <input id="organistaion-ins" type="text"> 
    </div>

    <div>
        <label for="courriel-ins">Courriel:</label>
        <input id="courriel-ins" type="email" required> 
        <label for="tel-ins">Numéro de téléphone:</label>
        <input id="tel-ins" type="tel" placeholder="514-222-2222" pattern="^\d{3}-\d{3}-\d{4}$" required> 
        <label for="mdp-ins">Mot de passe:</label>
        <input id="mdp-ins" type="text" minlength="8" required> 
        <label for="Cmdp-ins">Confirmation de mot de passe:</label>
        <input id="Cmdp-ins" type="text" required> 
    </div>

    <input type="submit" value="Inscription">
 </form> 

 <footer>

<?php
include("components/footer.php");
?>
</footer>
</body>

</html>