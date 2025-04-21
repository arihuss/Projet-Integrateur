<?php
// *****************************************************************************************
// Description : Classe abstraite parente pour tous les contrôleurs spécifiques
// *****************************************************************************************

abstract class Controleur
{
    public function __construct()
	{}
	

    protected function redirect(string $url): void {
        header("Location: $url");
        exit();
    }

   
    protected function render(string $vue, array $donnees = []): void {
        extract($donnees);
        include(__DIR__ . "/../../vues/" . $vue); 
    }

    protected function isOrganisateurConnecte(): bool {
        return isset($_SESSION['organisateur']);
    }

   
    protected function getOrganisateur() {
        return $_SESSION['organisateur'] ?? null;
    }

    
    protected function error(string $message): void {
        echo "<div style='color:red; font-weight:bold;'>Erreur : $message</div>";
    }

    abstract public function executerAction();
}
