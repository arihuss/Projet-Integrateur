<?php
include_once(__DIR__ . '/../organisateur.class.php');
include_once(__DIR__ . "/DAO.interface.php");
include_once(__DIR__ . '/../DAO/connexionBD.class.php');

class OrganisateurDAO implements DAO {

    static public function findById(int $id): ?Organisateur {
        try {
            $connexion = ConnexionBD::getInstance();
        } catch (Exception $e) {
            throw new Exception("Impossible d'obtenir la connexion à la BD");
        }

        $requete = $connexion->prepare("SELECT * FROM Organisateur WHERE id_organisateur = :id");
        $requete->bindParam(':id', $id, PDO::PARAM_INT);
        $requete->execute();

        $organisateur = null;
        if ($requete->rowCount() != 0) {
            $enr = $requete->fetch();
            $organisateur = new Organisateur(
                $enr['id_organisateur'],
                $enr['img_organisateur'] ?? null,
                $enr['prenom'] ?? null,
                $enr['nom'] ?? null,
                $enr['courriel'],
                $enr['bio'],
                $enr['nom_organisateur'] ?? null,
                $enr['mot_de_passe'],
                $enr['nb_events'],
                $enr['telephone'] ?? null,
                $enr['est_confirme'] ?? false,
                $enr['code_confirmation'] ?? null
            );
        }

        $requete->closeCursor();
        ConnexionBD::close();
        return $organisateur;
    }

    static public function findAll(): array {
        return [0]; // Non utilisé
    }

    static public function save(object $organisateur): bool {
        try {
            $connexion = ConnexionBD::getInstance();
        } catch (Exception $e) {
            throw new Exception("Impossible d'obtenir la connexion à la BD");
        }

        $requete = $connexion->prepare(
            "INSERT INTO Organisateur (
                img_organisateur, prenom, nom, courriel, bio, nom_organisateur, 
                mot_de_passe, nb_events, telephone, est_confirme, code_confirmation
            ) VALUES (
                :img_organisateur, :prenom, :nom, :courriel, :bio, :nomOrganisateur,
                :mdp, :nbEvents, :telephone, :est_confirme, :code_confirmation
            )"
        );

        $requete->bindValue(':img_organisateur', $organisateur->getImgOrganisateur(), PDO::PARAM_STR);
        $requete->bindValue(':prenom', $organisateur->getPrenom(), PDO::PARAM_STR);
        $requete->bindValue(':nom', $organisateur->getNom(), PDO::PARAM_STR);
        $requete->bindValue(':courriel', $organisateur->getCourriel(), PDO::PARAM_STR);
        $requete->bindValue(':bio', $organisateur->getBiographie(), PDO::PARAM_STR);
        $requete->bindValue(':nomOrganisateur', $organisateur->getNomOrganisateur(), PDO::PARAM_STR);
        $requete->bindValue(':mdp', $organisateur->getMotDePasse(), PDO::PARAM_STR);
        $requete->bindValue(':nbEvents', $organisateur->getNbEvents(), PDO::PARAM_INT);
        $requete->bindValue(':telephone', $organisateur->getTelephone(), PDO::PARAM_STR);
        $requete->bindValue(':est_confirme', $organisateur->getEstConfirme(), PDO::PARAM_BOOL);
        $requete->bindValue(':code_confirmation', $organisateur->getCodeConfirmation(), PDO::PARAM_STR);

        $success = $requete->execute();

        if ($success) {
            $organisateur->setId((int) $connexion->lastInsertId());
        } else {
            echo "<pre>Erreur SQL : ";
            print_r($requete->errorInfo());
            echo "</pre>";
        }

        return $success;
    }

    static public function update(object $organisateur): bool {
        try {
            $connexion = ConnexionBD::getInstance();
        } catch (Exception $e) {
            throw new Exception("Impossible d'obtenir la connexion à la BD");
        }

        $requete = $connexion->prepare(
            "UPDATE Organisateur SET 
                img_organisateur = :img_organisateur,
                prenom = :prenom,
                nom = :nom,
                courriel = :courriel,
                bio = :bio,
                nom_organisateur = :nomOrganisateur,
                mot_de_passe = :mdp,
                nb_events = :nbEvents,
                telephone = :telephone,
                est_confirme = :est_confirme,
                code_confirmation = :code_confirmation
            WHERE id_organisateur = :id"
        );

        $requete->bindValue(':id', $organisateur->getId(), PDO::PARAM_INT);
        $requete->bindValue(':img_organisateur', $organisateur->getImgOrganisateur(), PDO::PARAM_STR);
        $requete->bindValue(':prenom', $organisateur->getPrenom(), PDO::PARAM_STR);
        $requete->bindValue(':nom', $organisateur->getNom(), PDO::PARAM_STR);
        $requete->bindValue(':courriel', $organisateur->getCourriel(), PDO::PARAM_STR);
        $requete->bindValue(':bio', $organisateur->getBiographie(), PDO::PARAM_STR);
        $requete->bindValue(':nomOrganisateur', $organisateur->getNomOrganisateur(), PDO::PARAM_STR);
        $requete->bindValue(':mdp', $organisateur->getMotDePasse(), PDO::PARAM_STR);
        $requete->bindValue(':nbEvents', $organisateur->getNbEvents(), PDO::PARAM_INT);
        $requete->bindValue(':telephone', $organisateur->getTelephone(), PDO::PARAM_STR);
        $requete->bindValue(':est_confirme', $organisateur->getEstConfirme(), PDO::PARAM_BOOL);
        $requete->bindValue(':code_confirmation', $organisateur->getCodeConfirmation(), PDO::PARAM_STR);

        return $requete->execute();
    }

    static public function delete(object $organisateur): bool {
        try {
            $connexion = ConnexionBD::getInstance();
        } catch (Exception $e) {
            throw new Exception("Impossible d'obtenir la connexion à la BD");
        }

        $requete = $connexion->prepare("DELETE FROM Organisateur WHERE id_organisateur = :id");
        $requete->bindValue(':id', $organisateur->getId(), PDO::PARAM_INT);
        return $requete->execute();
    }

    static public function findByEmail(string $courriel): ?Organisateur {
        try {
            $connexion = ConnexionBD::getInstance();
        } catch (Exception $e) {
            throw new Exception("Connexion BD échouée");
        }

        $requete = $connexion->prepare("SELECT * FROM Organisateur WHERE courriel = :courriel");
        $requete->bindValue(':courriel', $courriel, PDO::PARAM_STR);
        $requete->execute();

        if ($requete->rowCount() === 0) return null;

        $enr = $requete->fetch();

        return new Organisateur(
            $enr['id_organisateur'],
            $enr['img_organisateur'] ?? null,
            $enr['prenom'] ?? null,
            $enr['nom'] ?? null,
            $enr['courriel'],
            $enr['bio'],
            $enr['nom_organisateur'] ?? null,
            $enr['mot_de_passe'],
            $enr['nb_events'],
            $enr['telephone'] ?? null,
            $enr['est_confirme'] ?? false,
            $enr['code_confirmation'] ?? null
        );
    }
}
