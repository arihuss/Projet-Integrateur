<?php
include_once($_SERVER['DOCUMENT_ROOT'] . "/controleurs/controleur.abstract.class.php");
include_once($_SERVER['DOCUMENT_ROOT'] . "/modele/DAO/OrganisateurDAO.class.php");

class SeInscrire extends Controleur {

    private array $messagesErreur = [];

    public function __construct() {
        parent::__construct();
    }

    public function getMessagesErreur(): array {
        return $this->messagesErreur;
    }

    public function executerAction(): string {
        
        if ($_SERVER['REQUEST_METHOD'] === 'POST') {
           
            $prenom = $_POST['prenom'] ?? null;
            $nom = $_POST['nom'] ?? null;
            $nomOrganisateur = $_POST['organisation'] ?? null;
            $courriel = $_POST['courriel'] ?? null;
            $telephone = $_POST['telephone'] ?? "";
            $bio = "";
            $mdp = $_POST['mot_de_passe'] ?? "";
            $cmdp = $_POST['confirmation'] ?? "";
            $nbEvents = 0;

            if ($mdp !== $cmdp) {
                $this->messagesErreur[] = "Les mots de passe ne correspondent pas.";
                return "sign-up.php";
            }

            if (empty($courriel) || empty($mdp) || empty($cmdp)) {
                $this->messagesErreur[] = "Veuillez remplir tous les champs obligatoires.";
                return "sign-up.php";
            }

            if (empty($prenom) && empty($nomOrganisateur)) {
                $this->messagesErreur[] = "Veuillez entrer vos nom/prénom OU le nom d'organisation.";
                return "sign-up.php";
            }

           
            $existant = OrganisateurDAO::findByEmail($courriel);
            if ($existant !== null) {
                $this->messagesErreur[] = "Un compte avec ce courriel existe déjà.";
                return "sign-up.php";
            }

    
            $mdpHash = password_hash($mdp, PASSWORD_BCRYPT);

            $codeConfirmation = strval(rand(100000, 999999));

           
            $organisateur = new Organisateur(
                null,
                null,
                $prenom,
                $nom,
                $courriel,
                $bio,
                $nomOrganisateur,
                $mdpHash,
                $nbEvents,
                $telephone,
                false,            
                $codeConfirmation  
            );

            $success = OrganisateurDAO::save($organisateur);

            if ($success) {
                $_SESSION['user_id'] = $organisateur->getId();
                

            
                header("Location: index.php?action=seConnecter");
                exit;
            } else {
                $this->messagesErreur[] = "Erreur lors de la création du compte.";
                return "sign-up.php";
            }
        }

        return "sign-up.php";
    }
}
