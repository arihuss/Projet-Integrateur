<!DOCTYPE html>
<html lang="fr">

<head>
    <meta charset="UTF-8">
    <title>SQAK - Connexion</title>
    <link rel="stylesheet" type="text/css" href="./css/styles.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
</head>

<body class="container">

<header>
        <?php
        include("components/header.php")
        ?>
</header>


<div id="connexion">
    <h2>Connexion</h2>
    <form action="" method="POST" >
        <label for="courriel-connexion">Courriel:</label>
        <input id="courriel-connexion" type="email" required> 
        <label for="mdp-connexion">Mot de passe:</label>
        <input id="mdp-connexion" type="text" minlength="8" required> 
        <input type="submit" value="Connexion">
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