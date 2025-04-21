<?php
// ***************************
// routes.php pour SQAK
// ***************************

if (session_status() === PHP_SESSION_NONE) {
    session_start();
}


header("Access-Control-Allow-Origin: *");
header("Access-Control-Allow-Methods: GET, POST, PUT, DELETE, OPTIONS");
header("Access-Control-Allow-Headers: Content-Type, Authorization");

$action = $_GET['action'] ?? 'accueil'; 
echo "debuga";


$action = preg_replace('/[^a-zA-Z0-9_]/', '', $action);

include_once($_SERVER['DOCUMENT_ROOT'] . "/controleurs/controleurManufacture.class.php");

try {
    
    $controleur = ManufactureControleur::creerControleur($action);

    $vue = $controleur->executerAction();

    if (str_ends_with($vue, ".php") && file_exists($vue)) {
        include_once($vue);
    } else {
        echo $vue; 
    }

} catch (Exception $e) {
    echo "<h1>Erreur</h1><p>" . $e->getMessage() . "</p>";
}
