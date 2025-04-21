<?php
include_once($_SERVER['DOCUMENT_ROOT'] . "/controleurs/controleur.abstract.class.php");
include_once("modele/DAO/OrganisateurDAO.class.php");
class Confirmation extends Controleur
{

    private array $messagesErreur = [];

    public function __construct()
    {
        parent::__construct();
    }

    public function getMessagesErreur(): array
    {
        return $this->messagesErreur;
    }


    public function executerAction(): string
    {

        if (!isset($_SESSION['user_id'])) {
            header("Location: index.php?action=accueil");
            exit;
        }

        $id = $_SESSION['user_id'];
        $organisateur = OrganisateurDAO::findById($id);

        $mailEnvoye = mail(
            $organisateur->getCourriel(),
            "Confirmation de votre compte",
            "Bonjour,\n\nVoici votre code de confirmation : ".$organisateur->getCodeConfirmation()."\n\nL’équipe SQAK"
        );
        
        if (!$mailEnvoye) {
            echo "Échec de l'envoi de courriel à ".$organisateur->getCourriel();
        } else {
            echo "Courriel envoyé à".$organisateur->getCourriel();
        }
        

        if ($_SERVER['REQUEST_METHOD'] === 'POST') {
            $codeEntre = $_POST['code'] ?? '';

            if ($organisateur && $organisateur->getCodeConfirmation() === $codeEntre) {
                $connexion = ConnexionBD::getInstance();
                $requete = $connexion->prepare("
                    UPDATE Organisateur 
                    SET est_confirme = 1, code_confirmation = NULL 
                    WHERE id_organisateur = :id
                ");
                $requete->bindValue(':id', $id);
                $requete->execute();

                
                header("Location: index.php?action=seConnecter&message=Compte confirmé avec succès !");
                exit;
            } else {
                $this->messagesErreur[] = "Code incorrect. Veuillez réessayer.";
            }
        }

        return "confirmation.php";
    }
}

