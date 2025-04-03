<?php
include_once(__DIR__ . "/../evenement.class.php");
include_once(__DIR__ . "/DAO.interface.php");
include_once(__DIR__ . '/../DAO/connexionBD.class.php');

class EvenementDAO implements DAO{

    static public function findById(int $id): ?Evenement {
        try {
            $connexion = ConnexionBD::getInstance();
        } catch (Exception $e) {
            throw new Exception("Impossible d'obtenir la connexion à la BD");
        }

        $requete = $connexion->prepare("SELECT * FROM evenement WHERE id_evenement = :id");
        $requete->bindParam(':id', $id, PDO::PARAM_INT);
        $requete->execute();

        $evenement = null;
        if ($requete->rowCount() != 0) {
            $enr = $requete->fetch();
            $evenement = new Evenement(
                $enr['id_evenement'],
                $enr['id_statistique'],
                $enr['id_organisateur'],
                $enr['nom_event'],
                $enr['lieu'],
                $enr['date_debut'],
                $enr['date_fin'],
                $enr['nb_benevoles_max'],
                $enr['nb_participants_max'],
                $enr['etat_benevole'],
                $enr['categorie'],
                $enr['description'],
                $enr['etat'],
                $enr['nb_inscriptions'],
                $enr['nb_benevoles_acceptes'],
                $enr['complet_benevole'],
                $enr['complet_visiteur']
            );
        }

        $requete->closeCursor();
        ConnexionBD::close();

        return $evenement;
    }

    static public function findAll(): array {
        try {
            $connexion = ConnexionBD::getInstance();
        } catch (Exception $e) {
            throw new Exception("Impossible d'obtenir la connexion à la BD");
        }

        $tableau = [];
        $requete = $connexion->prepare("SELECT * FROM evenement");
        $requete->execute();

        foreach ($requete as $enr) {
            $evenement = new Evenement(
                $enr['id_evenement'],
                $enr['id_statistique'],
                $enr['id_organisateur'],
                $enr['nom_event'],
                $enr['lieu'],
                $enr['date_debut'],
                $enr['date_fin'],
                $enr['nb_benevoles_max'],
                $enr['nb_participants_max'],
                $enr['etat_benevole'],
                $enr['categorie'],
                $enr['description'],
                $enr['etat'],
                $enr['nb_inscriptions'],
                $enr['nb_benevoles_acceptes'],
                $enr['complet_benevole'],
                $enr['complet_visiteur']
            );
            $tableau[] = $evenement;
        }

        $requete->closeCursor();
        ConnexionBD::close();

        return $tableau;
    }

