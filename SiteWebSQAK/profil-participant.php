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
    <header><?php include("components/header.php");
    include_once('modele/DAO/EvenementDAO.class.php');
    include_once('modele/DAO/ParticipantDAO.class.php');
    include_once('modele/DAO/UserDAO.class.php');

    $participant = ParticipantDAO::findById($_GET['id']);
    $event = EvenementDAO::findById($participant->getIdEvenement());
    $user = UtilisateurDAO::findById($participant->getIdUtilisateur());
    ?></header>

    <div class="container" id="section-profil">
        <?php
        echo "
        <div id='profil-appliquant'>
            <a href='?action=voirUnEvent&id=" . $event->getId() . "'><i class='fa-solid fa-circle-left'></i></a>
            <h2>Profil de l'applicant </h2>";
    
        if ($user->getImgUtilisateur()){
            echo"<img src='data:image/jpeg;base64," . base64_encode($user->getImgUtilisateur()) . "' alt='Logo Utulisateur'>"; 
        }else{
             echo"<img src='\img\default_profil.jpg' alt='Logo Utilisateur'>"; 
        }
        
           echo" <h3 id='nom-applicant'>" . $user->getPrenom() . " " . $user->getNom() . "</h3>
            <p class='content'>Courriel:<br>
                " . $user->getCourriel() . "<br><br>
                Numéro de téléphone:<br>
                " . $user->getNumTel() . "
                <a class='btn-jaune' href='mailto:" . $user->getCourriel() . "'> Contacter </a>
            </p>
        </div>";

        echo "<div id='experience-appliquant'>
            <h2>Expériences de bénévolat</h2>";

        $inscriptions = ParticipantDAO::getInscriptionByUserId($user->getIdUtilisateur());
        foreach ($inscriptions as $inscription) {
            $evenement = EvenementDAO::findById($inscription->getIdEvenement());
            $org = OrganisateurDAO::findById($evenement->getIdOrganisateur());
            echo "<div id='experience'>
                <div id='date'>
                    <h3>" . $evenement->getDateDebut() . "<br><br></h3> 
                </div>
                <div id='info'>
                    <img src='./img/event-arbre.svg' alt='img-evenement'>
                    <p class='content'>
                        " . $evenement->getNom() . " <br>
                        Lieu: " . $evenement->getLieu() . " <br>
                        Organisation: " . $org->getNom() . "
                    </p>
                   
                </div>
            </div>";
        }

        echo "</div>";
        ?>
    </div> <!-- fermeture correcte de container -->

    <footer><?php include("components/footer.php"); ?></footer>
    <script src="js/general.js"></script>

</body>

</html>