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
    <header>
        <?php
        include("components/header.php")
        ?>
    </header>

    <div class="container">
        <form action="" method="POST" >
                <h2>Entrez le code de confirmation</h2>
                 <input id="code-confirmation" type="text" required>
        </form>
        <br>
        <div>
            <a class="btn-jaune" href="page-principale.php">Confirmer</a><br>
            <a href="code-renvoye" > Renvoyez le code</a> <!-- code a renvoyer a changer-->
        </div>
        
       
    </div>

    <footer>
        <?php
        include("components/footer.php");
        ?>
    </footer>

</body>

</html>