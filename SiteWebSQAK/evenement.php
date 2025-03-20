<!DOCTYPE html>
<html lang="fr">

<head>
  <meta charset="UTF-8">
  <title>SQAK - Événement</title>
  <link rel="stylesheet" type="text/css" href="./css/styles.css">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
  <link href='https://fonts.googleapis.com/css?family=Inter' rel='stylesheet'>
</head>

<body>
  <header><?php include("components/header.php") ?></header>

  <a href="page-principale.php"><i class="fa-solid fa-circle-left"></i></a>
  <a href="modifier-evenement.php" class="btn-jaune">Modifier</a>
  <a href="page-principale.php" class="btn-rose" onclick="confirmSupprimer(event)">Supprimer</a>

  <h1>Guignolee Noel</h1>
  <p class="content">Description de l’evenement + horaire -
    Lorem ipsum dolor sit amet,
    consectetur adipiscing elit,
    sed do eiusmod tempor incididunt ut labore
    et dolore magna aliqua. Ut enim ad minim veniam,
    quis nostrud exercitation ullamco laboris nisi ut
    aliquip ex ea commodo consequat. Duis aute irure
    dolor in reprehenderit in voluptate velit esse cillum
    dolore eu fugiat nulla pariatur.</p>

  <div class="content">
    <p>Mardi 13 décembre 2025
      16h00 à 20h00</p>
    <a href="https://calendar.google.com/calendar/r/eventedit?text=Guignolée+Noël&dates=20251213T210000Z/20251214T010000Z&details=Description+de+l’evenement+:+Lorem+ipsum+dolor+sit+amet,+consectetur+adipiscing+elit,+sed+do+eiusmod+tempor+incididunt+ut+labore+et+dolore+magna+aliqua.&location="
      target="_blank">Rajouter a mon Google calendrier</a>
  </div>

  <div class="content">
    <p>286 Rue Saint-Charles Ouest</p>
    <a href="https://www.google.com/maps/search/?api=1&query=286+Rue+Saint-Charles+Ouest" target="_blank">Voir sur
      Google Maps</a>
  </div>

  <img class="content" src="img\event-arbre.svg">

  <button class="btn-jaune">Applications</button>
  <button class="btn-jaune">Benevoles</button>
  <button class="btn-jaune">Invites</button>

  <footer><?php include("components/footer.php"); ?> </footer>
  <script src="js/general.js"></script>


</body>

</html>