<?php
include_once($_SERVER['DOCUMENT_ROOT'] . "/controleurs/controleur.abstract.class.php");


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

            // Récupération des champs
            $prenom = $_POST['prenom'] ?? null;
            $nom = $_POST['nom'] ?? null;
            $nomOrganisateur = $_POST['organisation'] ?? null;
            $courriel = $_POST['courriel'] ?? null;
            $telephone = $_POST['telephone'] ?? "";
            $bio = ""; // à adapter selon ton besoin
            $mdp = $_POST['mot_de_passe'] ?? "";
            $cmdp = $_POST['confirmation'] ?? "";
            $nbEvents = 0;
           
            // =================== VALIDATIONS ===================
            if ($mdp !== $cmdp) {
                $this->messagesErreur[] = "Les mots de passe ne correspondent pas.";
                return "sign-up.php";
            }
            $mdp=password_hash($_POST['mot_de_passe'] ?? "", PASSWORD_BCRYPT);

            if (empty($courriel) || empty($mdp) || empty($cmdp)) {
                $this->messagesErreur[] = "Veuillez remplir tous les champs obligatoires.";
                return "sign-up.php";
            }


            if (empty($prenom) && empty($nomOrganisateur)) {
                $this->messagesErreur[] = "Veuillez entrer vos nom/prénom OU le nom d'organisation.";
                return "sign-up.php";
            }

            // =================== CRÉATION ORGANISATEUR ===================
            $organisateur = new Organisateur(
                null,
                null,
                $prenom,
                $nom,
                $courriel,
                $bio,
                $nomOrganisateur,
                $mdp, 
                $nbEvents,
                $telephone 
            );

            // =================== SAUVEGARDE ===================
            $success = OrganisateurDAO::save($organisateur);

            if ($success) {
                header("Location: index.php?action=seConnecter&message=Compte créé avec succès !");
                exit;
            } else {
                $this->messagesErreur[] = "Erreur lors de la création du compte.";
                return "sign-up.php";
            }
        }

        // Si GET ou première fois
        return "sign-up.php";
    }
}

