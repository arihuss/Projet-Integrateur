<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Test - StatistiqueDAO</title>
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

<h1>Tests pour la classe StatistiqueDAO</h1>

<?php
include_once($_SERVER['DOCUMENT_ROOT'] . "/../../modele/statistiques.class.php");
include_once($_SERVER['DOCUMENT_ROOT'] . "/../../modele/DAO/statistiqueDAO.php");
include_once($_SERVER['DOCUMENT_ROOT'] . "/../../modele/ConnexionBD.php");


$nouvelleStat = new Statistique(
    0,   
    10,   
    5,   
    100,  
    200,  
    50    
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
            $result = StatistiqueDAO::save($nouvelleStat);
            echo $result
                ? "Statistique ajoutée avec succès (ID : " . $nouvelleStat->getIdStatistique() . ")"
                : "Échec de l'ajout.";
            ?>
        </td>
    </tr>

    <tr>
        <td>findById()</td>
        <td>
            <?php
            $stat = StatistiqueDAO::findById($nouvelleStat->getIdStatistique());
            if ($stat) {
                echo "Stat trouvée : vues = " . $stat->getNbVues() . ", likes = " . $stat->getNbLikes();
            } else {
                echo "Aucune statistique trouvée.";
            }
            ?>
        </td>
    </tr>

  
    <tr>
        <td>findAll()</td>
        <td>
            <?php
            $stats = StatistiqueDAO::findAll();
            echo "Nombre total de statistiques : " . count($stats);
            echo "<ul>";
            foreach ($stats as $s) {
                echo "<li>ID " . $s->getIdStatistique() . " — vues : " . $s->getNbVues() . " | likes : " . $s->getNbLikes() . "</li>";
            }
            echo "</ul>";
            ?>
        </td>
    </tr>


    <tr>
        <td>update()</td>
        <td>
            <?php
            $nouvelleStat->setNbVues(999);
            $nouvelleStat->setNbLikes(888);
            $success = StatistiqueDAO::update($nouvelleStat);
            echo $success ? "Mise à jour réussie (vues=999, likes=888)" : "Échec de la mise à jour.";
            ?>
        </td>
    </tr>
</table>

</body>
</html>
