<!DOCTYPE html>
<html lang="fr">

<head>
    <meta charset="UTF-8">
    <title>SQAK - Authentifier son compte</title>
    <link rel="stylesheet" type="text/css" href="./css/styles.css">
    <link rel="stylesheet" type="text/css" href="./css/confirmation.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    <link href='https://fonts.googleapis.com/css?family=Inter' rel='stylesheet'>
</head>

<body>
    <header><?php include("components/header.php") ?></header>

    <div class="container">

        <?php if (isset($messagesErreur) && !empty($messagesErreur)): ?>
            <div class="erreurs">
                <?php foreach ($messagesErreur as $msg): ?>
                    <p style="color: red;"><?= htmlspecialchars($msg) ?></p>
                <?php endforeach; ?>
            </div>
        <?php endif; ?>

        <form action="" method="POST">
            <h2>Entrez le code de confirmation</h2>
            <input id="code-confirmation" name="code" type="text" required>

            <br><br>
            <button type="submit" class="btn-jaune">Confirmer</button>
        </form>

        <br>
        <div>
            <a href="?action=confirmation">Renvoyer le code</a> 
        </div>
    </div>

    <footer><?php include("components/footer.php"); ?> </footer>
    <script src="js/general.js"></script>
</body>

</html>
