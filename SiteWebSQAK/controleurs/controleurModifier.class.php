<?php
include_once("controleur.abstract.class.php");

class Modifier extends Controleur{

    
		public function __construct() {
			//appel du constructeur parent
			parent::__construct();
		}
		

		// ******************* Méthode exécuter action
		// implémenter la méthde executerAction
		// retournez la page d'accueil
		public function executerAction():string
		{
				

			return "modifier-evenement.php";
		}

}

?>