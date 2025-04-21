<?php
include_once($_SERVER['DOCUMENT_ROOT'] . "/controleurs/controleur.abstract.class.php");
include_once($_SERVER['DOCUMENT_ROOT'] . "/modele/DAO/OrganisateurDAO.class.php");

class ProfilOrganisateur extends Controleur{

    
	private $tabOrganisateurs;

    
	public function __construct() {
		parent::__construct();
		$this->tabOrganisateurs=array();
	}

	public function getTabEvents():array{
		return $this->tabOrganisateurs;
	}

		
		public function executerAction():string
		{
			if (!isset($_SESSION['user_id'])) {
				header("Location: index.php?action=accueil");
				exit;
			}

			return "profil-organisateur.php";
		}
	
		public function getOrganisateur(): ?Organisateur {
			return $this->organisateur ?? null;
		}
		
}






