<!DOCTYPE html>
<html lang="fr">

<head>
    <meta charset="UTF-8">
    <title>Tests pour la classe OrganisateurDAO</title>
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

    <h1>Tests pour la classe OrganisateurDAO</h1>

    <?php
    include_once(__DIR__ . '/../organisateur.class.php');
    include_once(__DIR__ . '/../DAO/OrganisateurDAO.class.php');
    ?>

    <table>
        <tr>
            <th>Methode</th>
            <th>Resultat</th>
        </tr>
        <tr>
            <td>
                <h2>Test Connexion</h2>
            </td>
            <td>
                <?php
                try {
                    $db = ConnexionBD::getInstance();
                    echo "Connexion réussie !";
                } catch (Exception $e) {
                    echo "Erreur : " . $e->getMessage();
                }
                ?>
            </td>
        </tr>
        <tr>
            <td>
                <h2>Methode findById</h2>
            </td>
            <td>
                <?php
                echo "<h3>Recherche de l'organisateur avec un ID existant: </h3>";
                $unOrganisateur = OrganisateurDAO::findById(1);
                echo "<ul><li>Organisateur 1: " . ($unOrganisateur ? $unOrganisateur : "n'existe pas") . "</li></ul>";

                echo "<h3>Recherche de l'organisateur avec l'ID 999 (n'existe pas):</h3>";
                $unOrganisateur = OrganisateurDAO::findById(999);
                echo "<ul><li>Organisateur 999: " . ($unOrganisateur ? $unOrganisateur : "n'existe pas") . "</li></ul>";
                ?>
            </td>
        </tr>

        <tr>
            <td>
                <h2>Methode save</h2>
            </td>
            <td>
                <?php
                echo "<h3>Insertion d'un nouvel organisateur :</h3>";

                // Création d'un nouvel organisateur
                $nouveauOrganisateur = new Organisateur(null,'Mario', 'Bros', 'mroi.bros@itsame.com', 'Hello, its A ME, MARIO', null, 'luigi123', 10);

                try {

                    // Enregistrement dans la base de données
                    $testSave = OrganisateurDAO::save($nouveauOrganisateur);

                    if ($testSave) {
                        echo "<ul><li>Insertion réussie. ID généré : " . $nouveauOrganisateur->getId() . "</li></ul>";

                        // Vérification des données insérées
                        $organisateurInsere = OrganisateurDAO::findById($nouveauOrganisateur->getId());
                        echo "<ul><li>Organisateur inséré : " . ($organisateurInsere ? $organisateurInsere : "n'existe pas") . "</li></ul>";
                    } else {
                        echo "<ul><li>Insertion échouée.</li></ul>";
                    }
                } catch (PDOException $e) {
                    echo "Erreur inattendue : " . $e->getMessage();
                }
                ?>
            </td>
        </tr>

        <tr>
            <td>
                <h2>Methode update</h2>
            </td>
            <td>
                <?php

                // Utiliser l'id de l'utilisateur nouvellement créé pour la mise à jour
                $id = $nouveauOrganisateur->getId(); // id de l'utilisateur nouvellement inséré
                $nouveauOrganisateurAuthentifie = OrganisateurDAO::findById($id); // Recherche de l'utilisateur par id
                
                if ($nouveauOrganisateurAuthentifie !== null) {
                    echo "<h3>Modification de l'organisateur authentifié :</h3>";

                    // Charger l'utilisateur authentifié dans $nouveauOrganisateur
                    $nouveauOrganisateurAuthentifie->setPrenom('Updated');
                    $nouveauOrganisateurAuthentifie->setNom('User Updated');
                    $nouveauOrganisateurAuthentifie->setCourriel('test@yahoo.com');

                    // Appeler la méthode update
                    $testUpdate = OrganisateurDAO::update($nouveauOrganisateurAuthentifie);

                    if ($testUpdate) {
                        echo "<ul><li>Modification réussie pour l'utilisateur avec ID : " . $nouveauOrganisateurAuthentifie->getId() . "</li></ul>";

                        // Vérification des modifications
                        $organisateurModifie = OrganisateurDAO::findById($nouveauOrganisateurAuthentifie->getId());
                        echo "<ul><li>Utilisateur modifié : " . ($organisateurModifie ? $organisateurModifie : "n'existe pas") . "</li></ul>";
                    } else {
                        echo "<ul><li>Modification échouée.</li></ul>";
                    }
                } else {
                    echo "<h3>L'utilisateur avec l'id ", $id, " n'existe pas pour une mise à jour.</h3>";
                }

                ?>
            </td>
        </tr>

        <tr>
            <td>
                <h2>Methode delete</h2>
            </td>
            <td>
                <?php

                // Utiliser l'id de l'utilisateur nouvellement créé pour la mise à jour
                $id = $nouveauOrganisateur->getId(); // id de l'utilisateur nouvellement inséré
                $nouveauOrganisateurAuthentifie = OrganisateurDAO::findById($id); // Recherche de l'utilisateur par id
                
                if ($nouveauOrganisateurAuthentifie !== null) {
                    echo "<h3>Suppression de l'organisateur authentifié :</h3>";

                    // Appeler la méthode delete
                    $testDelete = OrganisateurDAO::delete($nouveauOrganisateurAuthentifie);

                    if ($testDelete) {
                        echo "<ul><li>Suppression réussie pour l'organisateur avec ID : " . $nouveauOrganisateurAuthentifie->getId() . "</li></ul>";

                        // Vérification de la suppression
                        $organisateurSupprime = OrganisateurDAO::findById($nouveauOrganisateurAuthentifie->getId());
                        echo "<ul><li>Organisateur supprimé : " . ($organisateurSupprime ? $organisateurSupprime : "n'existe plus") . "</li></ul>";
                    } else {
                        echo "<ul><li>Suppression échouée.</li></ul>";
                    }
                } else {
                    echo "<h3>L'organisateur avec l'id ", $id, " n'existe pas pour une suppression.</h3>";
                }

                ?>

            </td>
        </tr>

    </table>

</body>

</html>