<?php
// ***************************
// routes.php pour SQAK
// ***************************

if (session_status() === PHP_SESSION_NONE) {
    session_start();
}

// Headers pour les API REST (si besoin futur d'API JSON)
header("Access-Control-Allow-Origin: *");
header("Access-Control-Allow-Methods: GET, POST, PUT, DELETE, OPTIONS");
header("Access-Control-Allow-Headers: Content-Type, Authorization");

// Action par défaut
$action = $_GET['action'] ?? 'accueil'; // ou "accueil" si tu préfères

// Nettoyage du nom d'action
$action = preg_replace('/[^a-zA-Z0-9_]/', '', $action);

// Inclure la manufacture
include_once("controleurs/controleurManufacture.class.php");

try {
    // Créer le bon contrôleur via la manufacture
    $controleur = ManufactureControleur::creerControleur($action);

    // Exécuter l’action
    $vue = $controleur->executerAction();

    // Inclure la vue si elle existe
    if (str_ends_with($vue, ".php") && file_exists($vue)) {
        include($vue);
    } else {
        echo $vue; // Au cas où la vue retourne du texte directement
    }

} catch (Exception $e) {
    echo "<h1>Erreur</h1><p>" . $e->getMessage() . "</p>";
}
