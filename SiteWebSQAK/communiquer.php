<!DOCTYPE html>
<html lang="fr">

<head>
    <meta charset="UTF-8">
    <title>SQAK - Communiquer</title>
    <link rel="stylesheet" type="text/css" href="./css/styles.css">
    <link rel="stylesheet" type="text/css" href="./css/communiquer.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    <link href='https://fonts.googleapis.com/css?family=Inter' rel='stylesheet'>
</head>

<body>
    <header> <?php include("components/header.php") ?></header>

    <!-- SECTION : Moyens de communication + Destinataires (sans bloc coloré) -->
    <form id="communicationForm">
        <div class="section-flex">
            <!-- Moyens de communication -->
            <div class="section-block">
                <h2 class="section-title">Moyen de communication</h2>
                <div class="checkbox-group">
                    <input type="checkbox" id="sms"> <label for="sms">SMS</label>
                    <input type="checkbox" id="email"> <label for="email">Courriel</label>
                    <input type="checkbox" id="notification"> <label for="notification">Notification</label>
                </div>
            </div>

            <!-- Destinataires -->
            <div class="section-block">
                <h2 class="section-title">Destinataires</h2>
                <div class="checkbox-group">
                    <input type="checkbox" id="appliquants"> <label for="appliquants">Appliquants</label>
                    <input type="checkbox" id="benevoles"> <label for="benevoles">Bénévoles</label>
                    <input type="checkbox" id="invites"> <label for="invites">Invités</label>
                </div>
            </div>
        </div>
    </form>

    <!-- SECTION : Zone de message (dans bloc séparé) -->
    <div class="message-box">
        <form>
            <label for="message" class="section-title message-label">Message *</label>
            <textarea id="message" name="message" placeholder="Votre message" required></textarea>

            <div class="buttons">
                <button type="submit" class="btn btn-send">Envoyer</button>
                <button type="button" class="btn btn-back" onclick="history.back()">Revenir</button>
            </div>
        </form>
    </div>

    <footer> <?php include("components/footer.php"); ?> </footer>
    <script src="js/general.js"></script>
</body>

</html>
