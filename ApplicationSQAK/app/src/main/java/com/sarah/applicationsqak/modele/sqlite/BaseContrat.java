package com.sarah.applicationsqak.modele.sqlite;

import android.provider.BaseColumns;

public class BaseContrat {
    public static final String DB_NAME = "SQAK.DB";
    public static final int DB_VERSION = 1;


    // Colonnes de chaque table
    public static final class UtilisateurTable {
        public static final String TABLE_NAME = "Utilisateur";
        public static final String ID_UTILISATEUR = BaseColumns._ID;
        public static final String PRENOM = "PRENOM";
        public static final String NOM = "NOM";
        public static final String COURRIEL = "COURRIEL";
        public static final String NUM_TEL = "NUM_TEL";
        public static final String BIO = "BIO";
        public static final String MOT_DE_PASSE = "MOT_DE_PASSE";
        public static final String IMAGE_URL = "IMAGE_URL";
    }

    public static final class OrganisateurTable {
        public static final String TABLE_NAME = "Organisateur";
        public static final String ID_ORGANISATEUR = BaseColumns._ID;
        public static final String PRENOM = "PRENOM";
        public static final String NOM = "NOM";
        public static final String COURRIEL = "COURRIEL";
        public static final String NOM_ORGANISATEUR = "NOM_ORGANISATEUR";
        public static final String BIO = "BIO";
        public static final String MOT_DE_PASSE = "MOT_DE_PASSE";
        public static final String NB_EVENTS = "NB_EVENTS";
        public static final String IMAGE_URL = "IMAGE_URL";
    }


    public static final class StatistiqueTable {
        public static final String TABLE_NAME = "Statistique";
        public static final String ID_STATISTIQUE = BaseColumns._ID;
        public static final String NB_VISITEURS = "NB_VISITEURS";
        public static final String NB_BENEVOLES = "NB_BENEVOLES";
        public static final String NB_LIKES = "NB_LIKES";
        public static final String NB_VUES = "NB_VUES";
        public static final String NB_PARTAGES = "NB_PARTAGES";

    }

    public static final class EvenementTable {
        public static final String TABLE_NAME = "Evenement";
        public static final String ID_EVENEMENT = BaseColumns._ID;
        public static final String ID_ORGANISATEUR = "ID_ORGANISATEUR";
        public static final String ID_STATISTIQUE = "ID_STATISTIQUE";
        public static final String NOM_EVENT = "NOM_EVENT";
        public static final String LIEU = "LIEU";
        public static final String DATE_DEBUT = "DATE_DEBUT";
        public static final String DATE_FIN = "DATE_FIN";
        public static final String NB_BENEVOLES_MAX = "NB_BENEVOLES_MAX";
        public static final String NB_PARTICIPANTS_MAX = "NB_PARTICIPANTS_MAX";
        public static final String ETAT_BENEVOLE = "ETAT_BENEVOLE";
        public static final String CATEGORIE = "CATEGORIE";
        public static final String DESCRIPTION = "DESCRIPTION";
        public static final String ETAT = "ETAT";
        public static final String NB_INSCRIPTIONS = "NB_INSCRIPTIONS";
        public static final String NB_BENEVOLES_ACCEPTES = "NB_BENEVOLES_ACCEPTES";
        public static final String COMPLET_BENEVOLE = "COMPLET_BENEVOLE";
        public static final String COMPLET_VISITEUR = "COMPLET_VISITEUR";
        public static final String IMAGE_URL = "IMAGE_URL";

    }


    public static final class InscriptionTable {
        public static final String TABLE_NAME = "Inscription";
        public static final String ID_INSCRIPTION = BaseColumns._ID;
        public static final String ID_UTILISATEUR = "ID_UTILISATEUR";
        public static final String ID_EVENEMENT = "ID_EVENEMENT";
        public static final String ROLE = "ROLE";
        public static final String DATE_INSCRIPTION = "DATE_INSCRIPTION";
        public static final String DATE_ANNULATION = "DATE_ANNULATION";

    }

    public static final class CommentaireTable {
        public static final String TABLE_NAME = "Commentaire";
        public static final String ID_COMMENTAIRE = BaseColumns._ID;
        public static final String ID_UTILISATEUR = "ID_UTILISATEUR";
        public static final String ID_EVENEMENT = "ID_EVENEMENT";
        public static final String MESSAGE = "MESSAGE";
        public static final String DATE_ENVOI = "DATE_ENVOI";

    }

    public static final class MessageTable {
        public static final String TABLE_NAME = "Message";
        public static final String ID_MESSAGE = BaseColumns._ID;
        public static final String ID_EVENEMENT = "ID_EVENEMENT";
        public static final String MOYEN_COMMUNICATION = "MOYEN_COMMUNICATION";
        public static final String TYPE_DESTINATAIRE = "TYPE_DESTINATAIRE";
        public static final String MESSAGE = "MESSAGE";
        public static final String DATE_ENVOI = "DATE_ENVOI";

    }

    public static final class LikeUtilisateurTable {
        public static final String TABLE_NAME = "LikeUtilisateur";
        public static final String ID_UTILISATEUR = "ID_UTILISATEUR";  // clé primaire composite
        public static final String ID_STATISTIQUE = "ID_STATISTIQUE";  // clé primaire composite
    }
}