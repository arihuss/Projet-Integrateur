<?php
    // *****************************************************************************************
	// Description : Contrôleur frontal du site
    // *****************************************************************************************
	//Le contrôleur frontal reçoit la requête avec un paramètre lui indiquant l’action à accomplir. 	
	//Inclusion de la manufacture de controleur (qui importe déjà tous les contrôleur)
	include_once "controleurs\controleurManufacture.class.php";
	
	//Obtenir le bon controleur
	//Si la requête contenant le paramètre action n'est pas renseigne
	if(!ISSET($_GET['action'])){
			// on reste à la page d'accueil
         $action="";

	}else{
		// Sinon on recupere l’action indiqué à accomplir
		$action = $_GET['action'];

	}
   
	

//Creer une instance du contrôleur adapté 
$controleur = ManufactureControleur::creerControleur($action);
//qui contient maintenant des données qui peuvent être utilisées par la vue.
	
	
	// Executer l'action et obtenir le nom de la vue
   $nomVue = $controleur->executerAction();
	
	// inclure la bonne vue
	include_once($nomVue)

?>