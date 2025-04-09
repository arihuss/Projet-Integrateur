<?php
include_once($_SERVER['DOCUMENT_ROOT'] . "/controleurs/controleur.abstract.class.php");

class ModifierProfil extends Controleur{

    
		public function __construct() {
			//appel du constructeur parent
			parent::__construct();
		}
		

		// ******************* Méthode exécuter action
		// implémenter la méthde executerAction
		// retournez la page d'accueil
		public function executerAction():string
		{
			if (!isset($_SESSION['user_id'])) {
				//Rediriger vers la page d'accueil si non connecté
			   header("Location: index.php?action=accueil");
			   exit;
			}
			
			return "modifier-profil.php";
		}

}

