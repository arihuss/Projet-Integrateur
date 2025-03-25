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
        //a completer
    }

}

?>