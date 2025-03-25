<?php

include_once("controleurs\controleurAccueil.class.php");
include_once("controleurs\controleurAjouter.class.php");
include_once("controleurs\controleurCommuniquer.class.php");
include_once("controleurs\controleurConfirmation.class.php");
include_once("controleurs\controleurModifier.class.php");
include_once("controleurs\controleurModifierProfil.class.php");
include_once("controleurs\controleurProfilOrganisateur.class.php");
include_once("controleurs\controleurProfilParticipant.class.php");
include_once("controleurs\controleurSeConnecter.class.php");
include_once("controleurs\controleurSeInscrire.class.php");
include_once("controleurs\controleurSettings.class.php");
include_once("controleurs\controleurVoirEvents.class.php");
include_once("controleurs\controleurVoirUnEvent.class.php");


class ManufactureControleur{
    public static function creerControleur($action): Controleur{
        $controleur = null;

        if ($action =="accueil"){
            $controleur = new Accueil();
        }else if($action == "ajouterProduit" ){
            $controleur = new Ajouter();
        }else if($action == "communiquer"){
            $controleur = new Communiquer();
        }else if($action == "confirmation"){
            $controleur = new Confirmation();
        }else if($action == "modifierEvent"){
            $controleur = new Modifier();
        }else if($action == "modifierProfil"){
            $controleur = new ModifierProfil();
        }else if($action == "profilOrganisateur"){
            $controleur = new ProfilOrganisateur();
        }else if ($action == "seConnecter"){
            $controleur = new SeConnecter();
        }else if($action == "seInscrire"){
            $controleur = new SeInscrire();
        }else if($action == "settings"){
            $controleur = new Settings();
        }else if ($action == "voirEvents"){
            $controleur = new VoirEvents();
        }else if ($action == "voirUnEvent"){
            $controleur = new VoirUnEvent();
        }else {
            $controleur = new Accueil();
        }
        return $controleur;
    }
}
?>