<?php
include_once(__DIR__ . "../evenement.class.php");
include_once(__DIR__ . "/DAO.interface.php");

class EvenementDAO implements DAO{

     /**
     * Cette méthode retourne l'objet dont la clé primaire a été reçue en paramètre
     * @param int $id La clé primaire de l'objet à chercher
     * @return object|null L'objet trouvé ou null si non-trouvé
     */
    static public function findById(int $id): ?Evenement {

        try {
            $connexion = ConnexionBD::getInstance();
        } catch (Exception $e) {
            throw new Exception("Impossible d'obtenir la connexion à la BD");
        }

        $requete = $connexion->prepare("SELECT * FROM evenement WHERE id = :id");
        $requete->bindParam(':id', $id, PDO::PARAM_INT);
        $requete->execute();

        $evenement = null;
        if ($requete->rowCount() != 0) {
            $enr = $requete->fetch();
            $evenement = new Evenement(
                $enr['id'],
                $enr['idStats'],
                $enr['idOrganisateur'],
                $enr['nom'],
                $enr['lieu'],
                $enr['dateDebut'],
                $enr['dateFin'],
                $enr['nbBenevolesMax'],
                $enr['nbParticipantsMax'],
                $enr['etatBenevole'],
                $enr['categorie'],
                $enr['description'],
                $enr['etat'],
                $enr['nbInscriptions'],
                $enr['nbBenevolesAcceptes'],
                $enr['completBenevole'],
                $enr['completVisiteur']
            );
        }

        $requete->closeCursor();
        ConnexionBD::close();

        return $evenement;

    }

    /**
     * Retourne une liste de tous les objets de la table selon l'organisateur associe
     * @return array
     */
    static public function findAll():array{
        
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
                $enr['id'],
                $enr['idStats'],
                $enr['idOrganisateur'],
                $enr['nom'],
                $enr['lieu'],
                $enr['dateDebut'],
                $enr['dateFin'],
                $enr['nbBenevolesMax'],
                $enr['nbParticipantsMax'],
                $enr['etatBenevole'],
                $enr['categorie'],
                $enr['description'],
                $enr['etat'],
                $enr['nbInscriptions'],
                $enr['nbBenevolesAcceptes'],
                $enr['completBenevole'],
                $enr['completVisiteur']
            );

