<!DOCTYPE html>
<html lang="fr">

<head>
    <meta charset="UTF-8">
    <title>SQAK - Authentifier son compte</title>
    <link rel="stylesheet" type="text/css" href="./css/styles.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
</head>

<body>
<header>
        <?php
        include("components/header.php")
        ?>
    </header>

    <form action="" method="POST" >
        <div>
            <h2>Entrez le code de confirmation</h2>
             <input id="code-confirmation" type="text" required> 
        </div>

        <div>
        <input type="submit" value="Confirmer" required>
        <a href="code-renvoye" > Renvoyez le code</a> <!-- code a renvoyer a changer-->
        </div>
    </form>

    <footer>

<?php
include("components/footer.php");
?>
</footer>

</body>

</html>