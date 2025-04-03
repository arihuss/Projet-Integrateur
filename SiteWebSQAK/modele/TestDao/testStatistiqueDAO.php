<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Tests pour la classe StatistiqueDAO</title>
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
include_once("../../modele/statistiques.class.php");
include_once("../../modele/DAO/statistiqueDAO.php");
include_once("../../modele/ConnexionBD.php");

// Créer une nouvelle statistique pour les tests
$nouvelleStat = new Statistique(
    0,     // id_statistique 
    10,    // nb_visiteurs
    5,     // nb_benevoles
    100,   // nb_likes
    250,   // nb_vues
    15     // nb_partages
);
?>

<table>
    <tr>
        <th>Méthode</th>
        <th>Résultat</th>
    </tr>

    <!-- Test SAVE -->
    <tr>
        <td>save()</td>
        <td>
            <?php
            if (StatistiqueDAO::save($nouvelleStat)) {
                echo "Statistique ajoutée avec succès. ID : " . $nouvelleStat->getIdStatistique();
            } else {
                echo "Échec de l'ajout.";
            }
            ?>
        </td>
    </tr>

    <!-- Test FIND BY ID -->
    <tr>
        <td>findById()</td>
        <td>
            <?php
            $stat = StatistiqueDAO::findById($nouvelleStat->getIdStatistique());
            if ($stat) {
                echo "Stat trouvée : " . $stat->getNbVues() . " vues, " . $stat->getNbLikes() . " likes.";
            } else {
                echo "Aucune statistique trouvée.";
            }
            ?>
        </td>
    </tr>

    <!-- Test FIND ALL -->
    <tr>
        <td>findAll()</td>
        <td>
            <?php
            $stats = StatistiqueDAO::findAll();
            echo "Nombre total de statistiques : " . count($stats);
            echo "<ul>";
            foreach ($stats as $s) {
                echo "<li>ID " . $s->getIdStatistique() . " — vues : " . $s->getNbVues() . " | partages : " . $s->getNbPartages() . "</li>";
            }
            echo "</ul>";
            ?>
        </td>
    </tr>

    <!-- Test UPDATE -->
    <tr>
        <td>update()</td>
        <td>
            <?php
            $nouvelleStat->setNbVues(999);
            $nouvelleStat->setNbLikes(888);
            if (StatistiqueDAO::update($nouvelleStat)) {
                echo "Mise à jour réussie.";
            } else {
                echo "Échec de la mise à jour.";
            }
            ?>
        </td>
    </tr>

    <!-- Test DELETE -->
    <tr>
        <td>delete()</td>
        <td>
            <?php
            if (StatistiqueDAO::delete($nouvelleStat)) {
                echo "Suppression réussie.";
            } else {
                echo "Échec de la suppression.";
            }

            // Vérifier que c'est bien supprimé
            $verif = StatistiqueDAO::findById($nouvelleStat->getIdStatistique());
            echo $verif ? "<br>La statistique existe encore !" : "<br>La statistique a bien été supprimée.";
            ?>
        </td>
    </tr>
</table>

</body>
</html>
