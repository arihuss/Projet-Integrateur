<?php
include_once("controleur.abstract.class.php");
include_once(".\modele\DAO\ParticipantDAO.class.php");

class ProfilParticipant extends Controleur{

    
	private $tabParticipants;

    
	public function __construct() {
		//appel du constructeur parent
		parent::__construct();
		$this->tabParticipants=array();
	}

	public function getTabEvents():array{
		return $this->tabParticipants;
	}
	
		// ******************* Méthode exécuter action
		// implémenter la méthde executerAction
		// retournez la page d'accueil
		public function executerAction():string
		{
			if (isset($_GET['id'])){
				$id = $_GET['id'];
				$unParticipant = ParticipantDAO::findById($id);
				if ($unParticipant != null){
					array_push($this->tabParticipants,$unParticipant);
				}
			}	
				

			return "profil-participant.php";
		}

}

?>