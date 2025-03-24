<?php
include_once(__DIR__ . "../organisateur.class.php");
include_once(__DIR__ . "/DAO.interface.php");

class OrganisateurDAO implements DAO{

    /**
     * Cette méthode retourne l'objet dont la clé primaire a été reçue en paramètre
     * @param int $id La clé primaire de l'objet à chercher
     * @return object|null L'objet trouvé ou null si non-trouvé
     */
    static public function findById(int $id): ?Organisateur {
        //a completer
    }

    /**
     * Retourne une liste de tous les objets de la table
     * @return array
     */
    static public function findAll():array{
        //a completer
        return [0];
    }

    /**
     * Creer un nouvel organisateur au sign-up
     * @param object $object 
     * @return bool true si successful
     */
    static public function save(object $object):bool{
       //a completer
        return false;
    }

    /**
     * Modifier organisateur dans la page modifier
     * @param object $object
     * @return bool true si successful
     */
    static public function update(object $object):bool{
        //a completer
        return false;
    }

    /**
     * Supprimer le compte de l'organisateur 
     * @param object $object
     * @return bool true si successful
     */
    static public function delete(object $object):bool{
        //a completer
        return false;
    }
}
?>