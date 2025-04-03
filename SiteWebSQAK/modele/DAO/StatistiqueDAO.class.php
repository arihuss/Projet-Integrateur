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

?>