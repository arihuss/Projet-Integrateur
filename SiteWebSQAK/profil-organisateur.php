<?php

include_once("modele/DAO/OrganisateurDAO.class.php");

if (!isset($_SESSION['user_id'])) {
    //Rediriger vers la page de login si non connecté
   header("Location: index.php?action=seConnecter");
   exit;
}

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
        <img src="img/HemaQuebecLogo.jpg" alt="Logo Organisateur">
        <div>
            <h2>
                <?php
                    echo htmlspecialchars($organisateur?->getNomOrganisateur() ?? 
                                          trim($organisateur?->getPrenom() . " " . $organisateur?->getNom()));
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