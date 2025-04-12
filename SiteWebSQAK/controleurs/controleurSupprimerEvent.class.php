<?php
include_once($_SERVER['DOCUMENT_ROOT'] . "/controleurs/controleur.abstract.class.php");
include_once('modele/DAO/EvenementDAO.class.php');
class SupprimerEvent extends Controleur{

    
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

