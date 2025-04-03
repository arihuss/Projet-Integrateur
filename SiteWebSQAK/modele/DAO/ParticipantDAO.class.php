<?php
include_once(__DIR__ . "../participant.class.php");
include_once(__DIR__ . "/DAO.interface.php");

class ParticipantDAO{
    /*PARTICIPANT EST L'EQUIVALENT DE LA TABLE INSCRITPION*/

    /**
     * Cette méthode retourne le participant dont la clé primaire a été reçue en paramètre
     * @param int $id La clé primaire de l'objet à chercher
     * @return object|null L'objet trouvé ou null si non-trouvé
     */
    static public function findById(int $id): ?Participant {
        //a completer
    }

    /**
     * Summary of findByRole trouver la liste de participant par leur role (benevole, visiteur, appliquants)
     * @param string $role
     * @return array
     */
    static public function findByRole(string $role): array{
        //a completer
        return [0];
    }

    /**
     * Summary of accepterAppliquant change le role du partipant a benevole
     * @param object $object
     * @return bool true si successful
     */
    static public function accepterAppliquant(object $object):bool{
        //a completer
        return false;
    }

    /**
     * Summary of refuserApplicant enlever le participant de la liste des appliquants (je crois en supprimant son inscription)
     * @param object $object
     * @return bool true si successful
     */
    static public function refuserApplicant(object $object):bool{
        //a completer
        return false;
    }

   
}
?>