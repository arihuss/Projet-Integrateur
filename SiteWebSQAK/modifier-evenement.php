<!DOCTYPE html>
<html lang="fr">

<head>
    <meta charset="UTF-8">
    <title>SQAK - Modifier événement</title>
    <link rel="stylesheet" type="text/css" href="./css/styles.css">
    <link rel="stylesheet" type="text/css" href="./css/mod-even.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    <link href='https://fonts.googleapis.com/css?family=Inter' rel='stylesheet'>
</head>

<body>
<header>
        <?php
        include("components/header.php")
        ?>
    </header>

<body>
    <div class="container">

        <h2>Modifier événement</h2>

        <form action="#" method="POST">

            <label for="photo">Choisir nouvelle photo:</label>
            <input type="file" id="photo" name="photo" class="file-input">

            <label for="titre">Titre de l'événement:</label>
            <input type="text" id="titre" name="titre" required>

            <label for="description">Description:</label>
            <textarea id="description" name="description" required></textarea>

            <div class="row">
                <div>
                    <label for="categorie">Catégorie:</label>
                    <input type="text" id="categorie" name="categorie" required>
                </div>
                <div>
                    <label for="lieu">Lieu:</label>
                    <input type="text" id="lieu" name="lieu" required>
                </div>
            </div>
            <div class="row">
                <div>
                    <label for="date-debut">Date début:</label>
                    <input type="date" id="date-debut" name="date-debut" required>
                </div>
                <div>
                    <label for="date-fin">Date fin:</label>
                    <input type="date" id="date-fin" name="date-fin" required>
                </div>
            </div>
            <div class="row">
                <div>
                    <label for="heure-debut">Heure début:</label>
                    <input type="time" id="heure-debut" name="heure-debut" required>
                </div>
                <div>
                    <label for="heure-fin">Heure fin:</label>
                    <input type="time" id="heure-fin" name="heure-fin" required>
                </div>
            </div>
            <div class="row">
                <div>
                    <label for="benevoles-max">Nombre de bénévoles maximum:</label>
                    <input type="number" id="benevoles-max" name="benevoles-max">
                </div>
                <div>
                    <label for="invites-max">Nombre d'invités maximum:</label>
                    <input type="number" id="invites-max" name="invites-max">
                </div>
            </div>

            <div class="buttons">
                <button type="submit" class="btn-save">Modifier</button>
                <button type="button" class="btn-cancel">Revenir</button>
            </div>
        </form>
    </div>


           
</body>
<footer>

<?php
include("components/footer.php");
?>
</footer>
</body>

</html>