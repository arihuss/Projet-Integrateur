<!DOCTYPE html>
<html lang="fr">

<head>
    <meta charset="UTF-8">
    <title>SQAK - Accueil</title>
    <link rel="stylesheet" type="text/css" href="./css/styles.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
</head>

<body>
<body>

<header>
    <?php
    include("components/header.php");
    ?>
</header>

<div class="container">
    <div class="content">
        <h2>Mes événements</h2>
    
        <button class="add-button" onclick="window.location.href='ajouter-evenement.php'">Ajouter</button>

        <div class="event-grid">
            <div class="event-card">
                <div class="event-title">Entretient d'arbres</div>
                <img src=".\img\event-arbre.svg" alt="img-evenement">">
                <div class="event-date">13/12/2025</div>
            </div>

            <div class="event-card">
                <div class="event-title">Entretient d'arbres</div>
                <img src=".\img\event-arbre.svg" alt="img-evenement">">
                <div class="event-date">13/12/2025</div>
            </div>

            <div class="event-card">
                <div class="event-title">Entretient d'arbres</div>
                <img src=".\img\event-arbre.svg" alt="img-evenement">
                <div class="event-date">13/12/2025</div>
            </div>

            <div class="event-card">
                <div class="event-title">Entretient d'arbres</div>
                <img src=".\img\event-arbre.svg" alt="img-evenement">
                <div class="event-date">13/12/2025</div>
            </div>

            <div class="event-card">
                <div class="event-title">Entretient d'arbres</div>
                <img src=".\img\event-arbre.svg" alt="img-evenement">
                <div class="event-date">13/12/2025</div>
            </div>

            <div class="event-card">
                <div class="event-title">Entretient d'arbres</div>
                <img src=".\img\event-arbre.svg" alt="img-evenement">
                <div class="event-date">13/12/2025</div>
            </div>
        </div>
    </div>
</div>

<footer>
    <?php
    include("components/footer.php");
    ?>
</footer>

</body>

</body>

</html>

