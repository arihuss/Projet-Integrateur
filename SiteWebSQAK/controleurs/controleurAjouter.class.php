<?php
include_once("controleur.abstract.class.php");
include_once("modele/DAO/EvenementDAO.class.php");
class Ajouter extends Controleur{

	private array $messagesErreur = [];
    
		public function __construct() {
			//appel du constructeur parent
			parent::__construct();
		}

		public function getMessagesErreur(): array {
			return $this->messagesErreur;
		}
		
		

		// ******************* Méthode exécuter action
		// implémenter la méthde executerAction
		// retournez la page d'accueil
		public function executerAction():string
		{
			if ($_SERVER['REQUEST_METHOD'] === 'POST') {
				// Récupération des données du formulaire
				$titre = $_POST['titre'] ?? null;
				$description = $_POST['description'] ?? null;
				$categorie = $_POST['categorie'] ?? null;
				$lieu = $_POST['lieu'] ?? null;
				$dateDebut = $_POST['date-debut'] ?? null;
				$dateFin = $_POST['date-fin'] ?? null;
				$heureDebut = $_POST['heure-debut'] ?? null;
				$heureFin = $_POST['heure-fin'] ?? null;
				$nbBenevolesMax = $_POST['benevoles-max'] ?? 0;
				$nbInvitesMax = $_POST['invites-max'] ?? 0;
				
				// Validation des champs obligatoires
				if (empty($titre) || empty($description) || empty($categorie) || empty($lieu) || 
					empty($dateDebut) || empty($dateFin) || empty($heureDebut) || empty($heureFin)) {
					$this->messagesErreur[] = "Veuillez remplir tous les champs obligatoires.";
					return "ajouter-evenement.ph.php";
				}
				
				// Vérification de la cohérence des dates
				if (strtotime($dateDebut) > strtotime($dateFin)) {
					$this->messagesErreur[] = "La date de début ne peut pas être postérieure à la date de fin.";
					return "ajouter-evenement.ph.php";
				}
				
				// Si les dates sont identiques, vérifier la cohérence des heures
				if ($dateDebut === $dateFin && strtotime($heureDebut) >= strtotime($heureFin)) {
					$this->messagesErreur[] = "L'heure de début doit être antérieure à l'heure de fin pour un même jour.";
					return "ajouter-evenement.ph.php";
				}
				
				// Vérification que les nombres sont positifs
				if ($nbBenevolesMax < 0 || $nbInvitesMax < 0) {
					$this->messagesErreur[] = "Le nombre de bénévoles et d'invités doit être positif.";
					return "ajouter-evenement.php";
				}
				
				// Traitement de l'image
				$photo = null;
				if (isset($_FILES['photo']) && $_FILES['photo']['error'] === UPLOAD_ERR_OK) {
					$allowedTypes = ['image/jpeg', 'image/png', 'image/gif'];
					$fileType = $_FILES['photo']['type'];
					
					if (!in_array($fileType, $allowedTypes)) {
						$this->messagesErreur[] = "Le fichier doit être une image (JPG, PNG ou GIF).";
						return "ajouter-evenement.php";
					}
					
					// Génération d'un nom unique pour l'image
					$targetDir = "uploads/events/";
					$fileName = uniqid() . '_' . basename($_FILES['photo']['name']);
					$targetFile = $targetDir . $fileName;
					
					// Création du répertoire si nécessaire
					if (!file_exists($targetDir)) {
						mkdir($targetDir, 0777, true);
					}
					
					// Déplacement du fichier
					if (move_uploaded_file($_FILES['photo']['tmp_name'], $targetFile)) {
						$photo = $fileName;
					} else {
						$this->messagesErreur[] = "Erreur lors du téléchargement de l'image.";
						return "ajouter-evenement.php";
					}
				} else {
					$this->messagesErreur[] = "Veuillez sélectionner une image pour l'événement.";
					return "ajouter-evenement.php";
				}
				
				// Concaténation de la date et de l'heure pour le début et la fin
				$dateTimeDebut = $dateDebut . ' ' . $heureDebut . ':00';
				$dateTimeFin = $dateFin . ' ' . $heureFin . ':00';
				
				// Récupération de l'ID de l'organisateur à partir de la session
				$idOrganisateur = $_SESSION['user-id'] ?? null;
				
				/*
				if (!$idOrganisateur) {
					$this->messagesErreur[] = "Vous devez être connecté pour créer un événement.";
					return "log-in.php";
				}*/
				
				// Paramètres par défaut pour un nouvel événement
				$idStats = null; // Sera généré par la base de données ou initialisé plus tard
				$etatBenevole = true; // Activer le recrutement de bénévoles par défaut
				$etat = 'actif'; // L'événement est actif par défaut
				$nbInscriptions = 0;
				$nbBenevolesAcceptes = 0;
				$completBenevole = false;
				$completVisiteur = false;
				
				// Création de l'objet Evenement
				$evenement = new Evenement(
					9, // ID sera généré par la base de données
					1,
					1,
					$titre,
					$lieu,
					$dateTimeDebut,
					$dateTimeFin,
					$nbBenevolesMax,
					$nbInvitesMax,
					$etatBenevole,
					$categorie,
					$description,
					$etat,
					$nbInscriptions,
					$nbBenevolesAcceptes,
					$completBenevole,
					$completVisiteur
				);
				
				// Enregistrement de l'événement dans la base de données
				$success = EvenementDAO::save($evenement);
				
				if ($success) {
					// Redirection vers la page des événements avec un message de succès
					header("Location: index.php?action=voirEvents&message=Événement créé avec succès !");
					exit;
				} else {
					$this->messagesErreur[] = "Erreur lors de la création de l'événement.";
				}
			}
			
			
				

			return "ajouter-evenement.php";
		}

}

?>