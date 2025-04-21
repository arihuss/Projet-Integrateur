<!DOCTYPE html> 
<html lang="fr">  
<?php  
include_once('modele/DAO/EvenementDAO.class.php'); 
include_once('modele/DAO/ParticipantDAO.class.php'); 
include_once('modele/DAO/UserDAO.class.php');  

$event = EvenementDAO::findById($_GET['id']);  
$redirectMessage = "";

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
    
    // Remove duplicate emails     
    $tabCourriels = array_unique($tabCourriels);         
    
    if (!empty($tabCourriels)) {       
      $mailtoLink = 'mailto:' . implode(',', $tabCourriels);       
      // Instead of redirecting, open in a new window and then redirect to evenement.php
      $redirectMessage = "<script>
        window.open('" . $mailtoLink . "', '_blank');
        window.location.href = '?action=voirUnEvent&id=" . $eventId . "';
      </script>";
    } else {       
      $redirectMessage = "<script>
        alert('Aucun email trouvé pour les rôles sélectionnés.');
        window.location.href = '?action=voirUnEvent&id=" . $eventId . "';
      </script>";   
    }   
  } else {     
    $redirectMessage = "<script>
      alert('Veuillez sélectionner au moins un rôle.');
      window.location.href = '?action=voirUnEvent&id=" . $eventId . "';
    </script>";   
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
  <header> <?php include("components/header.php") ?> </header>    
  
  <div class="container">     
    <form id="communicationForm" method="POST" action="">       
      <input type="hidden" name="eventId" value="<?php echo $_GET['id']; ?>">       
      <div class="section-flex">         
        <!-- Right Column -->         
        <div class="right-column section-block">           
          <h2 class="section-title">Destinataires</h2>           
          <div class="checkbox-group">             
            <input type="checkbox" id="appliquants" name="roles[]" value="appliquant" <?php echo (isset($_POST['roles']) && in_array('appliquant', $_POST['roles'])) ? 'checked' : ''; ?>>             
            <label for="appliquants">Appliquants</label>              
            
            <input type="checkbox" id="benevoles" name="roles[]" value="benevole" <?php echo (isset($_POST['roles']) && in_array('benevole', $_POST['roles'])) ? 'checked' : ''; ?>>             
            <label for="benevoles">Bénévoles</label>              
            
            <input type="checkbox" id="invites" name="roles[]" value="invite" <?php echo (isset($_POST['roles']) && in_array('invite', $_POST['roles'])) ? 'checked' : ''; ?>>             
            <label for="invites">Invités</label>           
          </div>         
        </div>       
      </div>        
      
      <div class="buttons">         
        <button type="submit" name="sendEmail" class="btn-jaune">Écrire Courriel</button>         
        <button type="button" class="btn-rose" onclick="window.location.href='?action=voirUnEvent&id=<?= $event->getId() ?>'">Revenir</button>       
      </div>     
    </form>   
  </div>    
  
  <footer> <?php include("components/footer.php"); ?> </footer>   
  <script src="js/general.js"></script>
  <?php echo $redirectMessage; // Output the redirect message if there is one ?>
</body>   
</html>