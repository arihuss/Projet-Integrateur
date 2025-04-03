<?php
include_once(__DIR__ . "../commentaire.class.php");
include_once(__DIR__ . "/DAO.interface.php");

class CommentaireDAO{

    /**
     * Summary of findByEvenement Retourne tous les commentaires selon le id evenement
     * @param int $id_evenement le id de l'evenement
     * @return array les commentaires
     */
    static public function findByEvenement(int $id_evenement):array{
        try {
            $connexion = ConnexionBD::getInstance();
        } catch (Exception $e) {
            throw new Exception("Impossible d'obtenir la connexion à la BD");
        }

        $commentaires = [];
        $requete = $connexion->prepare(
            "SELECT C.*
             FROM Commentaire C
             JOIN Evenement E ON C.id_evenement = E.id_evenement"
        );
        $requete->bindParam(':id_evenement', $id_evenement, PDO::PARAM_STR);
        $requete->execute();

        foreach($requete as $enr){
            $commentaires[] = new Commentaire(
                $enr['id_commentaire'],
                $enr['id_utilisateur'],
                $enr['id_evenement'],
                $enr['message'],
                $enr['date_envoi'],
            );
        }

        $requete->closeCursor();
        ConnexionBD::close();

        return $commentaires;
    
     } 
}
?>