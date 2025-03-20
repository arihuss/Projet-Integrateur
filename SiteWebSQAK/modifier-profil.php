<!DOCTYPE html>
<html lang="fr">

<head>
    <meta charset="UTF-8">
    <title>SQAK - Modifier Profil</title>
    <link rel="stylesheet" type="text/css" href="./css/styles.css">
    <link rel="stylesheet" type="text/css" href="./css/sign-in.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    <link href='https://fonts.googleapis.com/css?family=Inter' rel='stylesheet'>
</head>

<body>
    <header><?php include("components/header.php") ?></header>

    <div class="container">
        <form action="" method="POST">
            <h2> Modifier profil </h2>

            <div id="form-section">
                <div class="form-column">
                    <h3>Personne</h3>
                    <label for="prenom-mod">Prénom:</label>
                    <input id="prenom-mod" type="text">
                    <br>
                    <label for="nom-mod">Nom:</label>
                    <input id="nom-mod" type="text">
                </div>

                <div class="form-column" id="ins-bar">
                    <h3> ou </h3>
                </div>

                <div class="form-column">
                    <h3 id="org-titre">Organisation</h3>
                    <label for="organisation-mod">Nom de l'organisation:</label>
                    <input id="organisation-mod" type="text">
                </div>
            </div>

            <div id="form-bottom">
                <label for="courriel-mod">Courriel:</label>
                <input id="courriel-mod" type="email">
                <br>
                <label for="tel-mod">Numéro de téléphone:</label>
                <input id="tel-mod" type="tel" placeholder="514-222-2222" pattern="^\d{3}-\d{3}-\d{4}$">
                <br>
                <label for="mdp-mod">Mot de passe:</label>
                <input id="mdp-mod" type="text" minlength="8">
                <br>
                <label for="Cmdp-mod">Confirmation de mot de passe:</label>
                <input id="Cmdp-mod" type="text">
                <div id="file-upload">
                    <label for="file-photo">Choisir nouvelle photo:</label>
                    <input id="file-photo" type="file" accept="image/png, image/jpeg" name="Choisir">
                </div>
            </div>

            <div id="btn-container2">
                <a class="btn-rose" href="profil-organisateur.php">Revenir</a>
                <a class="btn-jaune" href="profil-organisateur.php">Sauvegarder</a>
            </div>
        </form>
    </div>

    <footer><?php include("components/footer.php"); ?></footer>
    <script src="js/general.js"></script>
</body>

</html>