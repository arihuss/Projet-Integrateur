<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Tests pour la classe EvenementDAO</title>
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

<h1>Tests pour la classe EvenementDAO</h1>

<?php
include_once($_SERVER['DOCUMENT_ROOT'] . "/../../modele/evenement.class.php");
include_once($_SERVER['DOCUMENT_ROOT'] . "/../../modele/DAO/evenementDAO.class.php");

$nouvelEvenement = new Evenement(
    0,  
    11, 
    21, 
    "Test DAO",
    "Montréal", 
    "2025-04-01", 
    "2025-04-02", 
    50, 
    300, 
    true, 
    "Culture", 
    "Test d'insertion DAO", 
    "Ouvert", 
    0, 
    0, 
    false, 
    false 
);
?>

<table>
    <tr>
        <th>Méthode</th>
        <th>Résultat</th>
    </tr>

    <tr>
        <td>save()</td>
        <td>
            <?php
            $result = EvenementDAO::save($nouvelEvenement);
            if ($result) {
                echo " Événement ajouté avec succès. ID généré : " . $nouvelEvenement->getId();
            } else {
                echo "Échec de l'ajout de l'événement.";
            }
            ?>
        </td>
    </tr>

    
    <tr>
        <td>findById()</td>
        <td>
            <?php
            $event = EvenementDAO::findById($nouvelEvenement->getId());
            if ($event) {
                echo "Événement trouvé : " . $event->getNom() . " à " . $event->getLieu();
            } else {
                echo "Aucun événement trouvé avec l'ID : " . $nouvelEvenement->getId();
            }
            ?>
        </td>
    </tr>

  
    <tr>
        <td>findAll()</td>
        <td>
            <?php
            $events = EvenementDAO::findAll();
            echo "Nombre d'événements trouvés : " . count($events);
            echo "<ul>";
            foreach ($events as $e) {
                echo "<li>" . $e->getNom() . " à " . $e->getLieu() . "</li>";
            }
            echo "</ul>";
            ?>
        </td>
    </tr>


    <tr>
        <td>update()</td>
        <td>
            <?php
            $nouvelEvenement->setLieu("Québec");
            $nouvelEvenement->setDescription("Description modifiée");
            $updateSuccess = EvenementDAO::update($nouvelEvenement);
            echo $updateSuccess ? " Mise à jour réussie." : "Échec de la mise à jour.";
            ?>
        </td>
    </tr>

    
    <tr>
        <td>delete()</td>
        <td>
            <?php
            $deleteSuccess = EvenementDAO::delete($nouvelEvenement);
            echo $deleteSuccess ? "Suppression réussie." : "Échec de la suppression.";

            $event = EvenementDAO::findById($nouvelEvenement->getId());
            echo $event ? "<br> L'événement existe encore !" : "<br> L'événement a bien été supprimé.";
            ?>
        </td>
    </tr>
</table>

</body>
</html>
