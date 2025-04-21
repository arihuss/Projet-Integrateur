<?php
include_once($_SERVER['DOCUMENT_ROOT'] . "/controleurs/controleur.abstract.class.php");

class Settings extends Controleur{

    
		public function __construct() {
		
			parent::__construct();
		}
		

		
		public function executerAction():string
		{
			if (!isset($_SESSION['user_id'])) {
				
			   header("Location: index.php?action=accueil");
			   exit;
			}
		
			return "settings.php";
		}

}

