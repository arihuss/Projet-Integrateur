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
        $connexion = ConnexionBD::getInstance();

        $sql = "SELECT * FROM Inscription WHERE id_inscription = :id";
        $stmt = $connexion->prepare($sql);
        $stmt->bindParam(':id', $id, PDO::PARAM_INT);
        $stmt->execute();

        if ($stmt->rowCount() === 0) {
            return null;
        }

        $enr = $stmt->fetch();
        $participant = new Participant(
            $enr['id_inscription'],
            $enr['id_utilisateur'],
            $enr['id_evenement'],
            $enr['role'],
            $enr['date_inscription'],
            $enr['date_annulation']
        );

        $stmt->closeCursor();
        ConnexionBD::close();

        return $participant;
    }

    /**
     * Summary of findByRole trouver la liste de participant par leur role (benevole, visiteur, appliquants)
     * @param string $role
     * @return array
     */
    static public function findByRole(string $role): array{
        $connexion = ConnexionBD::getInstance();

        $sql = "SELECT * FROM Inscription WHERE role = :role";
        $stmt = $connexion->prepare($sql);
        $stmt->bindParam(':role', $role, PDO::PARAM_STR);
        $stmt->execute();

        $participants = [];
        while ($enr = $stmt->fetch()) {
            $participants[] = new Participant(
                $enr['id_inscription'],
                $enr['id_utilisateur'],
                $enr['id_evenement'],
                $enr['role'],
                $enr['date_inscription'],
                $enr['date_annulation']
            );
        }

        $stmt->closeCursor();
        ConnexionBD::close();

        return $participants;
        
        //return [0]; a voirrrr 
    }

    /**
     * Summary of accepterAppliquant change le role du partipant a benevole
     * @param object $object
     * @return bool true si successful
     */
    static public function accepterAppliquant(object $object):bool{
        $connexion = ConnexionBD::getInstance();

        $sql = "UPDATE Inscription SET role = 'benevole' WHERE id_inscription = :id";
        $stmt = $connexion->prepare($sql);
        $id = $object->getIdInscription();
        $stmt->bindParam(':id', $id, PDO::PARAM_INT);

        return $stmt->execute();
        //return false;
    }

    /**
     * Summary of refuserApplicant enlever le participant de la liste des appliquants (je crois en supprimant son inscription)
     * @param object $object
     * @return bool true si successful
     */
    static public function refuserApplicant(object $object):bool{
        $connexion = ConnexionBD::getInstance();

        $sql = "DELETE FROM Inscription WHERE id_inscription = :id";
        $stmt = $connexion->prepare($sql);
        $id = $object->getIdInscription();
        $stmt->bindParam(':id', $id, PDO::PARAM_INT);

        return $stmt->execute();
        //return false;
    }

   
}
?>