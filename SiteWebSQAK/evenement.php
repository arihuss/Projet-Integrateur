<!DOCTYPE html>
<html lang="fr">

<head>
  <meta charset="UTF-8">
  <title>SQAK - Événement</title>
  <link rel="stylesheet" type="text/css" href="./css/styles.css">
  <link rel="stylesheet" type="text/css" href="./css/evenement.css">
  <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
  <link href='https://fonts.googleapis.com/css?family=Inter' rel='stylesheet'>
</head>

<body>
  <header><?php include("components/header.php") ?></header>

  <div class="container">
      <div id="bouton-section">
          <a href="page-principale.php"><i class="fa-solid fa-circle-left"></i></a>

          <div id="btn-droite">
                <a href="modifier-evenement.php" class="btn-jaune">Modifier</a>
                <a href="page-principale.php" class="btn-rose" onclick="confirmSupprimer(event)">Supprimer</a>
          </div>
      </div>

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

  <div id="info-eve">
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
</div>

  <img id="image-eve" src="img\event-arbre.svg">

<div id="btns">
  <button class="btn-rose">Applications</button>
  <button class="btn-jaune">Bénévoles</button>
  <button class="btn-jaune">Invités</button>
</div>

<div id="section-liste">
<div id="liste">
  <i class="fa-solid fa-circle-xmark"></i>
  <i class="fa-solid fa-circle-check"></i>
  <a href="profil-participant.php">Liliane Belvier</a>
  <p>16/12/24</p>
</div>
<div id="liste">
  <i class="fa-solid fa-circle-xmark"></i>
  <i class="fa-solid fa-circle-check"></i>
  <a href="profil-participant.php">Liliane Belvier</a>
  <p>16/12/24</p>
</div>
<div id="liste">
  <i class="fa-solid fa-circle-xmark"></i>
    <i class="fa-solid fa-circle-check"></i>
<a href="profil-participant.php">Liliane Belvier</a>
  <p>16/12/24</p>
</div>
<div id="liste">
  <i class="fa-solid fa-circle-xmark"></i>
  <i class="fa-solid fa-circle-check"></i>
  <a href="profil-participant.php">Liliane Belvier</a>
  <p>16/12/24</p>
</div>
</div>


<h2>Statistiques</h2>

<div id="graphique-container">
  <canvas id="donutChart"></canvas>
  <div class="legende">
    <div><span class="couleur-invites"></span>Invités</div>
    <div><span class="couleur-benevoles"></span>Bénévoles</div>
  </div>
</div>

<div id="statistiques">
  <span><h2>125</h2><p>Invités</p></span>
  <span><h2>55</h2><p>Applications</p></span>
  <span><h2>15</h2><p>Bénévoles</p></span>
  <span><h2>652</h2><p>Likes</p></span>
  <span><h2>58</h2><p>Partages</p></span>
  <span><h2>1589</h2><p>Visites</p></span>
</div>


<span id="btn-com"><a class="btn-rose" href="communiquer.php" >Communiquer</a></span>


<h2>Commentaires</h2>

<div class="content">
  <img src=".\img\profilapplicant.svg" alt="">
  <div id="commentaire-sec">
    <div id="com-haut"> 
      <p>Gill Tremblay</p>
      <p>16/12/2024</p> 
    </div>
      <p>Hate de refaire ca ! Je suis fan de l'organisation St-Paul</p>
  </div>
</div>


</div>
  <footer><?php include("components/footer.php"); ?> </footer>

  <script src="js/general.js"></script>

  <script src="js/evenement.js"></script>

</body>

</html>