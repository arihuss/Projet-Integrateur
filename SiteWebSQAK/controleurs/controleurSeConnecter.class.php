<?php
include_once($_SERVER['DOCUMENT_ROOT'] . "/controleurs/controleur.abstract.class.php");
include_once($_SERVER['DOCUMENT_ROOT'] . "/modele/DAO/OrganisateurDAO.class.php");

class SeConnecter extends Controleur{


	private array $messagesErreur = [];
		
    
		public function __construct() {
			parent::__construct();

		}
		
		
		public function executerAction():string
		{
				
			
			if ($this->isOrganisateurConnecte()) {
				array_push($this->messagesErreur, "Vous êtes déjà connecté.");
				return "page-principale.php";
			}
	
			
			if (isset($_POST['courriel']) && isset($_POST['mot_de_passe'])) {
				$organisateur = OrganisateurDAO::findByEmail($_POST['courriel']);
	
				if (!$organisateur || !password_verify($_POST['mot_de_passe'], $organisateur->getMotDePasse())) {
					array_push($this->messagesErreur, "Courriel ou mot de passe incorrect.");
					return "log-in.php"; 
				}
	
				
				$_SESSION ['user_id'] = $organisateur->getId();
				$_SESSION['organisateur'] = $organisateur;
				if (!$organisateur->getEstConfirme()){
					header(header("Location: index.php?action=confirmation"));
				}
				return "page-principale.php";
			}
	

			return "log-in.php";
		}

		public function getMessagesErreur(): array {
			return $this->messagesErreur;
		}
}

