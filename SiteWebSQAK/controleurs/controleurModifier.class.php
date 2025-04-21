<?php
include_once($_SERVER['DOCUMENT_ROOT'] . "/controleurs/controleur.abstract.class.php");

class Modifier extends Controleur{

    
	private $tabEvents;

    
	public function __construct() {
		parent::__construct();
		$this->tabEvents=array();
	}

	public function getTabEvents():array{
		return $this->tabEvents;
	}
	
		
		public function executerAction():string
		{
			if (!isset($_SESSION['user_id'])) {
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

			return "modifier-evenement.php";
		}

}

