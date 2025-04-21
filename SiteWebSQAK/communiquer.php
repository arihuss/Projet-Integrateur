<!DOCTYPE html>
<html lang="fr">

<?php
include_once('modele/DAO/EvenementDAO.class.php');
include_once('modele/DAO/ParticipantDAO.class.php');
include_once('modele/DAO/UserDAO.class.php');

$event = EvenementDAO::findById($_GET['id']);

if ($_SERVER['REQUEST_METHOD'] === 'POST' && isset($_POST['sendEmail'])) {
  $eventId = $_GET['id'];
  $tabCourriels = [];

  if (!empty($_POST['roles'])) {
    foreach ($_POST['roles'] as $selectedRole) {
      $participants = ParticipantDAO::findByRoleAndId($selectedRole, $eventId);
      foreach ($participants as $participant) {
        $user = UtilisateurDAO::findById($participant->getIdUtilisateur());
        if ($user && $user->getCourriel()) {
          $tabCourriels[] = $user->getCourriel();
        }
      }
    }

    $tabCourriels = array_unique($tabCourriels);

    if (!empty($tabCourriels)) {
      $mailtoLink = 'mailto:' . implode(',', $tabCourriels);
      echo '<script>
          window.location.href = "' . $mailtoLink . '";
          setTimeout(function() {
            window.history.back();
          }, 1000);
        </script>';
    } else {
      echo '<script>alert("Aucun email trouvé pour les rôles sélectionnés.");</script>';
    }
  } else {
    echo '<script>alert("Veuillez sélectionner au moins un rôle.");</script>';
  }
}
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
  <header> <?php include("components/header.php"); ?> </header>

  <div class="container">
    <h1 class="titre-destinataires">Destinataires</h1>
    <p class="sous-titre">Choisissez les groupes à contacter : </p>

    <form id="communicationForm" method="POST" action="">
      <input type="hidden" name="eventId" value="<?php echo $_GET['id']; ?>">

      <div class="checkbox-vertical">
        <label>
          <input type="checkbox" name="roles[]" value="appliquant"
            <?php echo (isset($_POST['roles']) && in_array('appliquant', $_POST['roles'])) ? 'checked' : ''; ?>>
          Appliquants
        </label>
        <label>
          <input type="checkbox" name="roles[]" value="benevole"
            <?php echo (isset($_POST['roles']) && in_array('benevole', $_POST['roles'])) ? 'checked' : ''; ?>>
          Bénévoles
        </label>
        <label>
          <input type="checkbox" name="roles[]" value="invite"
            <?php echo (isset($_POST['roles']) && in_array('invite', $_POST['roles'])) ? 'checked' : ''; ?>>
          Invités
        </label>
      </div>

      <div class="btn-container">
        <button type="submit" name="sendEmail" class="btn-jaune">Envoyer Courriel</button>
        <button type="button" class="btn-rose" onclick="window.location.href='?action=voirUnEvent&id=<?= $event->getId() ?>'">Revenir</button>
      </div>
    </form>
  </div>

  <footer> <?php include("components/footer.php"); ?> </footer>
  <script src="js/general.js"></script>
</body>

</html>