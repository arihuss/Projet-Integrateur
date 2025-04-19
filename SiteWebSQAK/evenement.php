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
  <header>
    <?php include("components/header.php");
    include_once('modele/DAO/EvenementDAO.class.php');
    include_once('modele/DAO/ParticipantDAO.class.php');
    include_once('modele/DAO/UserDAO.class.php');
    include_once('modele/DAO/StatistiqueDAO.class.php');
    include_once('modele/DAO/CommentaireDAO.class.php');

    $event = EvenementDAO::findById($_GET['id']);

    ?>
  </header>

  <?php echo "<div class='container'>
      <div id='bouton-section'>
          <a href='?action=voirEvents'><i class='fa-solid fa-circle-left'></i></a>

          <div id='btn-droite'>
                <a href='?action=modifierEvent&id=" . $event->getId() . "' class='btn-jaune'>Modifier</a>
                <a href='?action=supprimerEvent&id=".$event->getId()."' class='btn-rose' onclick='confirmSupprimer(event)'>Supprimer</a>
          </div>
      </div>" ?>
  <?php


  if ($_SERVER["REQUEST_METHOD"] === "POST" && isset($_POST['action_decision'], $_POST['id_inscription'])) {
    $id = (int) $_POST['id_inscription'];
    $participant = ParticipantDAO::findById($id);

    if ($participant) {
      if ($_POST['action_decision'] === 'accepter') {
        ParticipantDAO::accepterAppliquant($participant);
      } elseif ($_POST['action_decision'] === 'refuser') {
        ParticipantDAO::refuserApplicant($participant);
      }
      // Rediriger pour éviter double soumission
      header("Location: " . $_SERVER['REQUEST_URI']);
      exit();
    }
  }


  if ($event) {

    echo "<h1>" . $event->getNom() . "</h1>";

    echo "<p class='content'>" . $event->getDescription() . "</p>";

    echo "<div id='info-eve'>";
    echo "<div class='content'>";
    echo "<p> Date de début: " . $event->getDateDebut() . "<br> Date de fin: " . $event->getDateFin() . "<br>Heure de debut: " . $event->getHeureDebut() . "<br>Heure de fin: " . $event->getHeureFin() . "</p>";
    $nom = urlencode($event->getNom());
    $dateDebut = date("Ymd\THis\Z", strtotime($event->getDateDebut()));
    $dateFin = date("Ymd\THis\Z", strtotime($event->getDateFin()));
    $description = urlencode($event->getDescription());
    $lieu = urlencode($event->getLieu());

    echo "<a href='https://calendar.google.com/calendar/r/eventedit?text=$nom&dates=$dateDebut/$dateFin&details=$description&location=$lieu'
  target='_blank'>Rajouter à mon Google calendrier</a>";
    echo "</div>";

    echo "<div class='content'>";
    echo "  <p>" . $event->getLieu() . "</p>";
    $adresse = urlencode($event->getLieu());

    echo "<a href='https://www.google.com/maps/search/?api=1&query=$adresse' target='_blank'>Voir sur Google Maps</a>";

    echo " </div>";
    echo "</div>";
    echo "<div id='img-container'>";
    echo "<img id='image-eve' src='data:image/jpeg;base64," . base64_encode($event->getImageEvenement()) . "' alt='img-evenement'>";
  } else {
    echo "<h1>Evenement non trouvable</h1>";
  }
  echo "</div>";

  $selectedRole = $_POST['role'] ?? 'benevole'; // rôle par défaut
  $personnes = ParticipantDAO::findByRoleAndId($selectedRole, $event->getId());

  echo "<div id='btns'>
  <form method='POST' id='role-form'>";
  echo "<input type='hidden' name='id' value='" . $event->getId() . "'>";

  $roles = [
    'appliquant' => 'Applications',
    'benevole' => 'Bénévoles',
    'invite' => 'Invités'
  ];

  foreach ($roles as $key => $label) {
    $btnClass = ($selectedRole === $key) ? 'btn-rose' : 'btn-jaune';
    echo "<button class='$btnClass role-btn' name='role' value='$key'>$label</button>";
  }

  echo "</form>
</div>";

  echo "<div id='section-liste'>";
  foreach ($personnes as $personne) {
    $user = UtilisateurDAO::findById($personne->getIdUtilisateur());
    $idInscription = $personne->getIdInscription();

    echo "<div id='liste'>";
    if ($selectedRole === 'appliquant') {
      echo "<form method='POST' style='display: inline-block'>
      <input type='hidden' name='id_inscription' value='$idInscription'>
      <button type='submit' name='action_decision' value='accepter' class='icone-btn'>
        <i class='fa-solid fa-circle-check'></i>
      </button>
      <button type='submit' name='action_decision' value='refuser' class='icone-btn'>
        <i class='fa-solid fa-circle-xmark'></i>
      </button>
    </form>";
    }
    echo "<a href='?action=profilParticipant&id=" . $personne->getIdInscription() . "'>" . $user->getNom() . "</a>
    <p>" . $personne->getDateInscription() . "</p>
  </div>";
  }
  echo "</div>";


  $stats = StatistiqueDAO::findById($event->getIdStats());

  echo "<h2>Statistiques</h2>

<div id='graphique-container'>
  <canvas id='donutChart'></canvas>
  <div class='legende'>
    <div><span class='couleur-invites'></span>Invités</div>
    <div><span class='couleur-benevoles'></span>Bénévoles</div>
  </div>
</div>

<div id='statistiques'>
  <span><h2>" . $stats->getNbVisiteurs() . "</h2><p>Invités</p></span>
  <span><h2>" . $stats->getNbApplication() . "</h2><p>Applications</p></span>
  <span><h2>" . $stats->getNbBenevoles() . "</h2><p>Bénévoles</p></span>
  <span><h2>" . $stats->getNbLikes() . "</h2><p>Likes</p></span>
  <span><h2>" . $stats->getNbPartages() . "</h2><p>Partages</p></span>
  <span><h2>" . $stats->getNbVues() . "</h2><p>Visites</p></span>
</div>";


  echo "<span id='btn-com'><a class='btn-rose' href='communiquer.php' >Communiquer</a></span>


<h2>Commentaires</h2>

<h2>Commentaires</h2>

<?php
$commentaires = CommentaireDAO::findByEvenement($event->getId());

foreach ($commentaires as $comment) {
    $user = UtilisateurDAO::findById($comment->getIdUtilisateur());
    $image = $user->getImgUtilisateur();
    $src = $image ? "data:image/jpeg;base64," . base64_encode($image) : "./img/profilapplicant.svg";

    echo "<div class='content'>
        <img src='$src' alt='Photo de profil' style='width: 70px; height: 70px; object-fit: cover; border-radius: 50%; margin-right: 15px;'>
        <div id='commentaire-sec'>
            <div id='com-haut'> 
                <p>" . htmlspecialchars($user->getNom()) . "</p>
                <p>" . htmlspecialchars($comment->getDateEnvoi()) . "</p> 
            </div>
            <p>" . htmlspecialchars($comment->getMessage()) . "</p>
        </div>
    </div>";
}
?>


  </div>
  <footer><?php include("components/footer.php"); ?> </footer>

  <script src="js/general.js"></script>
  <script>
    const donutData = {
      invites: <?php echo $stats->getNbVisiteurs(); ?>,
      benevoles: <?php echo $stats->getNbBenevoles(); ?>
    };
  </script>

  <script src="js/evenement.js"></script>

</body>

</html>