<?php
// *****************************************************************************************
// Description : Classe abstraite parente pour tous les contrôleurs spécifiques
// *****************************************************************************************

abstract class Controleur
{
    public function __construct()
	{}
	
    // Redirige vers une autre page
    protected function redirect(string $url): void {
        header("Location: $url");
        exit();
    }

    // Charge une vue et lui passe les données 
    protected function render(string $vue, array $donnees = []): void {
        extract($donnees);
        include(__DIR__ . "/../../vues/" . $vue); 
    }

    // Vérifie si un organisateur est connecté
    protected function isOrganisateurConnecte(): bool {
        return isset($_SESSION['organisateur']);
    }

    // Retourne l’organisateur connecté (ou null)
    protected function getOrganisateur() {
        return $_SESSION['organisateur'] ?? null;
    }

    // Affiche un message d'erreur générique voir si veux le faire
    protected function error(string $message): void {
        echo "<div style='color:red; font-weight:bold;'>Erreur : $message</div>";
    }

    abstract public function executerAction();
}
