<?php
include_once($_SERVER['DOCUMENT_ROOT'] . "/controleurs/controleur.abstract.class.php");
include_once($_SERVER['DOCUMENT_ROOT'] . "/modele/DAO/EvenementDAO.class.php");

class VoirUnEvent extends Controleur{

	private $tabEvents;

    
		public function __construct() {
			//appel du constructeur parent
			parent::__construct();
			$this->tabEvents=array();
		}

		public function getTabEvents():array{
			return $this->tabEvents;
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
				$id = $_GET['id'];
				$unEvent = EvenementDAO::findById($id);
				if ($unEvent != null){
					array_push($this->tabEvents,$unEvent);
				}
			}	

			return "evenement.php";
		}

}

?>