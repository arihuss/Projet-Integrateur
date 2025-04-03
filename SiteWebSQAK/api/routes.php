<?php
// ***************************
// routes.php pour SQAK
// ***************************

session_start();

// Headers pour les API REST (si besoin futur d'API JSON)
header("Access-Control-Allow-Origin: *");
header("Access-Control-Allow-Methods: GET, POST, PUT, DELETE, OPTIONS");
header("Access-Control-Allow-Headers: Content-Type, Authorization");

// Action par défaut
$action = $_GET['action'] ?? 'accueil';

// Nettoyage du nom d'action pour éviter les injections
$action = preg_replace('/[^a-zA-Z0-9_]/', '', $action);

// Construction du nom de la classe contrôleur
$classeControleur = ucfirst($action);
$fichierControleur = __DIR__ . "/controleurs/" . $classeControleur . ".class.php";

if (file_exists($fichierControleur)) {
    include_once($fichierControleur);
    $controleur = new $classeControleur();
    
    try {
        $page = $controleur->executerAction();

        
        if (str_ends_with($page, ".php") && file_exists($page)) {
            include($page);
        } else {
            echo $page;
        }

    } catch (Exception $e) {
        echo "<h1>Erreur</h1><p>" . $e->getMessage() . "</p>";
    }
} else {
    // Page 404 personnalisée
    include_once("erreur404.php");
}
