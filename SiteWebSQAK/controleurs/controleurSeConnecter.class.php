<?php
include_once("controleurs/controleur.abstract.class.php");
include_once("modele/DAO/OrganisateurDAO.class.php");

class SeConnecter extends Controleur{


		if (session_status() === PHP_SESSION_NONE) {
			session_start();
		}
    
		public function __construct() {
			//appel du constructeur parent
			parent::__construct();
		}
		
		// ******************* Méthode exécuter action
		// implémenter la méthde executerAction
		// retournez la page d'accueil
		public function executerAction():string
		{
				
			// Vérifie si l'organisateur est déjà connecté
			if ($this->isOrganisateurConnecte()) {
				array_push($this->messagesErreur, "Vous êtes déjà connecté.");
				return "page-principale.php";
			}
	
			// Traitement du POST
			if (isset($_POST['courriel']) && isset($_POST['mot_de_passe'])) {
				$organisateur = OrganisateurDAO::findByEmail($_POST['courriel']);
	
				if (!$organisateur || !password_verify($_POST['mot_de_passe'], $organisateur->getMotDePasse())) {
					array_push($this->messagesErreur, "Courriel ou mot de passe incorrect.");
					return "log-in.php"; 
				}
	
				// Connexion réussie
				$_SESSION['organisateur'] = $organisateur;
				return "page-principale.php";
			}
	

			return "log-in.php";
		}

}

?>