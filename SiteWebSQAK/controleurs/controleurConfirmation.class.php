<?php
include_once($_SERVER['DOCUMENT_ROOT'] . "/controleurs/controleur.abstract.class.php");

class Confirmation extends Controleur {

    private array $messagesErreur = [];

    public function __construct() {
        parent::__construct();
    }

    public function getMessagesErreur(): array {
        return $this->messagesErreur;
    }

	
    public function executerAction(): string {

        $courriel = $_SESSION['courriel_a_confirmer'] ?? null;

        if (!$courriel) {
            header("Location: index.php?action=accueil");
            exit;
        }

        if ($_SERVER['REQUEST_METHOD'] === 'POST') {
            $codeEntre = $_POST['code'] ?? '';

            // Récupérer l'organisateur avec le courriel
            $organisateur = OrganisateurDAO::findByEmail($courriel);

            if ($organisateur && $organisateur->getCodeConfirmation() === $codeEntre) {
                // Mettre à jour dans la BD : est_confirme = 1, code_confirmation = NULL
                $connexion = ConnexionBD::getInstance();
                $requete = $connexion->prepare("
                    UPDATE Organisateur 
                    SET est_confirme = 1, code_confirmation = NULL 
                    WHERE courriel = :courriel
                ");
                $requete->bindValue(':courriel', $courriel);
                $requete->execute();

                // Nettoyer la session
                unset($_SESSION['courriel_a_confirmer']);

                // Redirection vers la connexion avec message
                header("Location: index.php?action=seConnecter&message=Compte confirmé avec succès !");
                exit;
            } else {
                $this->messagesErreur[] = "Code incorrect. Veuillez réessayer.";
            }
        }

        return "confirmation.php";
    }
}

