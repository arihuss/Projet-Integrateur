<?php
include_once("controleur.abstract.class.php");
include_once("modele/DAO/OrganisateurDAO.class.php");

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
			}	//refaire ca quand seConnecter marche

			return "profil-organisateur.php";
		}
		
		
}

?>




