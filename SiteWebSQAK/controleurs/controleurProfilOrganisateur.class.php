<?php
include_once($_SERVER['DOCUMENT_ROOT'] . "/controleurs/controleur.abstract.class.php");
include_once($_SERVER['DOCUMENT_ROOT'] . "/modele/DAO/OrganisateurDAO.class.php");

class ProfilOrganisateur extends Controleur{

    
	private $tabOrganisateurs;

    
	public function __construct() {
		//appel du constructeur parent
		parent::__construct();
		$this->tabOrganisateurs=array();
	}

	public function getTabEvents():array{
		return $this->tabOrganisateurs;
	}

		// ******************* Méthode exécuter action
		// implémenter la méthde executerAction
		// retournez la page d'accueil
		public function executerAction():string
		{
			if (isset($_GET['id'])){
				$id = $_GET['id'];
				$unOrganisateur = OrganisateurDAO::findById($id);
				if ($unOrganisateur != null){
					array_push($this->tabOrganisateurs,$unOrganisateur);
				}
			}	

			return "profil-organisateur.php";
		}
	
		public function getOrganisateur(): ?Organisateur {
			return $this->organisateur;
		}
		
}

?>




