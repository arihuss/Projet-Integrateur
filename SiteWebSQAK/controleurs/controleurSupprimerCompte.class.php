<?php
include_once($_SERVER['DOCUMENT_ROOT'] . "/controleurs/controleur.abstract.class.php");

class SupprimerCompte extends Controleur{

    
		public function __construct() {
			//appel du constructeur parent
			parent::__construct();
		}
		

		// ******************* Méthode exécuter action
		// implémenter la méthde executerAction
		// retournez la page d'accueil
		
        public function executerAction(): string {
            
    
            if (isset($_SESSION['organisateur'])) {
              

                $organisateur = OrganisateurDAO::findById($SESSION['user_id']);
    
                if ($organisateur !== null) {
                    $success = OrganisateurDAO::delete($organisateur);
    
                    if ($success) {
                        session_destroy();
                        return "accueil.php";
                        exit;
                        
                    } else {
                        return "settings.php";
                    }
                } else {
                    return "settings.php";
                }
            }
    
            return "login.php";
        }

}

