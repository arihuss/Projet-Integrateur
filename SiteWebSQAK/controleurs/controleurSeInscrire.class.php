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
            // Récupération des champs
            $prenom = $_POST['prenom'] ?? null;
            $nom = $_POST['nom'] ?? null;
            $nomOrganisateur = $_POST['organisation'] ?? null;
            $courriel = $_POST['courriel'] ?? null;
            $telephone = $_POST['telephone'] ?? "";
            $bio = "";
            $mdp = $_POST['mot_de_passe'] ?? "";
            $cmdp = $_POST['confirmation'] ?? "";
            $nbEvents = 0;

            // =================== VALIDATIONS ===================
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

            // ✅ Vérifier si le courriel existe déjà
            $existant = OrganisateurDAO::findByEmail($courriel);
            if ($existant !== null) {
                $this->messagesErreur[] = "Un compte avec ce courriel existe déjà.";
                return "sign-up.php";
            }

            // Hash du mot de passe
            $mdpHash = password_hash($mdp, PASSWORD_BCRYPT);

            // Génération du code de confirmation
            $codeConfirmation = strval(rand(100000, 999999));

            // =================== CRÉATION ORGANISATEUR ===================
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
                false,             // est_confirme
                $codeConfirmation  // code_confirmation
            );

            $success = OrganisateurDAO::save($organisateur);

            if ($success) {
                // Envoi du code de confirmation par courriel
                mail(
                    $courriel,
                    "Confirmation de votre compte",
                    "Bonjour,\n\nVoici votre code de confirmation : $codeConfirmation\n\nL’équipe SQAK"
                );

                // Stocker le courriel en session pour la validation
                $_SESSION['courriel_a_confirmer'] = $courriel;

                // Rediriger vers la page de confirmation
                header("Location: index.php?action=confirmation");
                exit;
            } else {
                $this->messagesErreur[] = "Erreur lors de la création du compte.";
                return "sign-up.php";
            }
        }

        return "sign-up.php";
    }
}
