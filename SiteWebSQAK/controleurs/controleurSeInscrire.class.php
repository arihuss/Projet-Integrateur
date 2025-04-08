<?php
include_once("controleur.abstract.class.php");

class SeInscrire extends Controleur{

    
		public function __construct() {
			//appel du constructeur parent
			parent::__construct();
		}
		

		// ******************* Méthode exécuter action
		// implémenter la méthde executerAction
		// retournez la page d'accueil
		private array $messagesErreur = [];

   
    	public function getMessagesErreur(): array {
       		 return $this->messagesErreur;
    	}

    public function executerAction(): string {
        if ($_SERVER['REQUEST_METHOD'] === 'POST') {

            // Récupération des champs
            $prenom = $_POST['prenom'] ?? null;
            $nom = $_POST['nom'] ?? null;
            $nomOrganisateur = $_POST['organisation'] ?? null;
            $courriel = $_POST['courriel'] ?? null;
            $telephone = $_POST['telephone'] ?? "";
            $bio = ""; // tu peux personnaliser selon ton projet
            $mdp = $_POST['mot_de_passe'] ?? "";
            $cmdp = $_POST['confirmation'] ?? "";
            $nbEvents = 0;

            // Validation de base
            if (empty($courriel) || empty($mdp) || empty($cmdp)) {
                $this->messagesErreur[] = "Veuillez remplir tous les champs obligatoires.";
                return "sign-up.php";
            }

            if ($mdp !== $cmdp) {
                $this->messagesErreur[] = "Les mots de passe ne correspondent pas.";
                return "sign-up.php";
            }

            // Vérifie qu'au moins prénom/nom OU organisation est fourni
            if (empty($prenom) && empty($nomOrganisateur)) {
                $this->messagesErreur[] = "Veuillez entrer vos nom/prénom OU le nom d'organisation.";
                return "sign-up.php";
            }

            // Création de l'objet organisateur
            $organisateur = new Organisateur(
                null,
                $prenom,
                $nom,
                $courriel,
                $bio,
                $nomOrganisateur,
                $mdp, // il sera haché dans le DAO
                $nbEvents,
				//$telephone
            );

		
            // Sauvegarde
            $success = OrganisateurDAO::save($organisateur);
			
            if ($success) {
                header("Location: index.php?action=seConnecter&message=Compte créé avec succès !");
                exit;
            } else {
                $this->messagesErreur[] = "Erreur lors de la création du compte.";
            }
			if ($success) {
				echo "Insertion réussie !";
				exit;
			}
        }

        return "sign-up.php";
    }

}

?>