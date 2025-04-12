<?php
include_once(__DIR__ . '/../utilisateur.class.php');
include_once(__DIR__ . '/../DAO/connexionBD.class.php');

class UtilisateurDAO{

    /**
     * Cette méthode retourne l'utilisateur dont la clé primaire a été reçue en paramètre
     * @param int $id La clé primaire de l'objet à chercher
     * @return object|null L'objet trouvé ou null si non-trouvé
     */
    static public function findById(int $id): ?Utilisateur {
        try {
            $connexion = ConnexionBD::getInstance();
        } catch (Exception $e) {
            throw new Exception("Impossible d'obtenir la connexion à la BD");
        }

        $utilisateur = null;
        $requete = $connexion->prepare(
            "SELECT * 
             FROM Utilisateur 
             WHERE id_utilisateur = :id"
        );
        $requete->bindParam(':id',$id,PDO::PARAM_INT);
        $requete->execute();

        if ($requete->rowCount()!=0){
            $enr = $requete->fetch();
            $utilisateur = new Utilisateur(
                $enr['id_utilisateur'],
                $enr['img_utilisateur']??null,
                $enr['prenom'],
                $enr['nom'],
                $enr['courriel'],
                $enr['num_tel'],
                $enr['bio'],
                $enr['mot_de_passe']
            );
        }
        $requete->closeCursor();
        ConnexionBD::close();
        return $utilisateur;
    }
}?>