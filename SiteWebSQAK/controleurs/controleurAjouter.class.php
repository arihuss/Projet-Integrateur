<?php
include_once("controleur.abstract.class.php");
include_once("modele/DAO/EvenementDAO.class.php");
class Ajouter extends Controleur{

	private array $messagesErreur = [];
    
		public function __construct() {
		
			parent::__construct();
		}

		public function getMessagesErreur(): array {
			return $this->messagesErreur;
		}
		
		

		public function executerAction():string
		{
			if (!isset($_SESSION['user_id'])) {
			   header("Location: index.php?action=accueil");
			   exit;
			}
			
			if ($_SERVER['REQUEST_METHOD'] === 'POST') {
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
				
				
				if (empty($titre) || empty($description) || empty($categorie) || empty($lieu) || 
					empty($dateDebut) || empty($dateFin) || empty($heureDebut) || empty($heureFin)) {
					$this->messagesErreur[] = "Veuillez remplir tous les champs obligatoires.";
					return "ajouter-evenement.php";
				}
				
				
				if (strtotime($dateDebut) > strtotime($dateFin)) {
					$this->messagesErreur[] = "La date de début ne peut pas être postérieure à la date de fin.";
					return "ajouter-evenement.php";
				}
				
				
				if ($dateDebut === $dateFin && strtotime($heureDebut) >= strtotime($heureFin)) {
					$this->messagesErreur[] = "L'heure de début doit être antérieure à l'heure de fin pour un même jour.";
					return "ajouter-evenement.php";
				}
				
				
				if ($nbBenevolesMax < 0 || $nbInvitesMax < 0) {
					$this->messagesErreur[] = "Le nombre de bénévoles et d'invités doit être positif.";
					return "ajouter-evenement.php";
				}
				
				

				$photo = null;
				if (isset($_FILES['photo']) && $_FILES['photo']['error'] === UPLOAD_ERR_OK) {
					$allowedTypes = ['image/jpeg', 'image/png', 'image/gif'];
					$fileType = $_FILES['photo']['type'];
				
					if (!in_array($fileType, $allowedTypes)) {
						$this->messagesErreur[] = "Le fichier doit être une image (JPG, PNG ou GIF).";
						return "ajouter-evenement.php";
					}
				
					
					$photo = file_get_contents($_FILES['photo']['tmp_name']);
				} else {
					$this->messagesErreur[] = "Veuillez sélectionner une image pour l'événement.";
					return "ajouter-evenement.php";
				}

			
		
				$dateTimeDebut = $dateDebut;
				$dateTimeFin = $dateFin;
				
				
				$idOrganisateur = $_SESSION['user_id'] ?? null;
				
				
				if (!$idOrganisateur) {
					$this->messagesErreur[] = "Vous devez être connecté pour créer un événement.";
					return "log-in.php";
				}
				
				
				$etatBenevole = true; 
				$etat = 'disponible'; 
				$nbInscriptions = 0;
				$completBenevole = false;
				$completVisiteur = false;
				$imageEvenement = $photo;

				
				$connexion = ConnexionBD::getInstance();
				$requete = $connexion->prepare("INSERT INTO Statistique (nb_visiteurs, nb_benevoles, nb_likes, nb_vues, nb_applications, nb_partages) VALUES (0, 0, 0, 0, 0, 0)");
				$requete->execute();
				$idStatistique = $connexion->lastInsertId();

				
				$evenement = new Evenement(
					null, 
					$idStatistique,
					$_SESSION['user_id'],
					$imageEvenement,
					$titre,
					$lieu,
					$dateTimeDebut,
					$dateTimeFin,
					$heureDebut,
					$heureFin,
					$nbBenevolesMax,
					$nbInvitesMax,
					$etatBenevole,
					$categorie,
					$description,
					$etat,
					$nbInscriptions,
					$completBenevole,
					$completVisiteur
					
				);
				
				
				$success = EvenementDAO::save($evenement);
				
				if ($success) {
					
				$organisateur = OrganisateurDAO::findById($_SESSION['user_id']);
				$organisateur->setNbEvents($organisateur->getNbEvents()+1);
					header("Location: index.php?action=voirEvents&message=Événement créé avec succès !");
					exit;
				} else {
					$this->messagesErreur[] = "Erreur lors de la création de l'événement.";
				}
			}
			
			
				

			return "ajouter-evenement.php";
		}

}

