<?php
include_once($_SERVER['DOCUMENT_ROOT'] . "/controleurs/controleur.abstract.class.php");
include_once('modele/DAO/EvenementDAO.class.php');
class SupprimerEvent extends Controleur{

    
		public function __construct() {
			
			parent::__construct();
		}
		

		public function executerAction():string
		{
			if (!isset($_SESSION['user_id'])) {
				
			   header("Location: index.php?action=accueil");
			   exit;
			}

            if (isset($_GET['id'])){
                $event = EvenementDAO::findById($_GET['id']);
                $success = EvenementDAO::delete($event);
                if (!$success){
                    echo "une erreur s'est produite";
                    return "accueil.php";
                }
			}	

		
			return "page-principale.php";
		}

}

