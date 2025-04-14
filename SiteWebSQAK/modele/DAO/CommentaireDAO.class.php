<?php
include_once(__DIR__ . "/../commentaire.class.php");
include_once(__DIR__ . "/DAO.interface.php");
include_once(__DIR__ . '/../DAO/connexionBD.class.php');

class CommentaireDAO{

    /**
     * Summary of findByEvenement Retourne tous les commentaires selon le id evenement
     * @param int $id_evenement le id de l'evenement
     * @return array les commentaires
     */
    static public function findByEvenement(int $id_evenement): array {
        try {
            $connexion = ConnexionBD::getInstance();
        } catch (Exception $e) {
            throw new Exception("Impossible d'obtenir la connexion à la BD");
        }
    
        $commentaires = [];
        $requete = $connexion->prepare(
            "SELECT C.*, U.img_utilisateur, U.prenom
             FROM Commentaire C
             JOIN Utilisateur U ON C.id_utilisateur = U.id_utilisateur
             WHERE C.id_evenement = :id_evenement
             ORDER BY C.date_envoi DESC"
        );
        $requete->bindParam(':id_evenement', $id_evenement, PDO::PARAM_INT);
        $requete->execute();
    
        foreach ($requete as $enr) {
            $commentaire = new Commentaire(
                $enr['id_commentaire'],
                $enr['id_utilisateur'],
                $enr['id_evenement'],
                $enr['message'],
                $enr['date_envoi']
            );
            // Ajout dynamique des info utilisateurs
            $commentaire->setImageUtilisateur($enr['img_utilisateur']);
            $commentaire->setPrenomUtilisateur($enr['prenom']);
            $commentaires[] = $commentaire;
        }
    
        $requete->closeCursor();
        ConnexionBD::close();
    
        return $commentaires;
    }
    
}
?>