    static public function save(object $object): bool {
        try {
            $connexion = ConnexionBD::getInstance();
        } catch (Exception $e) {
            throw new Exception("Impossible d'obtenir la connexion à la BD");
        }

        $requete = $connexion->prepare("INSERT INTO evenement 
        (id_statistique, id_organisateur, nom_event, lieu, date_debut, date_fin, nb_benevoles_max, nb_participants_max, etat_benevole, categorie, description, etat, nb_inscriptions, nb_benevoles_acceptes, complet_benevole, complet_visiteur) 
        VALUES (:id_statistique, :id_organisateur, :nom_event, :lieu, :date_debut, :date_fin, :nb_benevoles_max, :nb_participants_max, :etat_benevole, :categorie, :description, :etat, :nb_inscriptions, :nb_benevoles_acceptes, :complet_benevole, :complet_visiteur)");

        $requete->bindValue(':id_statistique', $object->getIdStats(), PDO::PARAM_INT);
        $requete->bindValue(':id_organisateur', $object->getIdOrganisateur(), PDO::PARAM_INT);
        $requete->bindValue(':nom_event', $object->getNom(), PDO::PARAM_STR);
        $requete->bindValue(':lieu', $object->getLieu(), PDO::PARAM_STR);
        $requete->bindValue(':date_debut', $object->getDateDebut(), PDO::PARAM_STR);
        $requete->bindValue(':date_fin', $object->getDateFin(), PDO::PARAM_STR);
        $requete->bindValue(':nb_benevoles_max', $object->getNbBenevolesMax(), PDO::PARAM_INT);
        $requete->bindValue(':nb_participants_max', $object->getNbParticipantsMax(), PDO::PARAM_INT);
        $requete->bindValue(':etat_benevole', $object->getEtatBenevole(), PDO::PARAM_BOOL);
        $requete->bindValue(':categorie', $object->getCategorie(), PDO::PARAM_STR);
        $requete->bindValue(':description', $object->getDescription(), PDO::PARAM_STR);
        $requete->bindValue(':etat', $object->getEtat(), PDO::PARAM_STR);
        $requete->bindValue(':nb_inscriptions', $object->getNbInscriptions(), PDO::PARAM_INT);
        $requete->bindValue(':nb_benevoles_acceptes', $object->getNbBenevolesAcceptes(), PDO::PARAM_INT);
        $requete->bindValue(':complet_benevole', $object->getCompletBenevole(), PDO::PARAM_BOOL);
        $requete->bindValue(':complet_visiteur', $object->getCompletVisiteur(), PDO::PARAM_BOOL);

        $success = $requete->execute();
        if ($success) {
            $object->setId((int)$connexion->lastInsertId());
        }

        return $success;
    }

    static public function update(object $object): bool {
        try {
            $connexion = ConnexionBD::getInstance();
        } catch (Exception $e) {
            throw new Exception("Impossible d'obtenir la connexion à la BD");
        }

        $requete = $connexion->prepare("UPDATE evenement SET 
        id_statistique = :id_statistique, id_organisateur = :id_organisateur, nom_event = :nom_event, lieu = :lieu,
        date_debut = :date_debut, date_fin = :date_fin, nb_benevoles_max = :nb_benevoles_max, nb_participants_max = :nb_participants_max, 
        etat_benevole = :etat_benevole, categorie = :categorie, description = :description, etat = :etat, 
        nb_inscriptions = :nb_inscriptions, nb_benevoles_acceptes = :nb_benevoles_acceptes, complet_benevole = :complet_benevole, 
        complet_visiteur = :complet_visiteur WHERE id_evenement = :id");

        $requete->bindValue(':id', $object->getId(), PDO::PARAM_INT);
        $requete->bindValue(':id_statistique', $object->getIdStats(), PDO::PARAM_INT);
        $requete->bindValue(':id_organisateur', $object->getIdOrganisateur(), PDO::PARAM_INT);
        $requete->bindValue(':nom_event', $object->getNom(), PDO::PARAM_STR);
        $requete->bindValue(':lieu', $object->getLieu(), PDO::PARAM_STR);
        $requete->bindValue(':date_debut', $object->getDateDebut(), PDO::PARAM_STR);
        $requete->bindValue(':date_fin', $object->getDateFin(), PDO::PARAM_STR);
        $requete->bindValue(':nb_benevoles_max', $object->getNbBenevolesMax(), PDO::PARAM_INT);
        $requete->bindValue(':nb_participants_max', $object->getNbParticipantsMax(), PDO::PARAM_INT);
        $requete->bindValue(':etat_benevole', $object->getEtatBenevole(), PDO::PARAM_BOOL);
        $requete->bindValue(':categorie', $object->getCategorie(), PDO::PARAM_STR);
        $requete->bindValue(':description', $object->getDescription(), PDO::PARAM_STR);
        $requete->bindValue(':etat', $object->getEtat(), PDO::PARAM_STR);
        $requete->bindValue(':nb_inscriptions', $object->getNbInscriptions(), PDO::PARAM_INT);
        $requete->bindValue(':nb_benevoles_acceptes', $object->getNbBenevolesAcceptes(), PDO::PARAM_INT);
        $requete->bindValue(':complet_benevole', $object->getCompletBenevole(), PDO::PARAM_BOOL);
        $requete->bindValue(':complet_visiteur', $object->getCompletVisiteur(), PDO::PARAM_BOOL);

        return $requete->execute();
    }

    static public function delete(object $object): bool {
        try {
            $connexion = ConnexionBD::getInstance();
        } catch (Exception $e) {
            throw new Exception("Impossible d'obtenir la connexion à la BD");
        }

        $requete = $connexion->prepare("DELETE FROM evenement WHERE id_evenement = :id");
        $id = $object->getId();
        $requete->bindParam(':id', $id, PDO::PARAM_INT);
        return $requete->execute();
    }
}
?>