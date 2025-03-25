<?php
include_once(__DIR__ . "../evenement.class.php");
include_once(__DIR__ . "/DAO.interface.php");

class EvenementDAO {
     /**
     * Cette méthode retourne l'objet dont la clé primaire a été reçue en paramètre
     * @param int $id La clé primaire de l'objet à chercher
     * @return object|null L'objet trouvé ou null si non-trouvé
     */
    static public function findById(int $id): ?Evenement {
        //a completer
    }

    /**
     * Retourne une liste de tous les objets de la table selon l'organisateur associe
     * @return array
     */
    static public function findAll():array{
        //a completer
        return [0];
    }

    /**
     * Creer un nouvel evenement
     * @param object $object 
     * @return bool true si successful
     */
    static public function save(object $object):bool{
       //a completer
        return false;
    }

    /**
     * Modifier un evenement
     * @param object $object
     * @return bool true si successful
     */
    static public function update(object $object):bool{
        //a completer
        return false;
    }

    /**
     * Supprimer un evenement
     * @param object $object
     * @return bool true si successful
     */
    static public function delete(object $object):bool{
        //a completer
        return false;
    }


}
?>