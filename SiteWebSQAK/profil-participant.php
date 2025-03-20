<!DOCTYPE html>
<html lang="fr">

<head>
    <meta charset="UTF-8">
    <title>SQAK - Profil participant</title>
    <link rel="stylesheet" type="text/css" href="./css/styles.css">
    <link rel="stylesheet" type="text/css" href="./css/profil-participant.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    <link href='https://fonts.googleapis.com/css?family=Inter' rel='stylesheet'>
</head>

<body>
<header>
        <?php
        include("components/header.php")
        ?>
    </header>

<div class="container" id="section-profil">

 <div id="profil-appliquant">
 <a href="evenement.php"><i class="fa-solid fa-circle-left"></i></a>

       <h2>Profil de l'applicant </h2>
       <img src=".\img\profilapplicant.svg" alt="img-profil">
       <h3 id="nom-applicant">Liliane Belvier</h3>
       
            <p class="content">Courriel:<br>
               Liliane.belvier@hotmail.com<br><br>
               
               Numéro de téléphone:<br>
               514-678-0955
               <a class="btn-jaune" href="contacter"> Contacter </a>
            </p>

   </div>
        


<div id="experience-appliquant">
 <h2>Expériences de bénévolat</h2>

      <div id="experience">
        <div id="date">
            <h3>27 <br> janvier <br> 2025</h3>
        </div>
        <div id="info">   
            <img src=".\img\event-arbre.svg" alt="img-evenement">   
            <p class="content">   
               Ramassage déchets <br>
               Lieu: plage Oka <br>
               Organisation: Terre-Sauve
            </p>
        </div>
      </div>


</div>
</div>
 

<footer><?php include("components/footer.php");?></footer>
<script src="js/general.js"></script>

</body>
</html>