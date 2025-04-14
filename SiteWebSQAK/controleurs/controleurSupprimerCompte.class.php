<?php
include_once($_SERVER['DOCUMENT_ROOT'] . "/controleurs/controleur.abstract.class.php");
include_once($_SERVER['DOCUMENT_ROOT'] . "/modele/DAO/OrganisateurDAO.class.php");

class SupprimerCompte extends Controleur {

    public function __construct() {
        parent::__construct();
    }

    public function executerAction(): string {

        if (isset($_SESSION['user_id'])) {
            $organisateur = OrganisateurDAO::findById($_SESSION['user_id']);
           
            if ($organisateur !== null) {
                
                $success = OrganisateurDAO::delete($organisateur);

                if ($success) {
                    session_destroy();
                    header("Location: index.php?action=accueil&message=Compte supprimé avec succès");
                    exit;
                } else {
                    return "settings.php";
                }
            }
        }

        return "settings.php";
    }
}
