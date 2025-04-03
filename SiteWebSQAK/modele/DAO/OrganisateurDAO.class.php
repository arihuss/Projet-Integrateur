<?php
include_once(__DIR__ . "../organisateur.class.php");
include_once(__DIR__ . "/DAO.interface.php");

class OrganisateurDAO implements DAO{

    /**
     * Cette méthode retourne l'organisateur dont la clé primaire a été reçue en paramètre
     * @param int $id La clé primaire de l'objet à chercher
     * @return object|null L'objet trouvé ou null si non-trouvé
     */
    static public function findById(int $id): ?Organisateur {
        try {
            $connexion = ConnexionBD::getInstance();
        } catch (Exception $e) {
            throw new Exception("Impossible d'obtenir la connexion à la BD");
        }

        $organisateur = null;
        $requete = $connexion->prepare(
            "SELECT * 
             FROM Organisateur 
             WHERE id_organisateur = :id"
        );
        $requete->bindParam(':id',$id,PDO::PARAM_INT);
        $requete->execute();

        if ($requete->rowCount()!=0){
            $enr = $requete->fetch();
            $organisateur = new Organisateur(
                $enr['id_organisateur'],
                $enr['prenom'],
                $enr['nom'],
                $enr['courriel'],
                $enr['bio'],
                $enr['nom_organisateur'],
                $enr['mot_de_passe'],
                $enr['nb_events'],
            );
        }
        $requete->closeCursor();
        ConnexionBD::close();
        return $organisateur;

    }

    /**
     * Retourne une liste de tous les objets de la table
     * PAS SUPPOSE MARCHER POUR ORGANISATEUR
     * @return array
     */
    static public function findAll():array{
        //a completer
        return [0];
    }

    /**
     * Creer un nouvel organisateur au sign-up
     * @param object $organisateur
     * @return bool true si successful
     */
    static public function save(object $organisateur):bool{
        try {
            $connexion = ConnexionBD::getInstance();
        } catch (Exception $e) {
            throw new Exception("Impossible d'obtenir la connexion à la BD");
        }
        
        //Stockage de variables intermediaires
        $prenom = $organisateur->getPrenom();
        $nom = $organisateur->getNom();
        $courriel = $organisateur->getCourriel();
        $bio = $organisateur->getBiographie();
        $nomOrganisateur = $organisateur->getNomOrganisateur();
        $mdp = $organisateur->getMotDePasse();
        $nbEvents = $organisateur->getNbEvents();

        $mdp = password_hash($mdp,PASSWORD_BCRYPT);

        $requete = $connexion->prepare(
            "INSERT INTO Organisateur (prenom, nom, courriel, bio, nom_organisateur, mot_de_passe, nb_events)
            VALUES (:prenom, :nom, :courriel, :bio, :nomOrganisateur, :mdp, :nbEvents)"
        );

        //Liaison des parametres
        $requete->bindParam(':prenom',$prenom,PDO::PARAM_STR);
        $requete->bindParam(':nom',$nom,PDO::PARAM_STR);
        $requete->bindParam(':courriel',$courriel,PDO::PARAM_STR);
        $requete->bindParam(':bio',$bio,PDO::PARAM_STR);
        $requete->bindParam(':nomOrganisateur',$nomOrganisateur,PDO::PARAM_STR);
        $requete->bindParam(':mdp',$mdp,PDO::PARAM_STR);
        $requete->bindParam(':nbevents',$nbEvents,PDO::PARAM_STR);

        $success = $requete->execute();
        if ($success){
            $organisateur->setId((int)$connexion->lastInsertId()); //not sure if this part is needed
        }
        return $success;
    }

    /**
     * Modifier organisateur dans la page modifier
     * @param object $object
     * @return bool true si successful
     */
    static public function update(object $organisateur):bool{
        try {
            $connexion = ConnexionBD::getInstance();
        } catch (Exception $e) {
            throw new Exception("Impossible d'obtenir la connexion à la BD");
        }

        //Stockage de variables intermediaires
        $id = $organisateur->getId();
        $prenom = $organisateur->getPrenom();
        $nom = $organisateur->getNom();
        $courriel = $organisateur->getCourriel();
        $bio = $organisateur->getBiographie();
        $nomOrganisateur = $organisateur->getNomOrganisateur();
        $mdp = $organisateur->getMotDePasse();
        $nbEvents = $organisateur->getNbEvents();

        $mdp = password_hash($mdp,PASSWORD_BCRYPT);

        $requete = $connexion->prepare(
            "UPDATE Organisateur
             SET prenom = :prenom, nom = :nom, courriel = :courriel, 
                 bio = :bio, nom_organisateur = :nomOrganisateur, mot_de_passe = :mdp, nb_events = :nbEvents
             WHERE id_organisateur= :id"
        );

        $requete->bindParam(':id', $id, PDO::PARAM_INT);
        $requete->bindParam(':prenom',$prenom,PDO::PARAM_STR);
        $requete->bindParam(':nom',$nom,PDO::PARAM_STR);
        $requete->bindParam(':courriel',$courriel,PDO::PARAM_STR);
        $requete->bindParam(':bio',$bio,PDO::PARAM_STR);
        $requete->bindParam(':nomOrganisateur',$nomOrganisateur,PDO::PARAM_STR);
        $requete->bindParam(':mdp',$mdp,PDO::PARAM_STR);
        $requete->bindParam(':nbevents',$nbEvents,PDO::PARAM_STR);

        return $requete->execute();
    }

    /**
     * Supprimer le compte de l'organisateur 
     * @param object $object
     * @return bool true si successful
     */
    static public function delete(object $organisateur):bool{
        try {
            $connexion = ConnexionBD::getInstance();
        } catch (Exception $e) {
            throw new Exception("Impossible d'obtenir la connexion à la BD");
        }

        $id = $organisateur->getId();

        $requete = $connexion->prepare("DELETE FROM Organisateur WHERE id_organisateur = :id");

        // Liaison du paramètre
        $requete->bindParam(':id', $id, PDO::PARAM_INT);

        return $requete->execute();
    }
}
?>