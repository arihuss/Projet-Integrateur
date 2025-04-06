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
          <a href="?action=voirEvents"><i class="fa-solid fa-circle-left"></i></a>

          <div id="btn-droite">
                <a href="?action=modifierEvent" class="btn-jaune">Modifier</a>
                <a href="?action=voirEvents" class="btn-rose" onclick="confirmSupprimer(event)">Supprimer</a>
          </div>
      </div>
<?php
include_once('modele/DAO/EvenementDAO.class.php');
include_once('modele/DAO/ParticipantDAO.class.php');
include_once('modele/DAO/UserDAO.class.php');
$event = EvenementDAO::findById($_GET['id']);
if ($event){

  echo "<h1>".$event->getNom()."</h1>";

  echo "<p class='content'>". $event->getDescription()."</p>";

  echo "<div id='info-eve'>";
  echo "<div class='content'>";
  echo "<p>".$event->getDateDebut(). " au ". $event->getDateFin()."</p>";
  $nom = urlencode($event->getNom());
$dateDebut = date("Ymd\THis\Z", strtotime($event->getDateDebut()));
$dateFin = date("Ymd\THis\Z", strtotime($event->getDateFin()));
$description = urlencode($event->getDescription());
$lieu = urlencode($event->getLieu());

echo "<a href='https://calendar.google.com/calendar/r/eventedit?text=$nom&dates=$dateDebut/$dateFin&details=$description&location=$lieu'
  target='_blank'>Rajouter à mon Google calendrier</a>";
  echo "</div>";

  echo "<div class='content'>";
  echo "  <p>".$event->getLieu()."</p>";
  $adresse = urlencode($event->getLieu());

echo "<a href='https://www.google.com/maps/search/?api=1&query=$adresse' target='_blank'>Voir sur Google Maps</a>";

 echo " </div>";
echo "</div>";

echo "  <img id='image-eve' src='img/event-arbre.svg'>";
}else {
  echo "<h1>Evenement non trouvable</h1>";
}


$personnes = ParticipantDAO::findByRoleAndId("benevole", $event->getId());

echo "<div id='btns'>
  <button class='btn-rose'>Applications</button>
  <button class='btn-jaune'>Bénévoles</button>
  <button class='btn-jaune'>Invités</button>
</div>";
echo "<div id='section-liste'>";
foreach ($personnes as $personne){
  $user = UtilisateurDAO::findById($personne->getIdUtilisateur());
echo "<div id='liste'>
  <i class='fa-solid fa-circle-xmark'></i>
  <i class='fa-solid fa-circle-check'></i>
  <a href='?action=profilParticipant'>". $user->getNom()."</a>
  <p>". $personne->getDateInscription()."</p>
</div>";
}
echo "</div>";
?>

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