            $tableau[] = $evenement;
        }

        $requete->closeCursor();
        ConnexionBD::close();

        return $tableau;
    }

    /**
     * Creer un nouvel evenement
     * @param object $object 
     * @return bool true si successful
     */
    static public function save(object $object):bool{

        try {
            $connexion = ConnexionBD::getInstance();
        } catch (Exception $e) {
            throw new Exception("Impossible d'obtenir la connexion à la BD");
        }

        $requete = $connexion->prepare("
            INSERT INTO evenement 
            (idStats, idOrganisateur, nom, lieu, dateDebut, dateFin, nbBenevolesMax, nbParticipantsMax, etatBenevole, categorie, description, etat, nbInscriptions, nbBenevolesAcceptes, completBenevole, completVisiteur)
            VALUES 
            (:idStats, :idOrganisateur, :nom, :lieu, :dateDebut, :dateFin, :nbBenevolesMax, :nbParticipantsMax, :etatBenevole, :categorie, :description, :etat, :nbInscriptions, :nbBenevolesAcceptes, :completBenevole, :completVisiteur)
        ");

        $requete->bindValue(':idStats', $object->getIdStats(), PDO::PARAM_INT);
        $requete->bindValue(':idOrganisateur', $object->getIdOrganisateur(), PDO::PARAM_INT);
        $requete->bindValue(':nom', $object->getNom(), PDO::PARAM_STR);
        $requete->bindValue(':lieu', $object->getLieu(), PDO::PARAM_STR);
        $requete->bindValue(':dateDebut', $object->getDateDebut(), PDO::PARAM_STR);
        $requete->bindValue(':dateFin', $object->getDateFin(), PDO::PARAM_STR);
        $requete->bindValue(':nbBenevolesMax', $object->getNbBenevolesMax(), PDO::PARAM_INT);
        $requete->bindValue(':nbParticipantsMax', $object->getNbParticipantsMax(), PDO::PARAM_INT);
        $requete->bindValue(':etatBenevole', $object->getEtatBenevole(), PDO::PARAM_BOOL);
        $requete->bindValue(':categorie', $object->getCategorie(), PDO::PARAM_STR);
        $requete->bindValue(':description', $object->getDescription(), PDO::PARAM_STR);
        $requete->bindValue(':etat', $object->getEtat(), PDO::PARAM_STR);
        $requete->bindValue(':nbInscriptions', $object->getNbInscriptions(), PDO::PARAM_INT);
        $requete->bindValue(':nbBenevolesAcceptes', $object->getNbBenevolesAcceptes(), PDO::PARAM_INT);
        $requete->bindValue(':completBenevole', $object->getCompletBenevole(), PDO::PARAM_BOOL);
        $requete->bindValue(':completVisiteur', $object->getCompletVisiteur(), PDO::PARAM_BOOL);

        $success = $requete->execute();
        if ($success) {
            $object->setId((int)$connexion->lastInsertId());
        }

        return $success;
    }

    /**
     * Modifier un evenement
     * @param object $object
     * @return bool true si successful
     */
    static public function update(object $object):bool{

        try {
            $connexion = ConnexionBD::getInstance();
        } catch (Exception $e) {
            throw new Exception("Impossible d'obtenir la connexion à la BD");
        }

        $requete = $connexion->prepare("
            UPDATE evenement SET 
            idStats = :idStats, idOrganisateur = :idOrganisateur, nom = :nom, lieu = :lieu,
            dateDebut = :dateDebut, dateFin = :dateFin, nbBenevolesMax = :nbBenevolesMax, 
            nbParticipantsMax = :nbParticipantsMax, etatBenevole = :etatBenevole, categorie = :categorie, 
            description = :description, etat = :etat, nbInscriptions = :nbInscriptions, 
            nbBenevolesAcceptes = :nbBenevolesAcceptes, completBenevole = :completBenevole, 
            completVisiteur = :completVisiteur
            WHERE id = :id
        ");

        $requete->bindValue(':id', $object->getId(), PDO::PARAM_INT);
        $requete->bindValue(':idStats', $object->getIdStats(), PDO::PARAM_INT);
        $requete->bindValue(':idOrganisateur', $object->getIdOrganisateur(), PDO::PARAM_INT);
        $requete->bindValue(':nom', $object->getNom(), PDO::PARAM_STR);
        $requete->bindValue(':lieu', $object->getLieu(), PDO::PARAM_STR);
        $requete->bindValue(':dateDebut', $object->getDateDebut(), PDO::PARAM_STR);
        $requete->bindValue(':dateFin', $object->getDateFin(), PDO::PARAM_STR);
        $requete->bindValue(':nbBenevolesMax', $object->getNbBenevolesMax(), PDO::PARAM_INT);
        $requete->bindValue(':nbParticipantsMax', $object->getNbParticipantsMax(), PDO::PARAM_INT);
        $requete->bindValue(':etatBenevole', $object->getEtatBenevole(), PDO::PARAM_BOOL);
        $requete->bindValue(':categorie', $object->getCategorie(), PDO::PARAM_STR);
        $requete->bindValue(':description', $object->getDescription(), PDO::PARAM_STR);
        $requete->bindValue(':etat', $object->getEtat(), PDO::PARAM_STR);
        $requete->bindValue(':nbInscriptions', $object->getNbInscriptions(), PDO::PARAM_INT);
        $requete->bindValue(':nbBenevolesAcceptes', $object->getNbBenevolesAcceptes(), PDO::PARAM_INT);
        $requete->bindValue(':completBenevole', $object->getCompletBenevole(), PDO::PARAM_BOOL);
        $requete->bindValue(':completVisiteur', $object->getCompletVisiteur(), PDO::PARAM_BOOL);

        return $requete->execute();
    }

    /**
     * Supprimer un evenement
     * @param object $object
     * @return bool true si successful
     */
    static public function delete(object $object):bool{
        
        try {
            $connexion = ConnexionBD::getInstance();
        } catch (Exception $e) {
            throw new Exception("Impossible d'obtenir la connexion à la BD");
        }
    
        $requete = $connexion->prepare("DELETE FROM products WHERE id = :id");
    
        // Stockage dans une variable locale
        $id = $object->getId();
    
        // Liaison du paramètre
        $requete->bindParam(':id', $id, PDO::PARAM_INT);
    
        return $requete->execute();
    }


}
?>