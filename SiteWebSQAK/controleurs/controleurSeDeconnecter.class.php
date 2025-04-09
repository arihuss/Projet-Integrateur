<?php
include_once($_SERVER['DOCUMENT_ROOT'] . "/controleurs/controleur.abstract.class.php");

class SeDeconnecter extends Controleur{

    
		public function __construct() {
			//appel du constructeur parent
			parent::__construct();
		}
		

		// ******************* Méthode exécuter action
		// implémenter la méthde executerAction
		// retournez la page d'accueil
		public function executerAction():string
		{
            session_unset();     // Supprime toutes les variables de session
            session_destroy();   // Détruit la session elle-même
    
            // Optionnel : réinitialiser la session (pour en démarrer une nouvelle propre si besoin)
            session_start();
            session_regenerate_id(true);
    
            // Rediriger vers la page de connexion
            return "accueil.php";
		}

}

