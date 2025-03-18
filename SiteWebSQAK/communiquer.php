<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>SQAK - Communiquer</title>
    <link rel="stylesheet" type="text/css" href="./css/styles.css">
</head>
<body>
<header>
        <?php
        include("components/header.php")
        ?>
    </header>

    <footer>

<?php
include("components/footer.php");
?>
</footer>

    <div class="content">
        <form id="communicationForm">
        <h2 class="section-title">Moyen de communication</h2>
            <div class="checkbox-group">
                <input type="checkbox" id="sms"> <label for="sms">SMS</label>
                <input type="checkbox" id="email"> <label for="email">Courriel</label>
                <input type="checkbox" id="notification"> <label for="notification">Notification</label>
            </div>

            <div class="section-title">Destinataires</div>
            <div class="checkbox-group">
                <input type="checkbox" id="appliquants"> <label for="appliquants">Appliquants</label>
                <input type="checkbox" id="benevoles"> <label for="benevoles">Bénévoles</label>
                <input type="checkbox" id="invites"> <label for="invites">Invités</label>
            </div>

            <div class="section-title">Message *</div>
            <textarea id="message" name="message" placeholder="Votre message" required></textarea>

            <div class="buttons">
                <button type="submit" class="btn btn-send">Envoyer</button>
                <button type="button" class="btn btn-back" onclick="history.back()">Revenir</button>
            </div>
        </form>
    </div>

</body>
</html>
