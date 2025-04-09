<?php

include_once("modele/DAO/OrganisateurDAO.class.php");

$organisateur = OrganisateurDAO::findById($_SESSION['user_id']);
?>
<!DOCTYPE html>
<html lang="fr">

<head>
    <meta charset="UTF-8">
    <title>SQAK - Mon profil</title>
    <link rel="stylesheet" type="text/css" href="./css/styles.css">
    <link rel="stylesheet" type="text/css" href="./css/profil-organisateur.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    <link href='https://fonts.googleapis.com/css?family=Inter' rel='stylesheet'>
</head>



<body>
    <header><?php include("components/header.php")?></header>

<div class="container">
    <div id="entete-profil">
        <h1>Mon Profil</h1>
        <a href="?action=modifierProfil" class="btn-rose">Modifier</a>
    </div>

    <div id="profil-content">
      <?php 
      if ($organisateur->getImgOrganisateur()){
        echo"<img src='data:image/jpeg;base64," . base64_encode($organisateur->getImgOrganisateur()) . "' alt='Logo Organisateur'>"; 
      }else{
        echo"<img src='\img\default_profil.jpg' alt='Logo Organisateur'>"; 
      }
        ?>

        <div>
            <h2>
                <?php
                    if ($organisateur->getNomOrganisateur()){
                        echo $organisateur->getNomOrganisateur();
                    }else{
                        echo $organisateur->getPrenom()." ".$organisateur->getNom();
                    }
                ?>
            </h2>
            <p class="content"><?php echo htmlspecialchars($organisateur->getBiographie()); ?></p>
        </div>
    </div>
</div>

    <footer> <?php include("components/footer.php"); ?></footer>
    <script src="js/general.js"></script>
</body>

</html>