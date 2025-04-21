<!DOCTYPE html>
<html lang="fr">

<?php

include_once('modele/DAO/EvenementDAO.class.php');
include_once('modele/DAO/ParticipantDAO.class.php');
include_once('modele/DAO/UserDAO.class.php');

$event = EvenementDAO::findById($_GET['id']);

?>

<head>
    <meta charset="UTF-8">
    <title>SQAK - Communiquer</title>
    <link rel="stylesheet" type="text/css" href="./css/styles.css">
    <link rel="stylesheet" type="text/css" href="./css/communiquer.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    <link href='https://fonts.googleapis.com/css?family=Inter' rel='stylesheet'>
</head>

<body>
    <header> <?php include("components/header.php") ?> </header>

    <div class="container">
  <form id="communicationForm">
    <div class="section-flex">
    

      <!-- COLONNE DROITE -->
      <div class="right-column section-block">
        <h2 class="section-title">Destinataires</h2>
        <div class="checkbox-group">
          <input type="checkbox" id="appliquants"> <label for="appliquants">Appliquants</label>
          <input type="checkbox" id="benevoles"> <label for="benevoles">Bénévoles</label>
          <input type="checkbox" id="invites"> <label for="invites">Invités</label>
        </div>
      </div>
    </div>

    <div class="buttons">
      <button type="submit" class="btn-jaune">Écrire Courriel</button>
      <button type="button" class="btn-rose" onclick="history.back()">Revenir</button>
    </div>
  </form>
</div>




    <footer> <?php include("components/footer.php"); ?> </footer>
    <script src="js/general.js"></script>
</body>


</html>
