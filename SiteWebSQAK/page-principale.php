<!DOCTYPE html>
<html lang="fr">

<head>
    <meta charset="UTF-8">
    <title>SQAK - Accueil</title>
    <link rel="stylesheet" type="text/css" href="./css/styles.css">
    <link rel="stylesheet" type="text/css" href="./css/principale.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    <link href='https://fonts.googleapis.com/css?family=Inter' rel='stylesheet'>
</head>

<body>

<header><?php include("components/header.php");?></header>

<div class="container">
    <div>
        
    <div class="header-row">
    <h2>Mes événements</h2>
    <button class="add-button" onclick="window.location.href='ajouter-evenement.php'">Ajouter</button>
</div>


    <a href="evenement.php" class="event">
        <div class="event-grid">
            <div class="event-card">
                <div class="event-title">Entretient d'arbres</div>
                <img src=".\img\event-arbre.svg" alt="img-evenement">
                <div class="event-date">13/12/2025</div>
            </div>
    </a>

 <a href="evenement.php" class="event">
            <div class="event-card">
                <div class="event-title">Entretient d'arbres</div>
                <img src=".\img\event-arbre.svg" alt="img-evenement">
                <div class="event-date">13/12/2025</div>
            </div>
</a>
<a href="evenement.php" class="event">
            <div class="event-card">
                <div class="event-title">Entretient d'arbres</div>
                <img src=".\img\event-arbre.svg" alt="img-evenement">
                <div class="event-date">13/12/2025</div>
            </div>
</a>
<a href="evenement.php" class="event">
            <div class="event-card">
                <div class="event-title">Entretient d'arbres</div>
                <img src=".\img\event-arbre.svg" alt="img-evenement">
                <div class="event-date">13/12/2025</div>
            </div>
</a>
<a href="evenement.php" class="event">
            <div class="event-card">
                <div class="event-title">Entretient d'arbres</div>
                <img src=".\img\event-arbre.svg" alt="img-evenement">
                <div class="event-date">13/12/2025</div>
            </div>
</a>
<a href="evenement.php" class="event">
            <div class="event-card">
                <div class="event-title">Entretient d'arbres</div>
                <img src=".\img\event-arbre.svg" alt="img-evenement">
                <div class="event-date">13/12/2025</div>

            </div>
            </a>
        </div>
    </div>
</div>

<footer><?php include("components/footer.php");?></footer>
<script src="js/general.js"></script>

</body>

</html>
