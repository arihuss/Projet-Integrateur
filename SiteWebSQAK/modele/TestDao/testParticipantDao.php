<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Tests pour la classe ParticipantDAO</title>
    <style>
        body {
            background-color: #F5F5F5;
            font-family: Arial, sans-serif;
            padding: 20px;
        }
        h1 {
            color: #444;
            text-align: center;
        }
        table {
            border-collapse: collapse;
            margin: auto;
            width: 90%;
        }
        th, td {
            border: 2px solid #888;
            padding: 12px;
            text-align: left;
        }
        th {
            background-color: #4444CC;
            color: white;
        }
        td {
            background-color: #EEE;
        }
    </style>
</head>
<body>

<h1>Tests pour la classe ParticipantDAO</h1>

<?php
include_once("../../modele/participant.class.php");
include_once("../../modele/DAO/ParticipantDAO.php");
include_once("../../modele/ConnexionBD.php"); // à ajuster selon ton projet

// Test 1 : findByRole("appliquant")
$appliquants = ParticipantDAO::findByRole("appliquant");

// Test 2 : findById (si des appliquants existent)
$firstId = count($appliquants) > 0 ? $appliquants[0]->getIdInscription() : 1;
$participant = ParticipantDAO::findById($firstId);
?>

<table>
    <tr>
        <th>Méthode</th>
        <th>Résultat</th>
    </tr>

    <tr>
        <td>findByRole("appliquant")</td>
        <td>
            <?php
            echo "Nombre d'appliquants : " . count($appliquants);
            echo "<ul>";
            foreach ($appliquants as $p) {
                echo "<li>ID utilisateur : " . $p->getIdUtilisateur() . 
                     " | ID événement : " . $p->getIdEvenement() . 
                     " | Rôle : " . $p->getRole() . "</li>";
            }
            echo "</ul>";
            ?>
        </td>
    </tr>

    <tr>
        <td>findById(<?= $firstId ?>)</td>
        <td>
            <?php
            if ($participant) {
                echo "Participant trouvé : ID utilisateur = " . $participant->getIdUtilisateur() . 
                     ", rôle = " . $participant->getRole();
            } else {
                echo "Aucun participant trouvé avec l'ID $firstId";
            }
            ?>
        </td>
    </tr>

    <tr>
        <td>accepterAppliquant()</td>
        <td>
            <?php
            if ($participant) {
                $success = ParticipantDAO::accepterAppliquant($participant);
                echo $success ? "Rôle changé à 'benevole'." : "Échec du changement de rôle.";
            } else {
                echo "Aucun participant pour tester.";
            }
            ?>
        </td>
    </tr>

    <tr>
        <td>refuserApplicant()</td>
        <td>
            <?php
            if ($participant) {
                $success = ParticipantDAO::refuserApplicant($participant);
                echo $success ? "Participant supprimé." : "Échec de la suppression.";

                // Vérification
                $verif = ParticipantDAO::findById($firstId);
                echo $verif ? "<br> Le participant existe encore." : "<br> Le participant a bien été supprimé.";
            } else {
                echo "Aucun participant pour tester.";
            }
            ?>
        </td>
    </tr>
</table>

</body>
</html>
