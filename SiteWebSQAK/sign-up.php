<!DOCTYPE html>
<html lang="fr">

<head>
    <meta charset="UTF-8">
    <title>SQAK - Inscription</title>
    <link rel="stylesheet" type="text/css" href="./css/styles.css">
    <link rel="stylesheet" type="text/css" href="./css/sign-in.css">
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
<form action="log-in.php" method="POST">
        <h2> Inscription </h2>

<div id="form-section">
    <div class="form-column">
        <h3>Personne</h3>
        <label for="prenom-ins">Prénom:</label>
        <input id="prenom-ins" type="text"> 
        <br>
        <label for="nom-ins">Nom:</label>
        <input id="nom-ins" type="text"> 
    </div>

    <div class="form-column" id="ins-bar">
         <h3> ou </h3>
    </div>

    <div class="form-column">
        <h3>Organisation</h3>
        <label for="organisation-ins">Nom de l'organisation:</label>
        <input id="organisation-ins" type="text"> 
    </div>
</div>

    <div id="form-bottom">
        <label for="courriel-ins">Courriel:</label>
        <input id="courriel-ins" type="email" required> 
        <br>
        <label for="tel-ins">Numéro de téléphone:</label>
        <input id="tel-ins" type="tel" placeholder="514-222-2222" pattern="^\d{3}-\d{3}-\d{4}$" required> 
        <br>
        <label for="mdp-ins">Mot de passe:</label>
        <input id="mdp-ins" type="password" minlength="8" required> 
        <br>
        <label for="Cmdp-ins">Confirmation de mot de passe:</label>
        <input id="Cmdp-ins" type="password" required> 
    </div>

    <div id="btn-container">
        <input class="btn-rose" type="submit" value="Inscription">
    </div>
    </form> 
</div>

<footer>
    <?php
    include("components/footer.php");
    ?>
</footer>
</body>

</html>