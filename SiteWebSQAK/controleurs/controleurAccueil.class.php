<?php
include_once($_SERVER['DOCUMENT_ROOT'] . "/controleurs/controleur.abstract.class.php");

class Accueil extends Controleur{

    
		public function __construct() {
			parent::__construct();
		}
		

	
		public function executerAction():string
		{
				
			return "accueil.php";
		}

}


