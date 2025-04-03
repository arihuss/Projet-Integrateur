<!DOCTYPE html>
<html lang="fr">

<head>
    <meta charset="UTF-8">
    <title>Tests pour la classe CommentaireDAO</title>
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

        th,
        td {
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

    <h1>Tests pour la classe CommentaireDAO</h1>

    <?php
    include_once("../../modele/commentaire.class.php");
    include_once("../../modele/DAO/CommentaireDAO.class.php");

    ?>

    <table>
        <tr>
            <th>Methode</th>
            <th>Resultat</th>
        </tr>
        <tr>
            <td>
                <h2>Methode findByEvenement</h2>
            </td>
            <td>
                <?php
                echo "<h3>Liste de tous les commentaires de l'evenement avec l'id 1:</h3>";
                $tabCommentaires = CommentaireDAO::findByEvenement(1);
                echo "<ul>";
                foreach ($tabCommentaires as $commentaire) {
                    echo "<li>$commentaire</li>";
                }
                echo "</ul>";
                ?>
            </td>
        </tr>
    </table>
</body>

</html>