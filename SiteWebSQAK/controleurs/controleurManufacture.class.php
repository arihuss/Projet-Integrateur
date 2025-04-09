<?php

include_once($_SERVER['DOCUMENT_ROOT'] . "/controleurs/controleurAccueil.class.php");
include_once($_SERVER['DOCUMENT_ROOT'] . "/controleurs/controleurAjouter.class.php");
include_once($_SERVER['DOCUMENT_ROOT'] . "/controleurs/controleurCommuniquer.class.php");
include_once($_SERVER['DOCUMENT_ROOT'] . "/controleurs/controleurConfirmation.class.php");
include_once($_SERVER['DOCUMENT_ROOT'] . "/controleurs/controleurModifier.class.php");
include_once($_SERVER['DOCUMENT_ROOT'] . "/controleurs/controleurModifierProfil.class.php");
include_once($_SERVER['DOCUMENT_ROOT'] . "/controleurs/controleurProfilOrganisateur.class.php");
include_once($_SERVER['DOCUMENT_ROOT'] . "/controleurs/controleurProfilParticipant.class.php");
include_once($_SERVER['DOCUMENT_ROOT'] . "/controleurs/controleurSeConnecter.class.php");
include_once($_SERVER['DOCUMENT_ROOT'] . "/controleurs/controleurSeInscrire.class.php");
include_once($_SERVER['DOCUMENT_ROOT'] . "/controleurs/controleurSettings.class.php");
include_once($_SERVER['DOCUMENT_ROOT'] . "/controleurs/controleurVoirEvents.class.php");
include_once($_SERVER['DOCUMENT_ROOT'] . "/controleurs/controleurVoirUnEvent.class.php");
include_once($_SERVER['DOCUMENT_ROOT'] . "/controleurs/controleurPolitiques.class.php");

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
        }else if ($action == "politiques"){
            $controleur = new Politiques();
        }else if ($action == "profilParticipant"){
            $controleur = new ProfilParticipant();
        }else {
            $controleur = new Accueil();
        }
        return $controleur;
    }
}
