<!DOCTYPE html>
<html lang="fr">

<head>
    <meta charset="UTF-8">
    <title>SQAK - Profil participant</title>
    <link rel="stylesheet" type="text/css" href="./css/styles.css">
</head>

<body>
<header>
        <?php
        include("components/header.php")
        ?>
    </header>

 <div id="profil-applicant">
       <h2>Profil de l'applicant </h2>
       <img src=".\img\profilapplicant.svg" alt="img-profil">
       <p id="nom-applicant">Liliane Belvier</p>
       <div>
            <p>Courriel:<br>
               Liliane.belvier@hotmail.com
            </p>  
            <p>
               Numéro de téléphone:<br>
               514-678-0955
            </p>
            <a href="contacter"> Contacter </a>
        </div>
</div>

<div id="experience-applicant">
 <h2>Expériences de bénévolat</h2>

 <div>
    <div>
        <p>27 janvier 2025 </p>
   </div>
   <div>
        <img src=".\img\event-arbre.svg" alt="img-evenement">         
        <p>Ramassage déchets <br>
           Lieu: plage Oka <br>
           Organisation: Terre-Sauve
        </p>
   </div>
 </div>

</div>

<footer>

<?php
include("components/footer.php");
?>
</footer>

</body>
</html>