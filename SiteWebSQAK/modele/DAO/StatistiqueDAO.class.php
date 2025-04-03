<?php
include_once(__DIR__ . "../statistiques.class.php");
include_once(__DIR__ . "/DAO.interface.php");

class StatistiqueDAO {

     /**
     * Cette méthode retourne les statistiques associes a un evenement selon le id
     * @param int $id La clé primaire de l'objet à chercher
     * @return object|null L'objet trouvé ou null si non-trouvé
     */
    static public function findById(int $id): ?Statistique {
        try {
            $connexion = ConnexionBD::getInstance();
        } catch (Exception $e) {
            throw new Exception("Impossible d'obtenir la connexion à la base de données");
        }
    
        $stmt = $connexion->prepare("SELECT * FROM Statistique WHERE id_statistique = :id");
        $stmt->bindParam(':id', $id, PDO::PARAM_INT);
        $stmt->execute();
    
        $stat = null;
        if ($stmt->rowCount() > 0) {
            $enr = $stmt->fetch();
            $stat = new Statistique(
                $enr['id_statistique'],
                $enr['nb_visiteurs'],
                $enr['nb_benevoles'],
                $enr['nb_likes'],
                $enr['nb_vues'],
                $enr['nb_partages']
            );
        }
    
        $stmt->closeCursor();
        ConnexionBD::close();
    
        return $stat;
    }
//ecq je dois en mettre plus que ce que tu m as mis 
}
/**
     * Récupère toutes les statistiques
     */
    static public function findAll(): array {
        $connexion = ConnexionBD::getInstance();

        $stmt = $connexion->prepare("SELECT * FROM Statistique");
        $stmt->execute();

        $resultats = [];
        while ($enr = $stmt->fetch()) {
            $resultats[] = new Statistique(
                $enr['id_statistique'],
                $enr['nb_visiteurs'],
                $enr['nb_benevoles'],
                $enr['nb_likes'],
                $enr['nb_vues'],
                $enr['nb_partages']
            );
        }

        $stmt->closeCursor();
        ConnexionBD::close();

        return $resultats;
    }

    /**
     * Ajoute une nouvelle statistique dans la base
     */
    static public function save(object $object): bool {
        $connexion = ConnexionBD::getInstance();

        $stmt = $connexion->prepare("
            INSERT INTO Statistique 
            (nb_visiteurs, nb_benevoles, nb_likes, nb_vues, nb_partages)
            VALUES (:v, :b, :l, :vu, :p)
        ");

        $stmt->bindValue(':v', $object->getNbVisiteurs(), PDO::PARAM_INT);
        $stmt->bindValue(':b', $object->getNbBenevoles(), PDO::PARAM_INT);
        $stmt->bindValue(':l', $object->getNbLikes(), PDO::PARAM_INT);
        $stmt->bindValue(':vu', $object->getNbVues(), PDO::PARAM_INT);
        $stmt->bindValue(':p', $object->getNbPartages(), PDO::PARAM_INT);

        $success = $stmt->execute();

        if ($success) {
            $object->setIdStatistique((int)$connexion->lastInsertId());
        }

        ConnexionBD::close();
        return $success;
    }

    /**
     * Met à jour une statistique existante
     */
    static public function update(object $object): bool {
        $connexion = ConnexionBD::getInstance();

        $stmt = $connexion->prepare("
            UPDATE Statistique SET 
                nb_visiteurs = :v,
                nb_benevoles = :b,
                nb_likes = :l,
                nb_vues = :vu,
                nb_partages = :p
            WHERE id_statistique = :id
        ");

        $stmt->bindValue(':id', $object->getIdStatistique(), PDO::PARAM_INT);
        $stmt->bindValue(':v', $object->getNbVisiteurs(), PDO::PARAM_INT);
        $stmt->bindValue(':b', $object->getNbBenevoles(), PDO::PARAM_INT);
        $stmt->bindValue(':l', $object->getNbLikes(), PDO::PARAM_INT);
        $stmt->bindValue(':vu', $object->getNbVues(), PDO::PARAM_INT);
        $stmt->bindValue(':p', $object->getNbPartages(), PDO::PARAM_INT);

        $success = $stmt->execute();
        ConnexionBD::close();
        return $success;
    }



?>