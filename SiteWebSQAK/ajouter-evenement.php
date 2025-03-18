<!DOCTYPE html>
<html lang="fr">

<head>
    <meta charset="UTF-8">
    <title>SQAK - Ajouter un événement</title>
    <link rel="stylesheet" type="text/css" href="./css/styles.css">
</head>

<body>
    <div class="container">
        <h2>Ajouter un événement</h2>

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

          
           