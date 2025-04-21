<!DOCTYPE html>
<html lang="fr">

<head>
  <meta charset="UTF-8">
  <title>SQAK - Événement</title>
  <link rel="stylesheet" type="text/css" href="./css/styles.css">
  <link rel="stylesheet" type="text/css" href="./css/evenement.css">
  <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
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

  <div class='container'>
    <div id='bouton-section'>
      <a href='?action=voirEvents'><i class='fa-solid fa-circle-left'></i></a>
      <div id='btn-droite'>
        <a href='?action=modifierEvent&id=<?php echo $event->getId(); ?>' class='btn-jaune'>Modifier</a>
        <a href='?action=supprimerEvent&id=<?php echo $event->getId(); ?>' class='btn-rose' onclick='confirmSupprimer(event)'>Supprimer</a>
      </div>
    </div>

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
        
      }
    }

    if ($event) {
      echo "<h1>" . $event->getNom() . "</h1>";
      echo "<p class='content'>" . $event->getDescription() . "</p>";

      echo "<div id='info-eve'>";

      echo "<div class='content' id='date'>";
      echo "<div class='inner-content'>";
      echo "<h3><i class='fa-regular fa-clock'></i> Quand?</h3>";
      echo "<p><b>Date de début: </b>" . $event->getDateDebut() . "<br>";
      echo "<b>Date de fin: </b>" . $event->getDateFin() . "<br>";
      echo "<b>Heure: </b>" . $event->getHeureDebut() . " -> " . $event->getHeureFin() . "</p>";

      $nom = urlencode($event->getNom());
      $dateDebut = date("Ymd\THis\Z", strtotime($event->getDateDebut()));
      $dateFin = date("Ymd\THis\Z", strtotime($event->getDateFin()));
      $description = urlencode($event->getDescription());
      $lieu = urlencode($event->getLieu());

      echo "<a class='btn-rose' href='https://calendar.google.com/calendar/r/eventedit?text=$nom&dates=$dateDebut/$dateFin&details=$description&location=$lieu' target='_blank'>Rajouter à mon Google calendrier</a>";
      echo "</div></div>";

      echo "<div class='content' id='lieu'>";
      echo "<div class='inner-content'>";
      echo "<h3><i class='fa-solid fa-location-dot'></i> Lieu:</h3>";
      echo "<p>" . $event->getLieu() . "</p>";
      $adresse = urlencode($event->getLieu());
      echo "<a class='btn-rose' href='https://www.google.com/maps/search/?api=1&query=$adresse' target='_blank'>Voir sur Google Maps</a>";
      echo "</div></div>";

      echo "</div>"; 

      echo "<div id='img-container'>";
      echo "<img id='image-eve' src='data:image/jpeg;base64," . base64_encode($event->getImageEvenement()) . "' alt='img-evenement'>";
      echo "</div>";
    } else {
      echo "<h1>Événement non trouvable</h1>";
    }

    $selectedRole = $_POST['role'] ?? 'benevole';
    $personnes = ParticipantDAO::findByRoleAndId($selectedRole, $event->getId());
    ?>

    <div id='btns'>
      <form method='POST' id='role-form'>
        <input type='hidden' name='id' value='<?php echo $event->getId(); ?>'>
        <?php
        $roles = [
          'appliquant' => 'Applications',
          'benevole' => 'Bénévoles',
          'invite' => 'Invités'
        ];
        foreach ($roles as $key => $label) {
          $btnClass = ($selectedRole === $key) ? 'btn-rose' : 'btn-jaune';
          echo "<button class='$btnClass role-btn' name='role' value='$key'>$label</button>";
        }
        ?>
      </form>
    </div>

    <div id='section-liste'>
  <div class='participants-container'>
    <?php if (empty($personnes)): ?>
      <p class='no-participants'>Il n'y a pas d'inscriptions dans cette section.</p>
    <?php else: ?>
      <?php foreach ($personnes as $personne):
        $user = UtilisateurDAO::findById($personne->getIdUtilisateur());
        $idInscription = $personne->getIdInscription();
      ?>
        <div class='participant-item'>
          <?php if ($selectedRole === 'appliquant'): ?>
            <form method='POST' class='decision-form'>
              <input type='hidden' name='id_inscription' value='<?php echo $idInscription; ?>'>
              <button type='submit' name='action_decision' value='accepter' class='icone-btn'>
                <i class='fa-solid fa-circle-check accepter'></i>
              </button>
              <button type='submit' name='action_decision' value='refuser' class='icone-btn'>
                <i class='fa-solid fa-circle-xmark refuser'></i>
              </button>
            </form>
          <?php endif; ?>
          <a href='?action=profilParticipant&id=<?php echo $personne->getIdInscription(); ?>'><?php echo $user->getPrenom().' '.$user->getNom(); ?></a>
          <p class='inscription-date'><?php echo $personne->getDateInscription(); ?></p>
        </div>
      <?php endforeach; ?>
    <?php endif; ?>
  </div>
</div>

    <?php $stats = StatistiqueDAO::findById($event->getIdStats()); ?>
    <h2>Statistiques</h2>
    <div id='graphique-container'>
      <canvas id='donutChart'></canvas>
      <div class='legende'>
        <div><span class='couleur-invites'></span>Invités</div>
        <div><span class='couleur-benevoles'></span>Bénévoles</div>
      </div>
    </div>

    <div id='statistiques'>
      <span><h2><?php echo $stats->getNbVisiteurs(); ?></h2><p>Invités</p></span>
      <span><h2><?php echo $stats->getNbApplication(); ?></h2><p>Applications</p></span>
      <span><h2><?php echo $stats->getNbBenevoles(); ?></h2><p>Bénévoles</p></span>
      <span><h2><?php echo $stats->getNbLikes(); ?></h2><p>Likes</p></span>
      <span><h2><?php echo $stats->getNbPartages(); ?></h2><p>Partages</p></span>
      <span><h2><?php echo $stats->getNbVues(); ?></h2><p>Visites</p></span>
    </div>

    <?php echo "<span id='btn-com'><a class='btn-rose' href='?action=communiquer&id=".$event->getId()."'>Communiquer</a></span>";?>
    <h2>Commentaires</h2>

    <?php
    $commentaires = CommentaireDAO::findByEvenement($event->getId());
    foreach ($commentaires as $comment):
      $user = UtilisateurDAO::findById($comment->getIdUtilisateur());
      $image = $user->getImgUtilisateur();
      $src = $image ? "data:image/jpeg;base64," . base64_encode($image) : "./img/profilapplicant.svg";
    ?>
      <div class='content'>
        <div id='img-comm'>
        <img  src='<?php echo $src; ?>' alt='Photo de profil'>
      </div>
        <div id='commentaire-sec'>
          <div id='com-haut'>
            <p><?php echo htmlspecialchars($user->getPrenom().' '.$user->getNom()); ?></p>
            <p><?php echo htmlspecialchars($comment->getDateEnvoi()); ?></p>
          </div>
          <p><?php echo htmlspecialchars($comment->getMessage()); ?></p>
        </div>
      </div>
    <?php endforeach; ?>

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