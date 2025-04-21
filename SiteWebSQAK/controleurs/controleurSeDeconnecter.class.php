<?php
include_once($_SERVER['DOCUMENT_ROOT'] . "/controleurs/controleur.abstract.class.php");

class SeDeconnecter extends Controleur{

    
		public function __construct() {
			parent::__construct();
		}
		

		public function executerAction():string
		{
            session_unset();     
            session_destroy();   
    
           
            session_start();
            session_regenerate_id(true);
    
        
            return "accueil.php";
		}

}

