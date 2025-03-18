<!DOCTYPE html>
<html lang="fr">

<head>
    <meta charset="UTF-8">
    <title>SQAK - Connexion</title>
    <link rel="stylesheet" type="text/css" href="./css/styles.css">
</head>

<body>
    <h2>Connexion</h2>
    <form action="" method="POST" >
        <label for="courriel-connexion">Courriel:</label>
        <input id="courriel-connexion" type="email" required> 
        <label for="mdp-connexion">Mot de passe:</label>
        <input id="mdp-connexion" type="text" minlength="8" required> 
        <input type="submit" value="Connexion">
        <a href="mdp-oublie" > Mot de passe oublié ?</a> <!-- mot de passe oublie a changer-->
    </form>

</body>

</html>