<!DOCTYPE html>
<html lang="fr">

<head>
    <meta charset="UTF-8">
    <title>SQAK - Connexion</title>
    <link rel="stylesheet" type="text/css" href="./css/styles.css">
    <link rel="stylesheet" type="text/css" href="./css/log-in.css">
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
    <h1>Connexion</h1>
    <form action="" method="POST" >
        <label for="courriel-connexion">Courriel:</label> <br>
        <input id="courriel-connexion" type="email" required><br><br>
        <label for="mdp-connexion">Mot de passe:</label> <br>
        <input id="mdp-connexion" type="text" minlength="8" required><br><br>
        <a class="btn-jaune" href="evenement.php">Connexion</a><br>
        <a href="mdp-oublie" > Mot de passe oublié ?</a> <!-- mot de passe oublie a changer-->
    </form>
</div>

<footer>
    <?php
        include("components/footer.php");
    ?>
</footer>

</body>

</html>