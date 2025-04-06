<?php
include_once("controleur.abstract.class.php");
include_once("modele/DAO/OrganisateurDAO.class.php");

class ProfilOrganisateur extends Controleur{

    
	private ?Organisateur $organisateur = null;

		public function __construct() {
			//appel du constructeur parent
			parent::__construct();
		}
		
		public function executerAction(): string {
			if (isset($_SESSION['organisateur'])) {
				$this->organisateur = $_SESSION['organisateur'];

			}
			return "profil-organisateur.php";
		}
	
		public function getOrganisateur(): ?Organisateur {
			return $this->organisateur;
		}
		
}

?>




