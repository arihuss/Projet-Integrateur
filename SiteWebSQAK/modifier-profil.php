<?php
session_start();
include_once("modele\DAO\OrganisateurDAO.class.php");

$message = '';
$typeMessage = '';

if (!isset($_SESSION['user_id'])) {
    // Rediriger vers la page de login si non connecté
    header("Location: index.php?action=seConnecter");
    exit;
}

$organisateur = OrganisateurDAO::findById($_SESSION['user_id']);


if ($_SERVER["REQUEST_METHOD"] === "POST") {
    $prenom = $_POST['prenom'] ?? null;
    $nom = $_POST['nom'] ?? null;
    $organisation = $_POST['organisation'] ?? null;
    $courriel = $_POST['courriel'];
    $tel = $_POST['tel'];
    $mdp = $_POST['mdp'];
    $cmdp = $_POST['Cmdp'];

    // Validation
    if ($mdp !== $cmdp) {
        $message = "Les mots de passe ne correspondent pas.";
        $typeMessage = "erreur";
    } else {
        // Mise à jour des infos
        $organisateur->setPrenom($prenom);
        $organisateur->setNom($nom);
        $organisateur->setNomOrganisateur($organisation);
        $organisateur->setCourriel($courriel);
        //$organisateur->setTelephone($tel);

        if (!empty($mdp)) {
            $utilisateur->setMotDePasse(password_hash($mdp, PASSWORD_DEFAULT));
        }

        // Gérer la photo quand c corriger
        if (isset($_FILES['photo']) && $_FILES['photo']['error'] == 0) {
            $targetDir = "uploads/";
            $fileName = basename($_FILES["photo"]["name"]);
            $targetFilePath = $targetDir . time() . "_" . $fileName;

            if (move_uploaded_file($_FILES["photo"]["tmp_name"], $targetFilePath)) {
                $utilisateur->setPhoto($targetFilePath);
            }
        }

        OrganisateurDAO::update($organisateur);
        $message = "Profil mis à jour avec succès.";
        $typeMessage = "confirmation";
    }
}
?>

<!DOCTYPE html>
<html lang="fr">

<head>
    <meta charset="UTF-8">
    <title>SQAK - Modifier Profil</title>
    <link rel="stylesheet" type="text/css" href="./css/styles.css">
    <link rel="stylesheet" type="text/css" href="./css/sign-in.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    <link href='https://fonts.googleapis.com/css?family=Inter' rel='stylesheet'>
</head>

<body>
    <header><?php include("components/header.php") ?></header>

    <div class="container">
        <?php if ($message): ?>
            <div class="message <?= $typeMessage ?>"><?= $message ?></div>
        <?php endif; ?>

        <form action="" method="POST" enctype="multipart/form-data">
            <h2>Modifier profil</h2>

            <div id="form-section">
                <div class="form-column">
                    <h3>Personne</h3>
                    <label for="prenom">Prénom:</label>
                    <input id="prenom" name="prenom" type="text" value="<?= $organisateur->getPrenom() ?>">
                    <br>
                    <label for="nom">Nom:</label>
                    <input id="nom" name="nom" type="text" value="<?= $organisateur->getNom() ?>">
                </div>

                <div class="form-column" id="ins-bar">
                    <h3> ou </h3>
                </div>

                <div class="form-column">
                    <h3 id="org-titre">Organisation</h3>
                    <label for="organisation">Nom de l'organisation:</label>
                    <input id="organisation" name="organisation" type="text"
                        value="<?= $organisateur->getNomOrganisateur() ?>">
                </div>
            </div>

            <div id="form-bottom">
                <label for="courriel">Courriel:</label>
                <input id="courriel" name="courriel" type="email" value="<?= $organisateur->getCourriel() ?>">
                <br>
                <label for="tel">Numéro de téléphone:</label>
                <input id="tel" name="tel" type="tel" 
                    pattern="^\d{3}-\d{3}-\d{4}$">
                <br>
                <label for="mdp">Mot de passe:</label>
                <input id="mdp" name="mdp" type="password" minlength="8">
                <br>
                <label for="Cmdp">Confirmation de mot de passe:</label>
                <input id="Cmdp" name="Cmdp" type="password">
                <br>
                <label for="file-photo">Choisir nouvelle photo:</label>
                <input id="file-photo" type="file" accept="image/png, image/jpeg" name="Choisir">
            </div>

            <div id="btn-container2">
                <button type="button" class="btn-rose" onclick="window.location.href='?action=profilOrganisateur'">Revenir</button>
                <button class="btn-jaune" type="submit">Sauvegarder</button>
            </div>
        </form>
    </div>

    <footer><?php include("components/footer.php") ?></footer>
    <script src="js/general.js"></script>
</body>

</html>