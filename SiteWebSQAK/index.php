<?php

	if (session_status() === PHP_SESSION_NONE) {
		session_start();
	}
	include_once($_SERVER['DOCUMENT_ROOT'] . "/controleurs/controleurManufacture.class.php");
	
	
	if(!ISSET($_GET['action'])){
         $action="accueil";

	}else{
		
		$action = $_GET['action'];

	}
   
	


$controleur = ManufactureControleur::creerControleur($action);

	
	

   $nomVue = $controleur->executerAction();
	
	include_once($nomVue);

?>